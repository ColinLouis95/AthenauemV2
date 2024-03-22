package com.athenauem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/* This class will serve as a window generator,
* involving methods that will generate the views the user will see when using the program.
*
* the views class will have a default constructor,
* when called in the main application all will be done via views.method();
* */
public class generateLayouts /*extends Application */{

    public generateLayouts(){
        /* TODO: see if default constructor is needed, currently just need to generate layouts specific to
            a specific view. ex  GridPane gp = generateLayouts.generateGP(); etc etc.
         */
    }

    /* method for general FlowPlane layout */
    public FlowPane generateFP() {
        // FlowPane root
        FlowPane root = new FlowPane();

        // setting up the orientation of root
        root.setAlignment(Pos.TOP_CENTER);
        root.setOrientation(Orientation.VERTICAL);
        root.setPadding(new Insets(15,16,17,18));
        root.setHgap(5);
        root.setVgap(5);
        root.setStyle("-fx-background-color: #1e90ff; -fx-border-color: #663399; -fx-border-width: 5px");

        return root;
    }

    /* method for general GridPane layout */
    public GridPane generateGP(){
        GridPane root = new GridPane();

        // determine orientation of GridPane
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10,11,12,13));
        root.setHgap(5);
        root.setVgap(5);
        root.setStyle("-fx-background-color: #1e90ff; -fx-border-color: #663399; -fx-border-width: 10px");

        return root;
    }

    /* method for general TextArea layout */
    public TextArea generateTA(String s){
        TextArea root = new TextArea();

        // orientation of TextArea
        root.setText(s);
        root.setPrefWidth(265);
        root.setPrefHeight(240);
        root.setEditable(false);
        root.setWrapText(true);
        root.setStyle("-fx-text-fill: #483d8b");
        root.setFont(Font.font("Source Serif Pro", FontWeight.BOLD, FontPosture.ITALIC,20));

        return root;
    }

    /* method for general TextField layout */
    public TextField generateTF(String s){
        TextField root = new TextField();

        //orientation of TextField
        root.setPrefWidth(250);
        root.setPromptText(s);
        root.setEditable(true);
        root.setStyle("-fx-font: 14 arial; -fx-text-fill: #000000; -fx-base: #6495ed;"
                + " -fx-border-color: #4b0082; -fx-border-width: 3px");

        return root;
    }

    /* method for general VBox layout */
    public VBox generateVBox(){
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-border-color: #001f4d; -fx-border-width: 4px");

        return root;
    }

    /* method for general Button layout */
    public Button makeButton(){
        Button root = new Button();
        root.setStyle("-fx-font: 14 arial; -fx-font-weight: bold; -fx-base: #6495ed;"
                + " -fx-text-fill: #483d8b; -fx-border-color: #4b0082; -fx-border-width: 3px");
        root.setMaxSize(250, 100);
        return root;

    }

    /* method for general Label layout */
    public Label makeLabel(){
        Label root = new Label();
        root.setWrapText(true);
        root.setStyle("-fx-text-fill: #000080; -fx-font-weight: bold");
        root.setFont(Font.font("Tahoma",FontWeight.BOLD,FontPosture.ITALIC,20));

        return root;
    }

    /* method for creating project logo */
    public VBox generateLogo() {
        Image logo;
        ImageView holdLogo;
        VBox frameLogo = new VBox(15);
        frameLogo.setAlignment(Pos.TOP_CENTER);

        try {
            logo = new Image(new FileInputStream("src/images/welcome.png"));
            holdLogo = new ImageView(logo);
            frameLogo.getChildren().add(holdLogo);

        }
        catch(FileNotFoundException e) {
            System.out.println("File not found, check path of file.");
            e.printStackTrace();
        }
        return frameLogo;
    }





    /*public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

    } */
}
