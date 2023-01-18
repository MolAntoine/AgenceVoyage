/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import Tests.Test2;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import modele.Gare;
import modele.Train;
import modele.TrajetUtilisateur;
import modele.Troncon;
import modele.Utilisateur;

/**
 *
 * @author komaeda
 */
public class Requetes {

    EntityManager em;
            
    public Requetes(EntityManager em) {
        if(em != null) this.em = em;
    }
    
    public List<Gare> getGares(){
        final String strQuery = "SELECT g FROM Gare g ";
        Query query = em.createQuery(strQuery);
        return query.getResultList();
    }
    public Gare getGareDepuisNom(String nom){
        final String strQuery = "SELECT g FROM Gare g "
                                + "WHERE g.nom = :nom ";
        
        Query query = em.createQuery(strQuery);
        query.setParameter("nom", nom);
        return (Gare) query.getSingleResult();
    }
    
    public Troncon getTronconById(int id){
        final String strQuery = "SELECT t FROM Troncon t WHERE t.id = :id";
        Query query = em.createQuery(strQuery);
        query.setParameter("id", id);
        return (Troncon) query.getSingleResult();
    }
    public Gare getGareById(int id){
        final String strQuery = "SELECT g FROM Gare g WHERE g.id = :id";
        Query query = em.createQuery(strQuery);
        query.setParameter("id", id);
        return (Gare) query.getSingleResult();
    }
    public int getNbGare(){
        String jpql = "SELECT COUNT(g) FROM Gare g";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
    public int getNbTroncons(){
        String jpql = "SELECT COUNT(t) FROM Troncon t";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
    
    public List<Troncon> getTronconsDuTrainDate(Gare gare, Date date) {
        final String strQuery = "SELECT t FROM Troncon t WHERE t.gareDepart = :gare AND t.heureDepart >= :date";
        Query query = em.createQuery(strQuery);
        query.setParameter("gare", gare);
        query.setParameter("date", date);
        return query.getResultList();
    }
    public List<Troncon> getTroncons() {
        final String strQuery = "SELECT t FROM Troncon t";
        Query query = em.createQuery(strQuery);
        return query.getResultList();
    }
    public List<Troncon> getTronconsDate(Date date) throws ParseException {
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
        System.out.println(trs.size());
        return trs;
    }
    public List<Troncon> getTronconsDuTrain(Gare gare){
        final String strQuery = "SELECT t from Troncon t "
                                + "WHERE t.gareDepart = :gare";
        
        Query query = em.createQuery(strQuery);
        query.setParameter("gare", gare);
        return query.getResultList();
    }


    public Utilisateur checkUtilisateur(String login, String mdp) {
        final String strQuery = "SELECT u from Utilisateur u "
                                + "WHERE u.login = :login "
                                + "AND u.mdp = :mdp";
        Query query = em.createQuery(strQuery);
        query.setParameter("login",login);
        query.setParameter("mdp",mdp);
        
        if (query.getResultList().isEmpty()) return null;
        else return (Utilisateur) query.getSingleResult();
    }
    public void addUtilisateur(Utilisateur u){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(u);
        et.commit();
    }
    public List<TrajetUtilisateur> getTrajetUtilisateur(Utilisateur u){
        final String strQuery = "SELECT u.trajetsUser from Utilisateur u"
                                + " WHERE u = :u";
        
        Query query = em.createQuery(strQuery);
        query.setParameter("u", u);
        return query.getResultList();
    }
    public void addTrajetUtilisateur(Utilisateur u){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(u);
        et.commit();
    }
    
}
