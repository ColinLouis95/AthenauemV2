package com.athenauem;

import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Athenauem extends Application {
    private generateLayouts lay_control = new generateLayouts();
    private dataControl db_controller = new dataControl();
    Scene main, addScene, editScene;
    Stage window;

    public Athenauem() throws SQLException, ClassNotFoundException {
    }

    /* constructor for main menu */
    private FlowPane main_menu(HBox welcome, Button newEntry, Button editVault){
        FlowPane root = lay_control.generateFP();
        VBox logo = lay_control.generateLogo();
        VBox button_container = new VBox(10);

        // add Buttons to container
        button_container.setAlignment(Pos.CENTER);
        button_container.getChildren().addAll(newEntry, editVault);

        // add everything to root.
        root.getChildren().add(welcome);
        root.getChildren().add(logo);
        root.getChildren().add(button_container);

        // setting actions for buttons to switch scenes.
        newEntry.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) {window.setScene(addScene);}});
        editVault.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) {window.setScene(editScene);}});

        return root;
    }

    /* constructor for addEntry view */
    private FlowPane newEntry(){
        /* general fields for use */
        VBox logo = lay_control.generateLogo();

        FlowPane root = lay_control.generateFP();
        root.setHgap(8);
        root.setVgap(8);

        TextField username = lay_control.generateTF("Enter in a user name:");
        username.setAlignment(Pos.TOP_CENTER);

        TextField password = lay_control.generateTF("Enter in a password:");
        password.setAlignment(Pos.CENTER);

        TextField origin = lay_control.generateTF("Enter in the site:");
        origin.setAlignment(Pos.BASELINE_CENTER);

        /* once button is clicked, save entered values into program database */
        Button add = lay_control.makeButton();
        add.setText("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) {
                String user = username.getText();
                String pass = password.getText();
                String site = origin.getText();
                db_controller.insertNew(user,pass,site);

                username.clear();
                password.clear();
                origin.clear();
            }
        });

        /* return to main menu */
        Button home = lay_control.makeButton();
        home.setText("Return to Main Menu");
        home.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) {
                window.setScene(main);}});

        /* add everything into root window */
        root.getChildren().add(logo);
        root.getChildren().add(username);
        root.getChildren().add(password);
        root.getChildren().add(origin);
        root.getChildren().add(add);
        root.getChildren().add(home);
        return root;
    }

    /* creating a TableView for cleaner data display, adding to a GridPane for button support */
    private GridPane editVault(){
        GridPane root = lay_control.generateGP();
        root.setHgap(15);
        root.setVgap(15);
        root.setPadding(new Insets(15));
        root.setPrefHeight(600);
        root.setPrefHeight(450);

        /* Table View for displaying user information */
        TableView <UserInfo> tv = new TableView<UserInfo>();
        tv.setPrefHeight(450);
        tv.setPrefWidth(600);

        TableColumn<UserInfo, String> userCol = new TableColumn<>("Username");
        userCol.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

        TableColumn<UserInfo, String> passCol = new TableColumn<>("Password");
        passCol.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

        TableColumn<UserInfo, String> siteCol = new TableColumn<>("Site");
        siteCol.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

        tv.getColumns().addAll(userCol, passCol, siteCol);

        /* gathering the user info and adding it into the table view */
        List<UserInfo> userInfoList = dataControl.retrieveAll();
        tv.getItems().addAll(userInfoList);


        /* creating button to go back to home screen */
        //TODO: determine if we want to add buttons for
        // adding/updating/deleting info within this screen. We have an addEntry view already set,
        // do we want to allow user to add info from this screen too?
        tv.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){ // user will double click on row to edit/delete info
                UserInfo selectedInfo = tv.getSelectionModel().getSelectedItem();
                if(selectedInfo != null){
                    setDiagBox(selectedInfo);
                }
            }
        });

        Button returnHome = lay_control.makeButton();
        returnHome.setText("Return to Main Menu");
        returnHome.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent actionEvent) {
            window.setScene(main);}});

        root.add(tv, 0, 0, 2, 1);
        root.add(returnHome, 0, 1);
        root.setStyle("-fx-font-family: 'serif'");
        return root;
    }
    private void setDiagBox(UserInfo us){
        String oldSite = us.getSite();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit or Delete Entry");

        ButtonType edit = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);
        ButtonType delete = new ButtonType("Delete", ButtonBar.ButtonData.FINISH);
        dialog.getDialogPane().getButtonTypes().addAll(edit, delete, ButtonType.CANCEL);

        // Set font for buttons
        Platform.runLater(() -> {
            for (ButtonType buttonType : dialog.getDialogPane().getButtonTypes()) {
                ButtonBase button = (ButtonBase) dialog.getDialogPane().lookupButton(buttonType);
                if (button != null) {
                    button.setFont(Font.font("Serif", 14));
                }
            }
        });

        // Create a grid pane for the edit dialog
        GridPane editGrid = new GridPane();
        editGrid.add(new Label("Username:"), 0, 0);
        editGrid.add(new Label("Password:"), 0, 1);
        editGrid.add(new Label("Site:"), 0, 2);
        editGrid.setStyle("-fx-font-family: 'serif'");

        TextField usernameField = new TextField(us.getUsername());
        PasswordField passwordField = new PasswordField();
        passwordField.setText(us.getPassword());
        TextField siteField = new TextField(us.getSite());

        editGrid.add(usernameField, 1, 0);
        editGrid.add(passwordField, 1, 1);
        editGrid.add(siteField, 1, 2);

        dialog.getDialogPane().setContent(editGrid);
        // Enable/disable the "Edit" button based on input validation
        Node editButton = dialog.getDialogPane().lookupButton(edit);
        editButton.setDisable(true);

        // Add listeners to enable/disable the "Edit" button
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            editButton.setDisable(newValue.trim().isEmpty());
        });
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            editButton.setDisable(newValue.trim().isEmpty());
        });
        siteField.textProperty().addListener((observable, oldValue, newValue) -> {
            editButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.setContentText("Username: " + us.getUsername() +
                "\nPassword: " + us.getPassword() +
                "\nWebsite: " + us.getSite());


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == edit) {
                db_controller.deleteEntry(oldSite); //remove outdated entry
                String editedUsername = usernameField.getText();
                String editedPassword = passwordField.getText();
                String editedSite = siteField.getText();
                db_controller.insertNew(editedUsername,editedPassword,editedSite);  //enter updated entry

            }else if (dialogButton == delete) {
                db_controller.deleteEntry(oldSite);
            }

            return null;
        });

        dialog.showAndWait();
    }

    // start method for Athenauem
    @Override
    public void start (Stage primaryStage){
        window = primaryStage;
        Label welcome = lay_control.makeLabel();
        welcome.setText("Welcome to Athenauem");

        HBox holdWelcome = new HBox(10);
        holdWelcome.setAlignment(Pos.CENTER);
        holdWelcome.getChildren().add(welcome);

        Button add = lay_control.makeButton();
        add.setText("Add New Entry");
        Button edit = lay_control.makeButton();
        edit.setText("Display Your Vault");

        FlowPane home = main_menu(holdWelcome, add, edit);
        main = new Scene(home, 450, 500);

        FlowPane addToVault = newEntry();
        addScene = new Scene(addToVault, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        addScene.getRoot().setStyle("-fx-font-family: 'serif'");
        GridPane displayVault = editVault();
        editScene = new Scene(displayVault, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        editScene.getRoot().setStyle("-fx-font-family: 'serif'");

        window.setScene(main);
        window.setTitle("Athenauem");
        window.centerOnScreen();
        window.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);

    }

    public static void main(String[] args){
        launch(args);
    }

}
