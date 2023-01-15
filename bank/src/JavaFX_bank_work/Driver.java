package bank_work;

import java.util.*;
import javafx.application.*;
import javafx.collections.FXCollections; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.geometry.*;
import javafx.stage.*; 
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.*;


public class Driver extends Application {

    private AccountService accountService = new AccountService();
    private Account account;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank Application");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome! Please Login.");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userId = new Label("User ID:");
        grid.add(userId, 0, 1);

        TextField userIdTextField = new TextField();
        grid.add(userIdTextField, 1, 1);

        Label pin = new Label("Pin:");
        grid.add(pin, 0, 2);

        PasswordField pinTextField = new PasswordField();
        grid.add(pinTextField, 1, 2);

        Button loginButton = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginButton);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                String userId = userIdTextField.getText();
                String pin = pinTextField.getText();

                if (accountService.isValid(userId, pin)) {
                    account = accountService.getAccount(userId);
                    actiontarget.setFill(Color.GREEN);
                    actiontarget.setText("Login successful!");
                    showMainMenu();
                } else {
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("Invalid userId or pin. Please try again.");
                }
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMainMenu() {
        Stage stage = new Stage();
        stage.setTitle("Main Menu");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Main Menu");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);



        Button withdrawButton = new Button("Withdraw");
        grid.add(withdrawButton, 1, 1);

        Button depositButton = new Button("Deposit");
        grid.add(depositButton, 0, 2);

        Button transferButton = new Button("Transfer");
        grid.add(transferButton, 1, 2);

        Button logoutButton = new Button("Log Out");
        grid.add(logoutButton, 1, 3);
        
        Button checkBalance = new Button("Check Balance");
        grid.add(checkBalance, 0, 3);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 4);
        
    
        Button transactionsButton = new Button("Transactions");
        grid.add(transactionsButton, 0, 1);
        boolean showTransferHistory = false;

        transactionsButton.setOnAction(new EventHandler<ActionEvent>() {
    	  
      @Override
      public void handle(ActionEvent event) {
          String transactions = accountService.getTransactions(account.getUserId());
          actiontarget.setText(transactions);
      }
  });

        
        checkBalance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	double balance = accountService.checkBalance(account.getUserId());
                actiontarget.setText(Double.toString(balance));
            	
            }
        });
          

        withdrawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField withdrawField = new TextField("Withdraw Amount here");
                grid.add(withdrawField, 2, 1);
                withdrawField.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                    	withdrawField.setText("");
                    }

                });
                Button withdrawConfirmButton = new Button("Confirm");
                grid.add(withdrawConfirmButton, 3, 1);

                withdrawConfirmButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        double withdrawAmount = Double.parseDouble(withdrawField.getText());
                        if (withdrawAmount > 0 && withdrawAmount <= account.getBalance()) {
                            accountService.withdraw(account.getUserId(), withdrawAmount);
                            actiontarget.setText("Withdraw Successful!");
                        } else {
                            actiontarget.setText("Withdraw Not Successful!");
                        }
                    }
                });
            }
        });

        depositButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField depositField = new TextField("Deposit Amount");
                grid.add(depositField, 2, 2);
                depositField.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                    	depositField.setText("");
                    }

                });
                Button depositConfirmButton = new Button("Confirm");
                grid.add(depositConfirmButton, 3, 2);

                depositConfirmButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        double depositAmount = Double.parseDouble(depositField.getText());
                        if (depositAmount > 0) {
                            accountService.deposit(account.getUserId(), depositAmount);
                            actiontarget.setText("Deposit Successful!");
                        } else {
                            actiontarget.setText("Invalid Deposit Amount!");
                        }
                    }
                });
            }
        });

        transferButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField transferUserIdField = new TextField("UserID to transfer");
                grid.add(transferUserIdField, 2, 3);
                transferUserIdField.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                    	transferUserIdField.setText("");
                    }

                });
                TextField transferAmountField = new TextField("Amount to transfer");
                grid.add(transferAmountField, 2, 4);
                transferAmountField.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                    	transferAmountField.setText("");
                    }

                });
                Button transferConfirmButton = new Button("Confirm");
                grid.add(transferConfirmButton, 3, 4);

                transferConfirmButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String transferUserId = transferUserIdField.getText();
                        double transferAmount = Double.parseDouble(transferAmountField.getText());
                        if (accountService.isValid(transferUserId) && transferAmount > 0 && transferAmount <= account.getBalance()) {
                            if (accountService.transfer(account.getUserId(), transferUserId, transferAmount)) {
                            	if(account.getUserId().equals(transferUserId)) {
                            		 actiontarget.setText("Can't transfer to your own Account!");
                            	}else
                                actiontarget.setText("Transfer Successful!");
                            } else {
                                actiontarget.setText("Transfer Not Successful!");
                            }
                        } else {
                            actiontarget.setText("Transfer Not Successful!");
                        }
                    }
                });
            }
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                start(new Stage());
            }
        });

       
        Scene scene = new Scene(grid, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}



