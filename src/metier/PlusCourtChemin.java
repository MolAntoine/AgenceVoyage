/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import javax.persistence.EntityManager;
import modele.Gare;
import modele.TrajetUtilisateur;
import modele.Troncon;

/**
 *
 * @author Dober
 */
public class PlusCourtChemin {

    public PlusCourtChemin() {
    }
               
    public TrajetUtilisateur plusPetitTrajet(Gare depart, Gare arrive, Date dep, EntityManager em) {
    Requetes r = new Requetes();     
    // Liste des tronçons visités
    List<Troncon> visites = new ArrayList<>();
    // Map des tronçons avec leur distance minimale depuis la gare de départ
    Map<Troncon, Integer> distanceMinimale = new HashMap<>();
    // Map des tronçons avec le tronçon précédent sur le chemin de distance minimale depuis la gare de départ
    Map<Troncon, Troncon> tronconPrecedent = new HashMap<>();
    // File de priorité des tronçons à visiter (triée par distance minimale)
    PriorityQueue<Troncon> aVisiter = new PriorityQueue<>(new Comparator<Troncon>() {
        @Override
        public int compare(Troncon t1, Troncon t2) {
            return distanceMinimale.get(t1) - distanceMinimale.get(t2);
        }
    });

    // Initialisation
    for (Troncon troncon : r.getTronconsDuTrainDate(em, depart, dep)) {
        distanceMinimale.put(troncon, troncon.gettime());
        aVisiter.add(troncon);
    }

    // Algorithme de Dijkstra
    while (!aVisiter.isEmpty()) {
        Troncon tronconCourant = aVisiter.poll();
        visites.add(tronconCourant);

        // Si le tronçon courant est le tronçon d'arrivée, on a trouvé le plus court chemin
        if (tronconCourant.getGareArrivee().equals(arrive)) {
            break;
        }

        // Mise à jour des distances minimales des tronçons voisins
        for (Troncon tronconVoisin : r.getTronconsDuTrainDate(em, tronconCourant.getGareArrivee(),tronconCourant.getHeureDepart())) {
            int distanceTotale = distanceMinimale.get(tronconCourant) + tronconVoisin.gettime();
            if (!visites.contains(tronconVoisin)) {
                if (!distanceMinimale.containsKey(tronconVoisin) || distanceTotale < distanceMinimale.get(tronconVoisin)) {
                    distanceMinimale.put(tronconVoisin, distanceTotale);
                    tronconPrecedent.put(tronconVoisin, tronconCourant);
                    aVisiter.add(tronconVoisin);
                }
            }
        }
    }
     // Reconstruction du plus court chemin à partir des tronçons précédents
    List<Troncon> plusCourtChemin = new ArrayList<>();
    Troncon troncon = aVisiter.stream().filter(t -> t.getGareArrivee().equals(arrive)).findFirst().orElse(null);
    while (troncon != null) {
        plusCourtChemin.add(troncon);
        troncon = tronconPrecedent.get(troncon);
    }
    Collections.reverse(plusCourtChemin);
    TrajetUtilisateur tr = new TrajetUtilisateur(plusCourtChemin);
    return tr;
} 
}
