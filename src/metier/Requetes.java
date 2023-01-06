/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Gare;
import modele.Troncon;

/**
 *
 * @author komaeda
 */
public class Requetes {

    public Requetes() {
    }
    
    public List<Gare> getGares(EntityManager em){
        final String strQuery = "SELECT g FROM Gare g ";
        Query query = em.createQuery(strQuery);
        return query.getResultList();
    }
    public Gare getGareDepuisNom(EntityManager em, String nom){
        final String strQuery = "SELECT g FROM Gare g "
                                + "WHERE g.nom = :nom ";
        
        Query query = em.createQuery(strQuery);
        query.setParameter("nom", nom);
        return (Gare) query.getSingleResult();
    }
    public List<Troncon> getTronconsDuTrain(EntityManager em, Gare gare){
        final String strQuery = "SELECT t from Troncon t "
                                + "WHERE t.gareDepart = :gare";
        
        Query query = em.createQuery(strQuery);
        query.setParameter("gare", gare);
        return query.getResultList();
    }
    
    public boolean checkUtilisateur(EntityManager em, String login, String mdp) {
        final String strQuery = "SELECT u from Utilisateur u "
                                + "WHERE u.login = :login "
                                + "AND u.mdp = :mdp";
        Query query = em.createQuery(strQuery);
        query.setParameter("login",login);
        query.setParameter("mdp",mdp);
        
        
        return !(query.getResultList().isEmpty());
    }
    
}
