/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import metier.Requetes;

/**
 *
 * @author Dober
 */
@Entity
public class TrajetUtilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="COUT", nullable = false)
    private double cout;
    @Column(name="TEMPS", nullable = false)
    private int temps;
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Troncon> trajet; 

    public TrajetUtilisateur() {
    }

    public TrajetUtilisateur(List<Troncon> trajet) {
        this.cout = 0;
        this.temps = 0;
        this.trajet = trajet;
    }
    
    public void calculate(){
        int tempsarr;
        
        this.cout = 0;
        this.temps = 0;
        if(!trajet.isEmpty()){
            for(Troncon t : trajet){
                cout+= t.getPrix();
            }
            String s = trajet.get(trajet.size()-1).getHeureArrivee().toString();
        
            String[] aux = (s.split(" "))[3].split(":");
            tempsarr = Integer.valueOf(aux[0]) * 60 + Integer.valueOf(aux[1]);
            s = trajet.get(0).getHeureDepart().toString();
            aux = (s.split(" "))[3].split(":");
            int tempsdep = Integer.valueOf(aux[0])*60 + Integer.valueOf(aux[1]);
            temps = tempsarr - tempsdep;
        
        
        }
    }
 
    
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrajetUtilisateur)) {
            return false;
        }
        TrajetUtilisateur other = (TrajetUtilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public double getCout() {
        return cout;
    }

    public int getTemps() {
        return temps;
    }

    @Override
    public String toString() {
        return "TrajetUtilisateur{" + "cout=" + cout + ", temps=" + temps + ", trajet=" + trajet + '}';
    }

    public List<Troncon> getTrajet() {
        return trajet;
    }

    
}
