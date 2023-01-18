/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dober
 */
@Entity
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="LOGIN", nullable = false)
    String login;
    @Column(name="MDP", nullable = false)
    String mdp;
    @Column(name="NOM", nullable = false)
    String nom;
    @Column(name="PRENOM", nullable = false)
    String prenom;
    @Column(name="ADRE", nullable = false)
    String adresse;
    @Temporal(TemporalType.DATE)
    @Column(name="NAISS", nullable = false)
    Date naissance;
    @Column(name="ISAD", nullable = false)
    Boolean admin;
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<TrajetUtilisateur> trajetsUser; 
    
    
    

    public Utilisateur(String login, String mdp, String nom, String prenom, String adresse, Date naissance, Boolean admin,List<TrajetUtilisateur> trajetsUser) {
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.naissance = naissance;
        this.admin = admin;
        this.trajetsUser = trajetsUser;
    }
//    public Utilisateur(String login, String mdp, String nom, String prenom, String adresse, Date naissance, Boolean admin) {
//        this.login = login;
//        this.mdp = mdp;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.adresse = adresse;
//        this.naissance = naissance;
//        this.admin = admin;
//    }
    
    public Utilisateur() {
    }
    

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public List<TrajetUtilisateur> getTrajetsUser() {
        return trajetsUser;
    }
    public void addTrajetUser(TrajetUtilisateur e){
        trajetsUser.add(e);
    }
    public void removeTrajetUser(TrajetUtilisateur e){
        trajetsUser.remove(e);
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
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
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", login=" + login + ", mdp=" + mdp + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", naissance=" + naissance + ", admin=" + admin + '}';
    }


}
