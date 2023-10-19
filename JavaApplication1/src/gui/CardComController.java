/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import connection.MyConnection;
import formations.formation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ghmougui ons
 */
public class CardComController implements Initializable {

    @FXML
    private Label titreformation;
    @FXML
    private Label prixformation;
    @FXML
    private Button reserverbtn;
    @FXML
    private Button commenterbtn;
    @FXML
    private MediaView mediaView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reserver(ActionEvent event) {
    }

    @FXML
    private void commenter(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentaireGui.fxml"));

            Parent root = loader.load();
            CommentaireGuiController cG=loader.getController();
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
    public void setData(long id ) throws SQLException{
    formation f = getFormationByID(id);
    titreformation.setText(f.getTitre());
    prixformation.setText(String.valueOf(f.getPrix()));
   ;
    
    Media media = new Media(f.getVideo());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    // Assuming Pvideo is the name of your VideoView or MediaView
    mediaView.setMediaPlayer(mediaPlayer);

    // Play the video (you can add play/stop/pause controls if needed).
    mediaPlayer.play();
}
    
    public formation getFormationByID(long id) throws SQLException{
        MyConnection conx= MyConnection.getInstance();
        Connection myConx=conx.getConnection();
        String req="SELECT * FROM formation WHERE id= ?";
    
    
        PreparedStatement prepStat = myConx.prepareStatement(req);
         prepStat.setLong(1, id);
         
        ResultSet resultSet = prepStat.executeQuery();

        formation formationResult = null;

        if (resultSet.next()) {
  
        String titre = resultSet.getString("titre");
        String categories = resultSet.getString("categories");
        double prix = resultSet.getDouble("prix");
        float remise = resultSet.getFloat("remise");
        String duree = resultSet.getString("duree");
        String description = resultSet.getString("description");
        
        String video = resultSet.getString("video");
  
    formationResult = new formation(titre, categories, prix, remise, duree, description, video) ;
}
        return formationResult;
    
}
}
