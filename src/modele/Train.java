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
public class Train implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name="DATECIR", nullable = false)
    private Date circ;
    @Column(name="NUMTR", nullable = false)
    private int numtrain;
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE })
    private List<Troncon> trajet; 

    public Train() {
    }

    public Train(Date circ, int numtrain, List<Troncon> trajet) {
        this.circ = circ;
        this.numtrain = numtrain;
        this.trajet = trajet;
    }
    
    
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Train other = (Train) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrajetHoraire{" + "id=" + id + ", circ=" + circ + ", numtrain=" + numtrain + ", trajet=" + trajet + '}';
    }



}
