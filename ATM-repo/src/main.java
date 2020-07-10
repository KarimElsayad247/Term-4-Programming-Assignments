import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class main extends Application {


    //Scanner scans = new Scanner(System.in);
    Account userAccount;
    //Label resultLabel = new Label();
    Scene mainMenuScene, withdrawMenuScene, depositMenuScene;
            //createAccountMenu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) {

        //Creating accounts
        Account user1 = new Account(0.50, "1234567");
        Account user2 = new Account(2000, "9876543");
        Account user3 = new Account(3000, "1357913");
        Account user4 = new Account(1000000, "9999999");

        Account[] arrayAccount = {user1, user2, user3, user4};

        Button signInButton = new Button("Sign in");
        Button exitButton = new Button("Exit");
        Label accountNumberLabel = new Label("Account number");
        TextField accountNumberInput = new TextField();
        AlertBox incorrectCreds = new AlertBox();

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String var = accountNumberInput.getText();
                int i;
                int flag = 0;
                for (i = 0; i < 4; i++) {
                    if (var.equals(arrayAccount[i].getCardNumber())) {
                        userAccount = arrayAccount[i];
                        flag = 1;
                        mainWindow.setScene(mainMenuScene);
                    }
                }
                if (flag == 0) {
                    incorrectCreds.display("Incorrect Number", "Please chech the number again!");
                }

            }
        });



        exitButton.setOnAction(event -> mainWindow.close());

        GridPane signInLayout = new GridPane();
        signInLayout.setVgap(20);
        signInLayout.setHgap(20);
        signInLayout.setPadding(new Insets(10, 10, 10, 10));

        GridPane.setConstraints(accountNumberLabel, 0, 0);
        GridPane.setConstraints(accountNumberInput, 1, 0);
        GridPane.setConstraints(exitButton, 0, 1);
        GridPane.setConstraints(signInButton, 1, 1);

        signInLayout.getChildren().addAll(accountNumberLabel, accountNumberInput, exitButton, signInButton);


        Scene signInScene = new Scene(signInLayout, 400, 100);




        /****             main menu               *****/

        Button withdrawMenuButton = new Button("Withdraw");
        Button depositMenuButton = new Button("Deposit");
        Button nextTransactionButton = new Button("Next Transaction");
        Button prevTransactionButton = new Button("Previous Transaction");
        Button balanceInquiryButton = new Button("Balance Inquiry");
        Label infoLabel = new Label("Choose an action");

        withdrawMenuButton.setOnAction(event -> mainWindow.setScene(this.withdrawMenuScene));
        depositMenuButton.setOnAction(event -> mainWindow.setScene(this.depositMenuScene));
        nextTransactionButton.setOnAction(event -> infoLabel.setText(userAccount.nextTransaction()));
        prevTransactionButton.setOnAction(event -> infoLabel.setText(userAccount.prevTransaction()));
        balanceInquiryButton.setOnAction(event -> infoLabel.setText("Balance is: " + userAccount.getBalance()));

        //balanceInquiryButton.setOnAction(event -> infoLabel.setText());

        GridPane mainMenuScene = new GridPane();
        mainMenuScene.setPadding(new Insets(40, 20, 20, 20));
        mainMenuScene.setHgap(30);
        mainMenuScene.setVgap(30);

        TextField placeHolder = new TextField();
        mainMenuScene.setMinHeight(placeHolder.getHeight());



        GridPane.setConstraints(withdrawMenuButton, 0, 1);
        GridPane.setConstraints(depositMenuButton, 1,1);
        GridPane.setConstraints(nextTransactionButton, 2, 1);
        GridPane.setConstraints(prevTransactionButton, 3, 1);
        GridPane.setConstraints(balanceInquiryButton, 4, 1);
        GridPane.setConstraints(infoLabel, 0, 0, 4,1);

        mainMenuScene.getChildren().addAll(infoLabel, withdrawMenuButton, depositMenuButton, prevTransactionButton, nextTransactionButton, balanceInquiryButton);

        /********* withdraw and deposit      ******/


        TextField withdrawAmountInput = new TextField();
        TextField depositAmountInput = new TextField();

        Label withdrawAmountLabel = new Label("Enter amount to withdraw");
        Label depositAmountLabel = new Label("Enter amount to  deposit");


        Button withdrawConfirmButton = new Button("Withdraw");
        withdrawConfirmButton.setOnAction(event -> userAccount.withdraw(Double.valueOf(withdrawAmountInput.getText())));

        Button withdrawToMainMenuButton = new Button("Back");

        Button depositConfirmButton = new Button("Deposit");
        depositConfirmButton.setOnAction(event -> userAccount.deposit(Double.valueOf(depositAmountInput.getText())));

        Button depositToMainMenuButton = new Button("Back");

        withdrawToMainMenuButton.setOnAction(event -> mainWindow.setScene(this.mainMenuScene));
        depositToMainMenuButton.setOnAction(event -> mainWindow.setScene(this.mainMenuScene)); ;


        GridPane withdrawMenuScene = new GridPane();
        GridPane depositMenuScene = new GridPane();

        withdrawMenuScene.setHgap(30);
        withdrawMenuScene.setVgap(30);
        withdrawMenuScene.setPadding(new Insets(40,20,20,20));

        depositMenuScene.setHgap(30);
        depositMenuScene.setVgap(30);
        depositMenuScene.setPadding(new Insets(40,20,20,20));

        GridPane.setConstraints(withdrawAmountLabel, 0, 0);
        GridPane.setConstraints(depositAmountLabel, 0, 0);

        GridPane.setConstraints(withdrawAmountInput, 1, 0);
        GridPane.setConstraints(depositAmountInput, 1,0);

        GridPane.setConstraints(withdrawConfirmButton, 1,1);
        GridPane.setConstraints(depositConfirmButton, 1,1);

        GridPane.setConstraints(withdrawToMainMenuButton, 0, 1);
        GridPane.setConstraints(depositToMainMenuButton, 0, 1);

        //GridPane.setConstraints(resultLabel, 2, 0);



        withdrawMenuScene.getChildren().addAll(withdrawAmountLabel, withdrawAmountInput, withdrawToMainMenuButton, withdrawConfirmButton);
        depositMenuScene.getChildren().addAll(depositAmountLabel, depositAmountInput, depositToMainMenuButton, depositConfirmButton);

        this.withdrawMenuScene = new Scene(withdrawMenuScene, 600, 180);
        this.depositMenuScene = new Scene(depositMenuScene, 600, 180);

        //mainMenuScene.getRowConstraints().add(new RowConstraints(withdrawAmountInput.getWidth()));

        // System.out.println(infoLabel.getText());






        this.mainMenuScene = new Scene(mainMenuScene, 800, 180);
        mainWindow.setTitle("ATM simulator");
        mainWindow.setScene(signInScene);
        //Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

        mainWindow.show();
    }


}








