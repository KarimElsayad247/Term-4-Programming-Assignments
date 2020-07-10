package Crossers;

public class CrosserFactory
{
    public ICrosser getInstance(String str,Double weight)
    {
        if(weight==0.0)
        {
            if(str.equals("Farmer"))
                return new Farmer();
            else if (str.equals("Carni"))
                return new Carnivore();
            else if(str.equals("Herbi"))
                return new Herbivore();
            else return new Plant();
        }
        else
        {
            if(str.equals("Farmer"))
                return new Farmer(weight);
            else if (str.equals("Carni"))
                return new Carnivore(weight);
            else if(str.equals("Herbi"))
                return new Herbivore(weight);
            else return new Plant(weight);
        }

    }

}
