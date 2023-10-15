/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formations;

import connection.MyConnection;
//import interfaceCrud.MyCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ghmougui ons
 */
public class formationServices {
    public formationServices(){
    }
    
    MyConnection conx= MyConnection.getInstance();
    Connection myConx=conx.getConnection();

    public int ajouter(formation t) {
        if(this.chercher(t)!=null)
            return -1; 
        
        String req="INSERT INTO `formation` ( `id`, `titre`, `categories`, `prix`,`remise`,`duree`,`description`)"
                    + " VALUES ( ?,?, ?,?,?,?,?);";
        try {
            PreparedStatement prepStat = myConx.prepareStatement(req);
            prepStat.setLong(1, t.getId());
            prepStat.setString(2, t.getTitre());
            prepStat.setString(3, t.getCategories());
            prepStat.setDouble(4, t.getPrix());
            prepStat.setFloat(5, t.getRemise());
            prepStat.setString(6, t.getDuree());
            prepStat.setString(7, t.getDescription());
            //prepStat.setString(8, t.getVideo());
            int rowsAffected =  prepStat.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return 0;
        
    }

    
    public formation chercher(formation t) {
        String req="SELECT * FROM `formation` WHERE `id` LIKE ? AND `titre` LIKE ? AND `categories` LIKE ? AND `prix` LIKE ? AND`remise` LIKE ? "
                + "AND`duree` LIKE ? AND `description` LIKE ?;";
        formation found = new formation();
        try {
            PreparedStatement prepStat = myConx.prepareStatement(req);
            prepStat.setLong(1, t.getId());
            prepStat.setString(2, t.getTitre());
            prepStat.setString(3, t.getCategories());
            prepStat.setDouble(4, t.getPrix());
            prepStat.setFloat(5, t.getRemise());
            prepStat.setString(6, t.getDuree());
            prepStat.setString(7, t.getDescription());
            //prepStat.setString(8, t.getVideo());
            
                      
                      
            ResultSet rS= prepStat.executeQuery();
            if(!rS.next())
                return null;
            found.setId(rS.getLong("id"));
            found.setTitre(rS.getString("titre"));
            found.setCategories(rS.getString("categories"));
            found.setPrix(rS.getDouble("prix"));
            found.setPrix(rS.getFloat("remise"));
            found.setDescription(rS.getString("duree"));
            found.setDescription(rS.getString("description"));
            //found.setVideo(rS.getString("video"));
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
        return found;
    }


    
    public int supprimer(formation t) {
        String req="DELETE FROM formation WHERE `formation`.`id` = ?;";
        try {
            PreparedStatement prepStat = myConx.prepareStatement(req);
            prepStat.setLong(1, t.getId());
            int rowsAffected =  prepStat.executeUpdate();
            if(rowsAffected==0)
                return -1;
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return 0;
        
    }

    
    public formation modifier(formation t, formation n) {
        String req = "UPDATE `formation` SET  `titre` = ?, `categories` = ?,`prix` = ? ,`remise` = ?, `duree` = ?,`description` = ? ,`video` = ?  WHERE `formation`.`id` = ?;";
        try {
            
            PreparedStatement prepStat = myConx.prepareStatement(req);
            prepStat.setLong(1, t.getId());
            prepStat.setString(2, n.getTitre());
            prepStat.setString(3, n.getCategories());
            prepStat.setDouble(4, n.getPrix());
            prepStat.setFloat(5, t.getRemise());
            prepStat.setString(6, t.getDuree());
            prepStat.setLong(7, t.getId());
            prepStat.setString(8, t.getDescription());
            prepStat.setString(9, t.getVideo());
 
            int rowsAffected =  prepStat.executeUpdate();
           
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return n;
    }
    public List<formation> retournerTout() {
        List<formation> retour= new ArrayList();
        String req ="select * from `formation` ";
        
        
        try {
            PreparedStatement prepStat = myConx.prepareStatement(req);
            ResultSet rS= prepStat.executeQuery();
            
            while(rS.next())
            {
            formation found= new formation();
            found.setId(rS.getLong("id"));
            found.setTitre(rS.getString("titre"));
            found.setCategories(rS.getString("categories"));
            found.setPrix(rS.getDouble("prix"));
            found.setPrix(rS.getFloat("remise"));
            found.setDescription(rS.getString("duree"));
            found.setDescription(rS.getString("description"));
            //found.setVideo(rS.getString("video"));
            retour.add(found);
                
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retour;
    }

    
    
}
