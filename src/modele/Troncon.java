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
    private Gare garedepart;
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE })
    @JoinColumn(name = "GARRIV")
    private Gare gerarrivee;
    @Column(name = "PRIX",nullable = false)
    private double prix;
    @Temporal(TemporalType.TIME)
    @Column(name="TDDEP", nullable = false)
    private Date datedepart;
    @Temporal(TemporalType.TIME)
    @Column(name="TDARR", nullable = false)
    private Date datearrivee;

    
    public Troncon(Gare garedepart, Gare gerarrivee, double prix, Date datedepart, Date datearrivee) {
        this.garedepart = garedepart;
        this.gerarrivee = gerarrivee;
        this.prix = prix;
        this.datedepart = datedepart;
        this.datearrivee = datearrivee;
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
        return "Troncon{" + "id=" + id + ", garedepart=" + garedepart + ", gerarrivee=" + gerarrivee + ", prix=" + prix + ", datedepart=" + datedepart + ", datearrivee=" + datearrivee + '}';
    }

  

}
