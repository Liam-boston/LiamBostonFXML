/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usermodel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author liamb
 */
public class DetailedModelViewController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField ageField;

    @FXML
    private Button backButton;

    @FXML
    private ImageView image;

    Usermodel selectedUser;

    Scene previousScene;

    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (previousScene != null) {
            stage.setScene(previousScene);
        }
    }

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);
    }

    public void initData(Usermodel model) {
        selectedUser = model;
        idField.setText(model.getId().toString());
        nameField.setText(model.getName());
        heightField.setText(model.getHeight());
        weightField.setText("" + model.getWeight());
        ageField.setText("" + model.getAge());

        try {
            String imagename = "/resource/images/profilepic.jpg";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert heightField != null : "fx:id=\"heightField\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert weightField != null : "fx:id=\"weightField\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert ageField != null : "fx:id=\"ageField\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        
        backButton.setDisable(true);
    }

}
