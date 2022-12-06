package metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.Gare;
import modele.TrajetHoraire;
import modele.Troncon;


/**
 *
 * @author komaeda
 */
public class Fichier {
    private String nomFichier;

    public Fichier() {
        this.nomFichier="test";
    }

    public Fichier(String nomFichier) {
        this();
        if(nomFichier!=null && !nomFichier.trim().isEmpty()) this.nomFichier = nomFichier;
    }
    
    
    
    
    
    public void traiterFicGares(EntityManager em) throws IOException {
        Path path = Paths.get("./FichiersTests/" + this.nomFichier);

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();

        String[] args = line.split("\t");
        int nb = Integer.valueOf(args[1]);
        
        for(int i=0;i<nb;i++){
            line = reader.readLine();
            args = line.split("\t");
            em.persist(new Gare(args[0],args[1],Integer.valueOf(args[2]),Double.valueOf(args[3]),Double.valueOf(args[4])));
        }
 
    }
    
    
    
    
    
    public void traiterFicTrajets(EntityManager em) throws IOException, ParseException {
        Path path = Paths.get("FichiersTests/" + this.nomFichier);
        
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        
        final String strQuery = "SELECT g FROM Gare g "
                                + "WHERE g.nom = :nom ";
        Query query = em.createQuery(strQuery);

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        String[] args;
        args = line.split("\t");
        
        Date trainDate;
        int noTrain;
        Gare gareOrigine;
        Date hOrigine;
        
        Gare gareFinTronçon;
        Date hFinTronçon;
                
        while(line != null && args[0].equals("DebutTrajet")){
            line = reader.readLine();
            args = line.split("\t");

            trainDate = simpleDateFormat2.parse(args[0]);

            noTrain = Integer.valueOf(args[1]);

            query.setParameter("nom", args[2]);
            gareOrigine = (Gare) query.getSingleResult();
            
            hOrigine = simpleDateFormat1.parse(args[3]);

            query.setParameter("nom", args[4]);
            final Gare gareFinTrajet = (Gare) query.getSingleResult();

            final Date hFinTrajet = simpleDateFormat1.parse(args[5]);

            line = reader.readLine();
            args = line.split("\t");
            
            query.setParameter("nom", args[0]);
            gareFinTronçon = (Gare) query.getSingleResult();
            
            
            List<Troncon> lt= new ArrayList<>();
            while(line != null && !args[0].equals("FinTrajet")){
                if(gareFinTronçon.equals(gareFinTrajet)){
                    //em.persist();
                    lt.add(new Troncon(gareOrigine,gareFinTrajet,Double.valueOf(args[1]),hOrigine,hFinTrajet));
                }
                else{
                    hFinTronçon = simpleDateFormat1.parse(args[1]);

                    //em.persist();
                    lt.add(new Troncon(gareOrigine,gareFinTronçon,Double.valueOf(args[3]),hOrigine,hFinTronçon));
                    hOrigine = simpleDateFormat1.parse(args[2]);
                    gareOrigine = gareFinTronçon;

                }
                line = reader.readLine();
                if(line!= null) {
                    args = line.split("\t");
                    if(!args[0].equals("FinTrajet")){
                        query.setParameter("nom", args[0]);
                        gareFinTronçon = (Gare) query.getSingleResult(); 
                    }
                }
            }
            em.persist(new TrajetHoraire(trainDate,noTrain,lt));
            line = reader.readLine();
            if(line!= null) args = line.split("\t");
        }
  
        
    }

    public String getNomFichier() {
        return nomFichier;
    }
    
    
    
    
    
    
}
