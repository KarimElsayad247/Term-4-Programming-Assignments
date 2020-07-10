package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Crossers.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Level.ICrossingStrategy;
import Level.StorySceneController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class RiverCrossingController implements IRiverCrossingController {

    private static RiverCrossingController riverCrossingController = null;

    private int NumberOfSails;
    private boolean boatOnTheLeftBank;
    private List<ICrosser> crossersOnRightBank = new ArrayList<>();
    private List<ICrosser> crossersOnLeftBank = new ArrayList<>();
    private ArrayList<ICrosser> crossersOnBoat = new ArrayList<>();
    private ICrossingStrategy level;
    private ArrayList<Label> observerLabels = new ArrayList<>();
    private int initialCrossersSize;
    private boolean canLoad=true;
     UndoCareteker careteker = new UndoCareteker();

   


	private RiverCrossingController() {
    }

    public static RiverCrossingController MakeRiverCrossingController() {
        if (riverCrossingController == null) {
            riverCrossingController = new RiverCrossingController();
        }
        return riverCrossingController;
    }

    @Override
    public void newGame(ICrossingStrategy gameStrategy) {
        level = gameStrategy;
        System.out.println("set strategy to chosen");
        crossersOnRightBank = level.getInitialCrossers();
        System.out.println("loaded initial crossers");
        crossersOnLeftBank.clear();
        crossersOnBoat.clear();
        for (Label label : observerLabels
        ) {
            label.setText("Sails: 0");
        }
        initialCrossersSize = crossersOnRightBank.size();
        NumberOfSails = 0;

    }

    public void addObserver(Label label) {
        observerLabels.add(label);
    }

    private void updateObservers() {
        for (Label label : observerLabels
        ) {
            label.setText("Sails: " + (getNumberOfSails()));
        }
    }

    @Override
    public void resetGame() {
        crossersOnRightBank = level.getInitialCrossers();
        crossersOnLeftBank.clear();
        crossersOnBoat.clear();
        boatOnTheLeftBank = false;
        NumberOfSails = 0;
        updateObservers();
    }

    @Override
    public String[] getInstructions() {
        return level.getInstructions();
    }

    @Override
    public List<ICrosser> getCrossersOnRightBank() {
        return crossersOnRightBank;
    }

    @Override
    public List<ICrosser> getCrossersOnLeftBank() {
        return crossersOnLeftBank;
    }

    @Override
    public boolean isBoatOnTheLeftBank() {

        return boatOnTheLeftBank;
    }

    public void setBoatOnTheLeftBank(boolean currentP)
    {
        this.boatOnTheLeftBank=currentP;
    }


    @Override
    public int getNumberOfSails() {

        return NumberOfSails;
    }

    @Override
    public boolean canMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
        return level.isValid(crossersOnRightBank, crossersOnLeftBank, crossers);
    }


    @Override
    public void doMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {

        if (!fromLeftToRightBank) {
            System.out.println("move from right to left");
            //TODO: add to undo

        } else if (fromLeftToRightBank) {
            System.out.println("move from left to right");
            //TODO: add to undo
        }
        NumberOfSails++;
        boatOnTheLeftBank = !boatOnTheLeftBank;
        updateObservers();

    }



    public ArrayList<ICrosser> getCrossersOnBoat() {
        return crossersOnBoat;
    }

    @Override
    public boolean canUndo() {
        if(careteker.isUndoEmpty())
            return true;
        else return false;
    }

    @Override
    public boolean canRedo(){
        if(careteker.isRedoEmpty())
            return true;
        else return false;
    }

    public void saveState(RiverCrossingController re) {
        careteker.SaveState(re);
    }
    @Override
    public void undo() {
        if(canUndo())
            careteker.getPreviousState(this);
    }

    @Override
    public void redo() {
        if(canRedo())
            careteker.getNextState(this);
    }

    @Override
    public void saveGame() {
        try {
        	
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element crossers = document.createElement("Crossers");

            
            Element levels = document.createElement("Level");
            Text levelsText = document.createTextNode(Getlevel().toString());
            levels.appendChild(levelsText);
            crossers.appendChild(levels);

            for (int i = 0; i < crossersOnRightBank.size(); i++) {
                Element name = document.createElement("CrossersOnRight");
                Text crosser = document.createTextNode(crossersOnRightBank.get(i).toString());
                Element weight = document.createElement("Weight");
                Text weightText = document.createTextNode(Double.toString(crossersOnRightBank.get(i).getWeight()));

                weight.appendChild(weightText);
                name.appendChild(crosser);
                name.appendChild(weight);
                crossers.appendChild(name);

            }


            for (int i = 0; i < crossersOnBoat.size(); i++) {
                Element name = document.createElement("crossersOnBoat");
                Text crosser = document.createTextNode(crossersOnBoat.get(i).toString());
                Element weight = document.createElement("Weight");
                Text weightText = document.createTextNode(Double.toString(crossersOnRightBank.get(i).getWeight()));

                weight.appendChild(weightText);
                name.appendChild(crosser);
                name.appendChild(weight);
                crossers.appendChild(name);
            }


            for (int i = 0; i < crossersOnLeftBank.size(); i++) {
                Element name = document.createElement("CrossersOnLeft");
                Text crosser = document.createTextNode(crossersOnLeftBank.get(i).toString());
                Element weight = document.createElement("Weight");
                Text weightText = document.createTextNode(Double.toString(crossersOnLeftBank.get(i).getWeight()));

                weight.appendChild(weightText);
                name.appendChild(crosser);
                name.appendChild(weight);
                crossers.appendChild(name);
            }


            Element sails = document.createElement("NumberOfSails");
            Text numberOfSails = document.createTextNode(Integer.toString(NumberOfSails));
            sails.appendChild(numberOfSails);
            crossers.appendChild(sails);


            Text BoatPosition;
            Element boat = document.createElement("BoatPosition");
            if (isBoatOnTheLeftBank()) {
                BoatPosition = document.createTextNode("Left");

            } else {
                BoatPosition = document.createTextNode("Right");
            }
            boat.appendChild(BoatPosition);
            crossers.appendChild(boat);


            ///////////////////

            document.appendChild(crossers);
////


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("data.xml"));
            transformer.transform(source, streamResult);


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void loadGame() {

        try {
        	
            CrosserFactory crosserFactory = new CrosserFactory();
            File xmlFiler = new File("data.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFiler);

            crossersOnRightBank.clear();
            crossersOnLeftBank.clear();
            crossersOnBoat.clear();
            
            NodeList list0 = document.getElementsByTagName("Level");
            Node node0 = list0.item(0);
            Element elemented0 = (Element) node0;
          
        if(Getlevel().toString().equals(elemented0.getTextContent()))
        {
        canLoad=true;
            NodeList list = document.getElementsByTagName("CrossersOnRight");
            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                Element elemented = (Element) node;

                ICrosser crosserOnRight = crosserFactory.getInstance(elemented.getFirstChild().getTextContent(), Double.parseDouble(elemented.getLastChild().getTextContent()));
                crossersOnRightBank.add(crosserOnRight);


            }
            NodeList list2 = document.getElementsByTagName("crossersOnBoat");

            for (int i = 0; i < list2.getLength(); i++) {
                Node node = list2.item(i);
                Element elemented = (Element) node;

                ICrosser crosserOnBoat = crosserFactory.getInstance(elemented.getFirstChild().getTextContent(), Double.parseDouble(elemented.getLastChild().getTextContent()));
                crossersOnBoat.add(crosserOnBoat);
            }

            NodeList list3 = document.getElementsByTagName("CrossersOnLeft");

            for (int i = 0; i < list3.getLength(); i++) {
                Node node = list3.item(i);
                Element elemented = (Element) node;

                ICrosser crosserOnLeft = crosserFactory.getInstance(elemented.getFirstChild().getTextContent(), Double.parseDouble(elemented.getLastChild().getTextContent()));
                crossersOnLeftBank.add(crosserOnLeft);
//          load.loadImagesR(crosserOnLeft);

            }


            NodeList list4 = document.getElementsByTagName("NumberOfSails");

            if (list4.getLength() > 0) {

                Node node1 = list4.item(0);
                Element element1 = (Element) node1;
                NumberOfSails = Integer.parseInt(element1.getTextContent());
            }

            NodeList list5 = document.getElementsByTagName("BoatPosition");

            if (list5.getLength() > 0) {

                Node node2 = list5.item(0);
                Element element2 = (Element) node2;
                String position = element2.getTextContent();

                if (position.equals("Left")) {
                    boatOnTheLeftBank = true;
                } else {
                    boatOnTheLeftBank = false;
                }
            }
        }
        else
        {
        	System.out.println("Nothing to load");
        	canLoad=false;
        }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isCanLoad() {
		return canLoad;
	}
    @Override
    public List<List<ICrosser>> solveGame() {

        return null;
    }
   public ICrossingStrategy Getlevel() {
	   return level;
   }
   
 public void clear()
 {
	 careteker.flush();
 }
    public boolean didWin() {

        System.out.println("comparing numbers, initial crossers = "
                + initialCrossersSize + " crossers on left = " + crossersOnLeftBank.size());
        if (crossersOnLeftBank.size() == initialCrossersSize
                || (crossersOnLeftBank.size() + crossersOnBoat.size() == initialCrossersSize
                && isBoatOnTheLeftBank())) {
            return true;
        } else {
            return false;
        }
    }
}
