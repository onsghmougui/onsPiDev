/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import connection.MyConnection;
import formations.formation;
import formations.formationServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnModifier;
    
    
    

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
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers MP4", "*.mp4"));
    File selectedFile = fileChooser.showOpenDialog(null);

    if (selectedFile != null) {
        String selectedFilePath = selectedFile.toURI().toString();
        selectedFilePath = selectedFilePath.replace(" ", "%20"); // Remplace les espaces par %20
        Media media = new Media(selectedFilePath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Associez le MediaPlayer au MediaView pour afficher la vidéo.
        mediaView.setMediaPlayer(mediaPlayer);

        // Jouez la vidéo (vous pouvez ajouter des contrôles de lecture/arrêt/pause si nécessaire).
        mediaPlayer.play();
    }
    }

    @FXML
    
    private void SavePerson(MouseEvent event) {
        try {
            String titre = tfTitre.getText();
            String categorie = tfCategorie.getText();
            double prix =Double.parseDouble(tfPrix.getText());
            float remise =Float.parseFloat(tfRemise.getText());
            String duree = tfDuree.getText();
            String description = tfDescription.getText();
            String video ;
            Media media = mediaView.getMediaPlayer().getMedia();
            String mediaSource = media.getSource();
            
            
            
            formationServices fs = new formationServices();
            if (fs.unicFormation(titre) == -1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Formation Title Not Unique");
            alert.setContentText("A Formation with the same name already exists. Please choose a different name.");
            alert.showAndWait();}
            else{
            formation f= new formation(titre, categorie, prix, remise, duree, description,mediaSource) ;
            fs.ajouter(f);}
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationGui.fxml"));
            Parent root = loader.load();
            FormationGuiController GF = loader.getController();

        // Configurez la nouvelle interface en tant que racine de la scène
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne la scène en fonction de son contenu
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
    
    
    
    
   
    public void remplirChamps(formation formation) {
        // Remplissez les champs avec les informations de la formation
        tfTitre.setText(formation.getTitre());
        tfCategorie.setText(formation.getCategories());
        tfPrix.setText(String.valueOf(formation.getPrix()));
        tfRemise.setText(String.valueOf(formation.getRemise()));
        tfDuree.setText(formation.getDuree());
        tfDescription.setText(formation.getDescription());
        String video = formation.getVideo();
        Media media = new Media(video);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Associez le MediaPlayer à la MediaView pour l'affichage de la vidéo
        mediaView.setMediaPlayer(mediaPlayer);
       
        // Remplissez d'autres champs en conséquence
    }
 MyConnection conx= MyConnection.getInstance();
    Connection myConx=conx.getConnection();
    @FXML
    private void Modifier(MouseEvent event) throws SQLException, IOException {
      formationServices fs = new formationServices();
      
       
        
       String titre = tfTitre.getText();
            String categorie = tfCategorie.getText();
            double prix =Double.parseDouble(tfPrix.getText());
            float remise =Float.parseFloat(tfRemise.getText());
            String duree = tfDuree.getText();
            String description = tfDescription.getText();
            String video ;
            Media media = mediaView.getMediaPlayer().getMedia();
            String mediaSource = media.getSource();
            formation f = new formation(titre, categorie, prix, remise, duree, description, mediaSource);
            formation nouveau = new formation(titre, categorie, prix, remise, duree, description, mediaSource);
            nouveau.setTitre(titre);
            nouveau.setCategories(categorie);
            nouveau.setPrix(prix);
            nouveau.setRemise(remise);
            nouveau.setDuree(duree);
            nouveau.setDescription(description);
            nouveau.setVideo(mediaSource);
            fs.modifier(f, nouveau);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationGui.fxml"));
            Parent root = loader.load();
            FormationGuiController GF = loader.getController();

        // Configurez la nouvelle interface en tant que racine de la scène
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.sizeToScene(); // Redimensionne la scène en fonction de son contenu
    }

   
}
            
             /*String query = "SELECT idtemp FROM temp WHERE id = ?";
                long id=1;long idTemp = -1;

    PreparedStatement prepStat = myConx.prepareStatement(query);
    prepStat.setLong(1, id); // Replace with the actual condition value

    ResultSet resultSet = prepStat.executeQuery();

    if (resultSet.next()) {
        idTemp = resultSet.getLong("idtemp");}
            formation f= new formation(idTemp,titre, categorie, prix, remise, duree, description,mediaSource) ;
             // Initialize the variable with a default value


    // Create a SQL query to retrieve the idtemp based on a condition (replace with your condition)
   
      fs.modifier(fs.getFormationById(idTemp), f);*/
  

    
    

    

