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
            
 public void plusPetitPrix(Gare depart, Gare arrive, EntityManager em) {
    // Liste des gares visitées
    List<Gare> visitees = new ArrayList<>();
    // Map des gares avec leur distance minimale depuis la gare de départ
    Map<Gare, Integer> distanceMinimale = new HashMap<>();
    // Map des gares avec la gare précédente sur le chemin de distance minimale depuis la gare de départ
    Map<Gare, Gare> garePrecedente = new HashMap<>();
    // File de priorité des gares à visiter (triée par distance minimale)
    PriorityQueue<Gare> aVisiter = new PriorityQueue<>(new Comparator<Gare>() {
        @Override
        public int compare(Gare g1, Gare g2) {
            return distanceMinimale.get(g1) - distanceMinimale.get(g2);
        }
    });

    // Initialisation
    distanceMinimale.put(depart, 0);
    aVisiter.add(depart);

    // Algorithme de Dijkstra
    while (!aVisiter.isEmpty()) {
        Gare gareCourante = aVisiter.poll();
        visitees.add(gareCourante);

        // Si la gare courante est la gare d'arrivée, on a trouvé le plus court chemin
        if (gareCourante.equals(arrive)) {
            break;
        }

        // Mise à jour des distances minimales des gares voisines
        for (Troncon troncon : gareCourante.getTroncons()) {
            Gare gareVoisine = troncon.getGareArrivee();
            int distanceTotale = (int) (distanceMinimale.get(gareCourante) + troncon.getPrix());
            if (!visitees.contains(gareVoisine)) {
                if (!distanceMinimale.containsKey(gareVoisine) || distanceTotale < distanceMinimale.get(gareVoisine)) {
                    distanceMinimale.put(gareVoisine, distanceTotale);
                    garePrecedente.put(gareVoisine, gareCourante);
                    aVisiter.add(gareVoisine);
                }
            }
        }
    }

    // Reconstruction du plus court chemin à partir des gares précédentes
    List<Gare> plusCourtChemin = new ArrayList<>();
    Gare gare = arrive;
    while (gare != null) {
        plusCourtChemin.add(gare);
        gare = garePrecedente.get(gare);
    }
    Collections.reverse(plusCourtChemin);

  
    
    List<Troncon> troncons = new ArrayList<>();

    for (int i = 0; i < plusCourtChemin.size() - 1; i++) {
        Gare gareDepart = plusCourtChemin.get(i);
        Gare gareArrivee = plusCourtChemin.get(i + 1);
        Troncon troncon = null;
        for (Troncon t : gareDepart.getTroncons()) {
            if (t.getGareArrivee().equals(gareArrivee)) {
                troncon = t;
                break;
            }
        }
        troncons.add(troncon);
    }
    trajet = troncons;
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

    @Override
    public String toString() {
        return "modele.TrajetUtilisateur[ id=" + id + " ]";
    }
    
}
