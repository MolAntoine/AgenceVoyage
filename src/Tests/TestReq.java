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
import metier.Individu;
import metier.Requetes;
import modele.Gare;
import modele.Troncon;

/**
 *
 * @author komaeda
 */
public class TestReq {
    public static void main(String[] args) throws ParseException, InterruptedException {
        Requetes r = new Requetes();
        Fichier fGares = new Fichier("gares_test.txt");
        Fichier fTrajets = new Fichier("trajets_test.txt");
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        final EntityManager em = emf.createEntityManager();

        Gare g1 = r.getGareDepuisNom(em, "le mans");
        Gare g2 = r.getGareDepuisNom(em, "nancy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy:HH:mm");
        Date d2 = simpleDateFormat.parse("22:11:2022:07:00");
        List<Troncon> tr = r.getTronconsDate(em, d2);
        
    }
}
