/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentaires;

import connection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ghmougui ons
 */
public class commentaireServices {
    public commentaireServices(){
    }
    
   MyConnection conx= MyConnection.getInstance();
    Connection myConx=conx.getConnection();

    public int ajouter(commentaire c) {
        if(this.chercher(c)!=null)
            return -1; 
        
        String req="INSERT INTO `commentaire` ( `id`, `text, `utilisateur`, `date`,`reponse`,`signalement`)"
                    + " VALUES ( ?,?, ?,?,?,?);";
        try {
            PreparedStatement prepStat = myConx.prepareStatement(req);
            prepStat.setLong(1, c.getId());
            prepStat.setString(2, c.getText());
            prepStat.setString(3, c.getUtilisateur());
            prepStat.setDate(4, c.getDate());
            prepStat.setString(5, c.getReponse());
            prepStat.setString(6, c.getSignalement());
            int rowsAffected =  prepStat.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return 0;
        
    }

    
    public commentaire chercher(commentaire c ) {
        String req="SELECT * FROM `commentaire` WHERE `id` LIKE ? AND `text` LIKE ? AND `utilisateur` LIKE ? AND "
                + "`date` LIKE ? AND `reponse` LIKE ?`signalement` LIKE ? ;";
        commentaire found = new commentaire();
        try {
            PreparedStatement prepStat = myConx.prepareStatement(req);
            prepStat.setLong(1, c.getId());
            prepStat.setString(2, c.getText());
            prepStat.setString(3, c.getUtilisateur());
            prepStat.setDate(4, c.getDate());
            prepStat.setString(5, c.getReponse());
            prepStat.setString(6, c.getSignalement());
            
                      
                      
            ResultSet rS= prepStat.executeQuery();
            if(!rS.next())
                return null;
            found.setId( rS.getLong("id"));
            found.setText(rS.getString("text"));
            found.setUtilisateur(rS.getString("utilisateur"));
            found.setDate(rS.getDate("date"));
            found.setReponse(rS.getString("reponse"));
            found.setSignalement(rS.getString("signalement"));
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
        return found;
    }


    
    public int supprimer(commentaire c) {
        String req="DELETE FROM commentaire WHERE `commentaire`.`id` = ?;";
        try {
            PreparedStatement prepStat = myConx.prepareStatement(req);
            prepStat.setLong(1, c.getId());
            int rowsAffected =  prepStat.executeUpdate();
            if(rowsAffected==0)
                return -1;
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return 0;
        
    }

    
    public commentaire modifier(commentaire c, commentaire c1) {
        String req = "UPDATE `commentaire` SET  `text` = ?, `utilisateur` = ?,`date` = ? ,`reponse` = ? ,`signalement` = ?  WHERE `commentaire`.`id` = ?;";
        try {
            
            PreparedStatement prepStat = myConx.prepareStatement(req);
            prepStat.setLong(6, c.getId());
            prepStat.setString(1, c1.getText());
            prepStat.setString(2, c1.getUtilisateur());
            prepStat.setDate(3, c1.getDate());
            prepStat.setString(4, c1.getReponse());
            prepStat.setString(5, c1.getSignalement());
            
 
            int rowsAffected =  prepStat.executeUpdate();
           
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c1;
    }
    
    
}


