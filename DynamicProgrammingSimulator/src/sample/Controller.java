package sample;


import java.io.IOException;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.stage.*;
//import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;
import sample.Main;
//import java.awt.event.ActionEvent;
import java.sql.SQLOutput;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import javax.swing.*;

public class Controller {
   // private Button button2;

    @FXML
    private void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("knp.fxml"));
        Scene tableViewScene = new Scene(tableViewParent, 945, 615);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      //  button2.setOnAction(e -> window.setScene(tableViewScene));
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void changeScreenButtonPushed2(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("coin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent, 945, 615);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //  button2.setOnAction(e -> window.setScene(tableViewScene));
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void changeScreenButtonPushed3(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("lcs.fxml"));
        Scene tableViewScene = new Scene(tableViewParent, 945,  615);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //  button2.setOnAction(e -> window.setScene(tableViewScene));
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void changeScreenButtonPushed4(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("lis.fxml"));
        Scene tableViewScene = new Scene(tableViewParent, 945, 615);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //  button2.setOnAction(e -> window.setScene(tableViewScene));
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void changeScreenButtonPushed5(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("mcm.fxml"));
        Scene tableViewScene = new Scene(tableViewParent, 945, 615);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //  button2.setOnAction(e -> window.setScene(tableViewScene));
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    private void changeScreenButtonPushed6(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("edit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent, 945, 615);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //  button2.setOnAction(e -> window.setScene(tableViewScene));
        window.setScene(tableViewScene);
        window.show();
    }

}
