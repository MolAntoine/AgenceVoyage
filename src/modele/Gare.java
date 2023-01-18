/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Dober
 */

/**
 * Classe Gare qui contient les données d'une gare pour interagir avec la base de données.
 */


@Entity
public class Gare implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="GNOM",unique = true, nullable = false)
    private String nom;
    @Column(name="GVILLE",nullable = false)
    private String ville;
    @Column(name="GCODE",nullable = false)
    private int codepost;
    @Column(name="GLAT",nullable = false)
    private double lat;
    @Column(name="GLONG",nullable = false)
    private double lng;

    public Gare() {
    }

    
    
    public Gare(String nom, String ville, int codepost, double lat, double lng) {
        this.nom = nom;
        this.ville = ville;
        this.codepost = codepost;
        this.lat = lat;
        this.lng = lng;
    }
    
    

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nom);
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
        final Gare other = (Gare) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
   
    @Override
    public String toString() {
        return nom;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }




}
