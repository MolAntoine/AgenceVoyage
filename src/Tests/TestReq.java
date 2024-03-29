/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import metier.Fichier;
import metier.Requetes;
import modele.Gare;
import modele.Troncon;
import modele.Utilisateur;

/**
 *
 * @author komaeda
 */
public class TestReq {
    public static void main(String[] args) throws ParseException, InterruptedException {
        Fichier fGares = new Fichier("gares_test.txt");
        Fichier fTrajets = new Fichier("trajets_test.txt");
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        final EntityManager em = emf.createEntityManager();
        Requetes r = new Requetes(em);

        Gare g1 = r.getGareDepuisNom("le mans");
        Gare g2 = r.getGareDepuisNom("nancy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy:HH:mm");
        Date d2 = simpleDateFormat.parse("22:11:2022:15:00");
        List<Troncon> tr = r.getTronconsDate(d2);
        SimpleDateFormat simpleDateFormatnaissance = new SimpleDateFormat("dd/MM/yyyy");
        Date naissance = simpleDateFormatnaissance.parse("05/11/2002");
        Utilisateur u = new Utilisateur("test","test","Gressier","Louis","ceci est une adresse",naissance, true,null);
        r.addUtilisateur(u);
        
        
    }
}
