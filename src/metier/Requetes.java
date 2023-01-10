/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Gare;
import modele.Train;
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
    
    public Troncon getTronconById(EntityManager em,int id){
        final String strQuery = "SELECT t FROM Troncon t WHERE t.id = :id";
        Query query = em.createQuery(strQuery);
        query.setParameter("id", id);
        return (Troncon) query.getSingleResult();
    }
    public Gare getGareById(EntityManager em,int id){
        final String strQuery = "SELECT g FROM Gare g WHERE g.id = :id";
        Query query = em.createQuery(strQuery);
        query.setParameter("id", id);
        return (Gare) query.getSingleResult();
    }
    public int getNbGare(EntityManager em){
        String jpql = "SELECT COUNT(g) FROM Gare g";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
    public int getNbTroncons(EntityManager em){
        String jpql = "SELECT COUNT(t) FROM Troncon t";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
    
    public List<Troncon> getTronconsDuTrainDate(EntityManager em, Gare gare, Date date) {
        final String strQuery = "SELECT t FROM Troncon t WHERE t.gareDepart = :gare AND t.heureDepart >= :date";
        Query query = em.createQuery(strQuery);
        query.setParameter("gare", gare);
        query.setParameter("date", date);
        return query.getResultList();
    }
    public List<Troncon> getTroncons(EntityManager em) {
        final String strQuery = "SELECT t FROM Troncon t";
        Query query = em.createQuery(strQuery);
        return query.getResultList();
    }
    public List<Troncon> getTronconsDate(EntityManager em,Date date) throws ParseException {
        final String strQuery = "SELECT tr FROM Train tr WHERE tr.circ = :date";
        Query query = em.createQuery(strQuery);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        Date formattedDate = dateFormat.parse(dateFormat.format(date));
        Date formattedTime = timeFormat.parse(timeFormat.format(date));
              
        query.setParameter("date", formattedDate);
               
        List<Troncon> trs = new ArrayList<Troncon>();
        List<Train> trains = (List<Train>)query.getResultList();
        for(Train tr : trains){
            for(Troncon t : tr.getTrajet()){
                if(t.getHeureDepart().after(formattedTime)){
                    trs.add(t);
                }
            }
            
        }
        return trs;
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
