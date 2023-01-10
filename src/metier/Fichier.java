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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import modele.Gare;
import modele.Train;
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
        //on vérifie que nomFichier existe et n'est pas vide
        if(nomFichier!=null && !nomFichier.trim().isEmpty()) this.nomFichier = nomFichier;
    }
    
    
    
    
    //traitement des fichiers de gares
    public void traiterFicGares(EntityManager em) throws IOException {
        Path path = Paths.get("./FichiersTests/" + this.nomFichier); //chemin du fichier

        //instanciation du lecteur de fichier
        BufferedReader reader = Files.newBufferedReader(path);
        //lecture de la première ligne ("NbGares {Nombre de gares}")
        String line = reader.readLine();
        String[] args = line.split("\t");
        int nb = Integer.valueOf(args[1]); //nombre de gares à ajouter dans la bdd
        
        for(int i=0;i<nb;i++){
            line = reader.readLine();
            args = line.split("\t");
            
            //ajout dans la bdd
            em.persist(new Gare(args[0],args[1],Integer.valueOf(args[2]),Double.valueOf(args[3]),Double.valueOf(args[4])));
        }
 
    }
    
    
    
    
    //traitement des fichiers de trajets
    public void traiterFicTrajets(EntityManager em) throws IOException, ParseException {      
        //INITIALISATION
        
        Path path = Paths.get("FichiersTests/" + this.nomFichier); //chemin du fichier
        
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm"); //format de l'heure de passage
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy"); //format de la date de passage
        
        Map<String,Gare> mg = this.getMapGares(em); //Structure qui contient toutes les gares présentes dans la bdd
        
        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        String[] args;
        args = line.split("\t");
        
        Date trainDate; //date de circulation
        int noTrain; //numéro du train
        Gare gareOrigine; //gare de départ d'un tronçon
        Date hOrigine; //heure de départ d'un tronçon
        
        Gare gareFinTronçon; //gare de fin d'un tronçon
        Date hFinTronçon; //heure d'arrivée d'un tronçon
                
        Gare gareDebutTrajet=null; // gare de départ d'un trajet (utilisé pour diminuer le temps du traitement)
        Gare gareFinTrajet=null; //gare d'arrivée d'un trajet
        
        //TRAITEMENT
        
        //la boucle s'exécute tant que la ligne existe et qu'elle désigne le début d'un trajet
        while(line != null && args[0].equals("DebutTrajet")){
            //TRAITEMENT DE LA PREMIERE LIGNE
            
            line = reader.readLine();
            args = line.split("\t");

            trainDate = simpleDateFormat2.parse(args[0]); //première colonne de la première ligne du trajet

            noTrain = Integer.valueOf(args[1]); //deuxième colonne de la première ligne du trajet
            
            //si la gare de départ du trajet est la même que celle du trajet précédent
            //on ne va pas chercher dans le HashMap la gare
            if(gareDebutTrajet == null || !gareDebutTrajet.getNom().equals(args[2])){   
                gareDebutTrajet = mg.get(args[2]); //troisième colonne de la première ligne du trajet
            }
            gareOrigine = gareDebutTrajet; //gare de départ du premier tronçon du trajet
            
            hOrigine = simpleDateFormat1.parse(args[3]); //quatrième colonne de la première ligne du trajet

            //si la gare d'arrivée du trajet est la même que celle du trajet précédent
            //on ne va pas chercher dans le HashMap la gare
            if(gareFinTrajet == null || !gareFinTrajet.getNom().equals(args[4])){
                gareFinTrajet = mg.get(args[4]); //cinquième colonne de la première ligne du trajet
            }

            final Date hFinTrajet = simpleDateFormat1.parse(args[5]); //sixième colonne de la première ligne du trajet

            //si on sait que c'est un trajet direct 
            //on sait que la gare d'arrivée du trajet est la gare d'arrivée du premier tronçon
            if(Integer.valueOf(args[6])>1){
                line = reader.readLine();
                args = line.split("\t");
            
                gareFinTronçon = mg.get(args[0]);
            }
            else {
                line = reader.readLine();
                args = line.split("\t");
                gareFinTronçon = gareFinTrajet;
            }
            
            //TRAITEMENT DES TRONCONS
            
            List<Troncon> lt= new ArrayList<>(); //liste des tronçons du trajet
            while(line != null && !args[0].equals("FinTrajet")){
                //ajout du tronçon à la liste de tronçons
                //traitement différent s'il s'agit du dernier tronçon
                if(gareFinTronçon.equals(gareFinTrajet)) lt.add(new Troncon(gareOrigine,gareFinTrajet,Double.valueOf(args[1]),hOrigine,hFinTrajet));
                else{
                    hFinTronçon = simpleDateFormat1.parse(args[1]);
                    lt.add(new Troncon(gareOrigine,gareFinTronçon,Double.valueOf(args[3]),hOrigine,hFinTronçon));
                    
                    hOrigine = simpleDateFormat1.parse(args[2]); //heure de départ du tronçon suivant
                    gareOrigine = gareFinTronçon; //gare de départ du tronçon suivant
                }
                
                line = reader.readLine();
                if(line!= null) {
                    //si ce n'est pas la fin du trajet
                    //on regarde la gare de fin du tronçon suivant
                    if(!args[0].equals("FinTrajet")){
                        args = line.split("\t");
                        gareFinTronçon = mg.get(args[0]);
                    }
                }
            }
            em.persist(new Train(trainDate,noTrain,lt)); //ajout du train dans la bdd
            line = reader.readLine();
            if(line!= null) args = line.split("\t");
        }   
    }

    //fait la requête de toutes les gares et les met dans un HashMap 
    //pour éviter de faire des requêtes à la bdd dans une boucle
    private Map<String,Gare> getMapGares(EntityManager em){
        Requetes r = new Requetes();
        
        Map<String,Gare> mg = new HashMap<>();
        List<Gare> resList = r.getGares(em);
        for(Gare g : resList){
            mg.put(g.getNom(), g);
        }
        return mg;
    }
    
    public String getNomFichier() {
        return nomFichier;
    }
}
