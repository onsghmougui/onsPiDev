/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import formations.formation;
import formations.formationServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ghmougui ons
 */
public class AddFormationGuiController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfCategorie;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfRemise;
    @FXML
    private TextField tfDuree;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button btnSauvegarder;
    @FXML
    private Button btnUpload;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void UplderVd(ActionEvent event) {
    }

    @FXML
    private void SavePerson(MouseEvent event) {
        String titre = tfTitre.getText();
        String categorie = tfCategorie.getText();
        double prix =Double.parseDouble(tfPrix.getText());
        float remise =Float.parseFloat(tfRemise.getText());
        String duree = tfDuree.getText();
        String description = tfDescription.getText();
        
        formation f= new formation(titre, categorie, prix, remise, duree, description) ;
      
        formationServices fs = new formationServices();
        fs.ajouter(f);
    }
    
}
