/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dober
 */
@Entity
public class Troncon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE })
    @JoinColumn(name = "GARDEP")
    private Gare gareDepart;
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE })
    @JoinColumn(name = "GARRIV")
    private Gare gareArrivee;
    @Column(name = "PRIX",nullable = false)
    private double prix;
    @Temporal(TemporalType.TIME)
    @Column(name="TDDEP", nullable = false)
    private Date heureDepart;
    @Temporal(TemporalType.TIME)
    @Column(name="TDARR", nullable = false)
    private Date heureArrivee;

    
    public Troncon(Gare garedepart, Gare garearrivee, double prix, Date datedepart, Date datearrivee) {
        this.gareDepart = garedepart;
        this.gareArrivee = garearrivee;
        this.prix = prix;
        this.heureDepart = datedepart;
        this.heureArrivee = datearrivee;
    }

    public Troncon() {
    }
    
    
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Troncon other = (Troncon) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Troncon{" + "id=" + id + ", garedepart=" + gareDepart + ", gerarrivee=" + gareArrivee + ", prix=" + prix + ", datedepart=" + heureDepart + ", datearrivee=" + heureArrivee + '}';
    }

  

}
