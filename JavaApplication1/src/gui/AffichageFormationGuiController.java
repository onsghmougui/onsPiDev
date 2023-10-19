/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import connection.MyConnection;
import formations.formation;
import formations.formationServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author ghmougui ons
 */
public class AffichageFormationGuiController implements Initializable {

    @FXML
    private AnchorPane AnchorOrder;
    @FXML
    private GridPane menu_gridPane;
    @FXML
    private Button poteriebtn;
    @FXML
    private Button tapisseriebtn;
    @FXML
    private Button bijouxbtn;
    @FXML
    private Button cuisinebtn;
    
    private ObservableList<formation> cardListData=FXCollections.observableArrayList();
    formationServices fs=new formationServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //refreshOrder();
        try {
            menuDisplayCard();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageFormationGuiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AffichageFormationGuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
       
   
   
   }
                // TODO
            


public void menuDisplayCard() throws SQLException, IOException {
    int column = 0;
    int row = 0;
    
    cardListData.clear();
    cardListData.addAll(fs.retournerTout());

    for (formation f : cardListData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cardCom.fxml"));
            AnchorPane pane = loader.load();
            CardComController CCC = loader.getController();

            // Assuming getId_pdts() is the method to retrieve the product ID
            CCC.setData(f.getId());
            if (column == 3) {
                column = 0;
                row += 1;
                
            }
            menu_gridPane.add(pane, column++, row);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


  
  
  public formation getformationByID(int id) throws SQLException{
         MyConnection conx= MyConnection.getInstance();
        Connection myConx=conx.getConnection();
        String req="SELECT * FROM formation WHERE id= ?";
    
    
        PreparedStatement prepStat = myConx.prepareStatement(req);
         prepStat.setInt(1, id);
         
        ResultSet resultSet = prepStat.executeQuery();

formation formResult = null;

if (resultSet.next()) {
  
    String titre = resultSet.getString("titre");
    String categories = resultSet.getString("categories");
    double prix = resultSet.getDouble("prix");
    float remise = resultSet.getFloat("remise");
    String duree = resultSet.getString("duree");
    String description = resultSet.getString("description");
    String video = resultSet.getString("video");
  
    formResult = new formation(titre, categories, prix, remise, duree, description, video);
}

// Close the result set and the prepared statement
resultSet.close();
prepStat.close();

return formResult;
    
    
    }
    
  
  
  
  
  
  
  
 /* public List<Long> getAllFormationIds() {
    List<Long> formationIds = new ArrayList<>();
    try {
        // Establish a database connection and execute a query to fetch all IDs
        MyConnection conx= MyConnection.getInstance();
        String query = "SELECT id FROM formation"; 
        Statement statement = conx.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Long id = resultSet.getLong("idPdts");
            formationIds.add(id);
        }

        // Close resources
        resultSet.close();
        statement.close();
        conx.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return formationIds;
}*/
    


    

    @FXML
    private void poterie(ActionEvent event) {
    }

    

    @FXML
    private void bijoux(ActionEvent event) {
    }

    @FXML
    private void cuisine(ActionEvent event) {
    }

   
    @FXML
    private void tapisserie(ActionEvent event) {
    }
    
}
