/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentaires;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ghmougui ons
 */
public class commentaire {
    private long id;
    private String text;
    private String utilisateur;
    private Date date;
    private String reponse;
    private String signalement;

    public commentaire(int id, String text, String utilisateur, Date date, String reponse, String signalement) {
        this.id = id;
        this.text = text;
        this.utilisateur = utilisateur;
        this.date = date;
        this.reponse = reponse;
        this.signalement = signalement;
    }

    public commentaire() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getSignalement() {
        return signalement;
    }

    public void setSignalement(String signalement) {
        this.signalement = signalement;
    }

   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final commentaire other = (commentaire) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.utilisateur, other.utilisateur)) {
            return false;
        }
        if (!Objects.equals(this.reponse, other.reponse)) {
            return false;
        }
        if (!Objects.equals(this.signalement, other.signalement)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "commentaire{" + "text=" + text + ", utilisateur=" + utilisateur + ", date=" + date + ", reponse=" + reponse + ", signalement=" + signalement + '}';
    }
    
    
    

    
}
