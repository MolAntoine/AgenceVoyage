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

/**
 * Classe Requetes qui contient les requêtes nécessaires pour récupérer les données de la base de données.
 * Elle est utilisée pour récupérer les gares, les tronçons, les trains, etc. nécessaires pour l'algorithme génétique et l'UI.
 */

public class Requetes {

    EntityManager em;
    
        /**
     * Constructeur pour créer une instance de la classe Requetes
     * @param em L'EntityManager utilisé pour effectuer les requêtes
     */
            
    public Requetes(EntityManager em) {
        if(em != null) this.em = em;
    }
    
     /**
     * Méthode pour récupérer toutes les gares de la base de données
     * @return La liste de toutes les gares
     */
    
    public List<Gare> getGares(){
        final String strQuery = "SELECT g FROM Gare g ";
        Query query = em.createQuery(strQuery);
        return query.getResultList();
    }
    
     /**
     * Méthode pour récupérer une gare à partir de son nom
     * @param nom Le nom de la gare à récupérer
     * @return La gare correspondant au nom donné
     */
    
    public Gare getGareDepuisNom(String nom){
        final String strQuery = "SELECT g FROM Gare g "
                                + "WHERE g.nom = :nom ";
        
        Query query = em.createQuery(strQuery);
        query.setParameter("nom", nom);
        return (Gare) query.getSingleResult();
    }
    
     /**
     * Méthode pour récupérer un tronçon à partir de son ID
     * @param id L'ID du tronçon à récupérer
     * @return Le tronçon correspondant à l'ID donné
     */
    
    public Troncon getTronconById(int id){
        final String strQuery = "SELECT t FROM Troncon t WHERE t.id = :id";
        Query query = em.createQuery(strQuery);
        query.setParameter("id", id);
        return (Troncon) query.getSingleResult();
    }
    
    /**
    * Méthode pour récupérer une gare à partir de son ID
    * @param id L'ID de la gare à récupérer
    * @return La gare correspondant à l'ID donné
    */
    
    public Gare getGareById(int id){
        final String strQuery = "SELECT g FROM Gare g WHERE g.id = :id";
        Query query = em.createQuery(strQuery);
        query.setParameter("id", id);
        return (Gare) query.getSingleResult();
    }
    
    /**
    * Méthode pour récupérer le nombre de gares dans la base de données
    * @return Le nombre de gares
    */
    
    public int getNbGare(){
        String jpql = "SELECT COUNT(g) FROM Gare g";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
    
    /**
    * Méthode pour récupérer le nombre de troncons dans la base de données
    * @return Le nombre de troncons
    */
    
    public int getNbTroncons(){
        String jpql = "SELECT COUNT(t) FROM Troncon t";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
    
    /**
    * Méthode pour récupérer les tronçons d'un train à partir d'une gare et d'une date donnée
    * @param gare La gare de départ des tronçons
    * @param date La date de départ des tronçons
    * @return La liste des tronçons correspondant aux critères donnés
    */
    
    public List<Troncon> getTronconsDuTrainDate(Gare gare, Date date) {
        final String strQuery = "SELECT t FROM Troncon t WHERE t.gareDepart = :gare AND t.heureDepart >= :date";
        Query query = em.createQuery(strQuery);
        query.setParameter("gare", gare);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
     /**
    * Méthode pour récupérer les tronçons 
    * @return La liste des tronçons
    */   
    
    public List<Troncon> getTroncons() {
        final String strQuery = "SELECT t FROM Troncon t";
        Query query = em.createQuery(strQuery);
        return query.getResultList();
    }
    
        /**
    * Méthode pour récupérer les tronçons d'un train à partir d'une date donnée avec la date et les heures
    * @param date La date de départ des tronçons des trains
    * @return La liste des tronçons correspondant aux critères donnés
    */

    
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

    
    
            /**
    * Méthode pour récupérer un utilisateur à partir de son MDP et de son login
    * @param login le login de l'utilisateur
    * @param mdp le mot de passe de l'utilisateur
    * @return L'utilisateur si il existe null sinon
    */

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
    /**
    * Méthode pour ajouter un utilisateur à la BDD
    * @param u l'utilisateur
    */
    
    public void addUtilisateur(Utilisateur u){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(u);
        et.commit();
    }
        /**
    * Méthode pour obtenir les trajets utilisateurs d'un utilisateur dans la BDD
    * @param u l'utilisateur
    * @return La liste des trajets de l'utilisateur
    */
    
    public List<TrajetUtilisateur> getTrajetUtilisateur(Utilisateur u){
        final String strQuery = "SELECT u.trajetsUser from Utilisateur u"
                                + " WHERE u = :u";
        
        Query query = em.createQuery(strQuery);
        query.setParameter("u", u);
        return query.getResultList();
    }
        /**
    * Méthode pour ajouter un ajouter/supprimer un trajetUtilisateur d'un utilisateur dans la BDD
    * @param u l'utilisateur
    */
    public void addTrajetUtilisateur(Utilisateur u){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(u);
        et.commit();
    }
    public List<Utilisateur> getUtilisateurs(){
        final String strQuery = "SELECT u from Utilisateur u";
        Query query = em.createQuery(strQuery);
        return query.getResultList();
    }
    
}
