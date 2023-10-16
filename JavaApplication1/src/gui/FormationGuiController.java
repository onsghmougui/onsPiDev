/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import formations.formation;
import formations.formationServices;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ghmougui ons
 */
public class FormationGuiController implements Initializable {
   
    @FXML
    private Button btnRechercher;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableColumn<formation, Long> idcolumn;
    @FXML
    private TableColumn<formation,String> titrecolumn;
    @FXML
    private TableColumn<formation,String> categoriecolumn;
    @FXML
    private TableColumn<formation,Double> prixcolumn;
    @FXML
    private TableColumn<formation,Float> remisecolumn;
    @FXML
    private TableColumn<formation,String> dureecolumn;
    @FXML
    private TableColumn<formation,String> descriptioncolumn;
    
    @FXML
    private TableView<formation> tableFormation;
@FXML
    private TextField tfsearch;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idcolumn.setCellValueFactory(new PropertyValueFactory<formation,Long>("id"));
        titrecolumn.setCellValueFactory(new PropertyValueFactory<formation,String>("titre"));
        categoriecolumn.setCellValueFactory(new PropertyValueFactory<formation,String>("categories"));
        prixcolumn.setCellValueFactory(new PropertyValueFactory<formation,Double>("prix"));
        remisecolumn.setCellValueFactory(new PropertyValueFactory<formation,Float>("remise"));
        dureecolumn.setCellValueFactory(new PropertyValueFactory<formation,String>("duree"));
        descriptioncolumn.setCellValueFactory(new PropertyValueFactory<formation,String>("description"));
        formationServices fS= new formationServices();
        ObservableList <formation> formationList=FXCollections.observableArrayList(fS.retournerTout());
        tableFormation.setItems(formationList);
        //String path = new File("Source")
    }    
private void afficher (TableColumn<formation, Long> idcolumn,TableColumn<formation,String> titrecolumn,TableColumn<formation,String> categoriecolumn,TableColumn<formation,Double> prixcolumn,TableColumn<formation,Float> remisecolumn,TableColumn<formation,String> dureecolumn,TableColumn<formation,String> descriptioncolumn){
}
    @FXML
    private void Ajout(ActionEvent event) {
           try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFormationGui.fxml"));

            Parent root = loader.load();
            AddFormationGuiController aGF=loader.getController();
            //btnAjouter.getScene().setRoot(root);
            Stage newStage = new Stage();

        // Set the scene for the new stage
            Scene scene = new Scene(root);
            newStage.setScene(scene);

        // Show the new stage
            newStage.show();
            
        }catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
          
    }
    
    


    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void modif(ActionEvent event) throws IOException {
       /* formation selectedFormation = tableFormation.getSelectionModel().getSelectedItem();
       // Assuming you have a FXMLLoader set up for loading the "addformationgui.fxml" file
FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFormationGui.fxml"));
Parent root = loader.load();

// Access the controller of the new interface
AddFormationGuiController controller = loader.getController();

// Fill the fields in the new interface using the selectedFormation variable
controller.tfTitre.setText(selectedFormation.getTitre());
controller.tfCategorie.setText(selectedFormation.getCategorie());
controller.tfPrix.setText(String.valueOf(selectedFormation.getPrix()));
controller.tfRemise.setText(String.valueOf(selectedFormation.getRemise()));
controller.tfDuree.setText(selectedFormation.getDuree());
controller.tfDescription.setText(selectedFormation.getDescription());

// Show the new interface
Stage stage = new Stage();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();*/

        
        
        
        
        
        
        
        
    }

    
    

}


