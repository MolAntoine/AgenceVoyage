/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

    public Gare getGareArrivee() {
        return gareArrivee;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public double getPrix() {
        return prix;
    }

    public Date getHeureDepart() {
        return heureDepart;
    }

    public Date getHeureArrivee() {
        return heureArrivee;
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
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String heureEtMinutes = dateFormat.format(heureDepart);
        int heured = Integer.parseInt(heureEtMinutes.split(":")[0]);
        int minutesd = Integer.parseInt(heureEtMinutes.split(":")[1]);
        heureEtMinutes = dateFormat.format(heureArrivee);
        int heurea = Integer.parseInt(heureEtMinutes.split(":")[0]);
        int minutesa = Integer.parseInt(heureEtMinutes.split(":")[1]);
        return "Troncon{" + "id=" + id + ", garedepart=" + gareDepart + ", gerarrivee=" 
                + gareArrivee + ", prix=" + prix 
                + ", heuredepart=" + Integer.toString(heured) +":" +Integer.toString(minutesd)
                + ", heurearrivee=" + Integer.toString(heurea) +":" +Integer.toString(minutesa) + '}';
    }

    
    public int gettimedep(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String heureEtMinutes = dateFormat.format(heureDepart);
        int heure = Integer.parseInt(heureEtMinutes.split(":")[0]);
        int minutes = Integer.parseInt(heureEtMinutes.split(":")[1]);
        return heure*60+minutes;
    }
    
    
    
    
    public Gare getGareDepart() {
        return gareDepart;
    }
    public int gettime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String heureEtMinutes = dateFormat.format(heureDepart);
        int heured = Integer.parseInt(heureEtMinutes.split(":")[0]);
        int minutesd = Integer.parseInt(heureEtMinutes.split(":")[1]);
        heureEtMinutes = dateFormat.format(heureArrivee);
        int heurea = Integer.parseInt(heureEtMinutes.split(":")[0]);
        int minutesa = Integer.parseInt(heureEtMinutes.split(":")[1]);
        return (heurea-heured)*60 +minutesa - minutesd;
    }
  

}
