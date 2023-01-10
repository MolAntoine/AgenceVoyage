
package metier;
import static java.lang.Math.abs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modele.Gare;
import modele.TrajetUtilisateur;
import modele.Troncon;



public class AlgoGenetique {
    private static final int pop_size = 8000;
    private final double mut_rate = 0.2; 
    private final int nb_loop = 100;
    private static final Random random = new Random();
    private List<Troncon> data;
    private List<Troncon> dataGareDep;
    EntityManager em;
    Requetes re;

    public AlgoGenetique(EntityManager em) {
        this.em = em;
        re = new Requetes();
     
    }
    


    public TrajetUtilisateur trouverCheminCourt(Gare depart, Gare arrivee, Date dep,int nbmaxch,double coeftemps,double coefcout) throws ParseException {

        data = re.getTronconsDate(em,dep);
        dataGareDep = new ArrayList<>();
        for(Troncon t : data){
            if(t.getGareDepart().equals(depart)){
                dataGareDep.add(t);
            }
        }
        
        
        
        List<Individu> pop = new ArrayList<>(); 
        
        for(int i = 0;i<pop_size;i++){
            pop.add(creerIndAlea(depart,nbmaxch));
        }
        
        for (int i = 0; i < nb_loop; i++) {
        // Évaluation de la population
        for (Individu ind : pop) {
            ind.setFitness(evaluerIndividu(ind, depart, arrivee, dep,coeftemps,coefcout));
           // System.out.println(String.valueOf(ind.getFitness()));
        };
        // Sélection des individus les plus aptes
        // Croisement des individus sélectionnés pour créer une nouvelle génération
        // Mutation de quelques individus de la nouvelle génération
        // Remplacement de la population par la nouvelle génération
        pop = muter(croiser(selectionner(pop)));        
    }
       List<Individu> selection = selectionner(pop);
       List<Troncon> trs = new ArrayList<>();
       for(Individu ind : selection){
       if(verifierTroncons(ind.getTr(),arrivee,depart)){
       for(Troncon t : ind.getTr()){
           trs.add(t);
           if(t.getGareArrivee().equals(arrivee)){
               break;
           }
       }
       break;
       }
       }
        
      TrajetUtilisateur trajet = new TrajetUtilisateur(trs);
      return trajet;
    }
    
    
private List<Individu> croiser(List<Individu> parents) {
    List<Individu> enfants = new ArrayList<>();

    // Vérification qu'il y a au moins deux individus dans la liste
    if (parents.size() >= 2) {
        // Croisement de chaque paire d'individus de la liste
        for (int i = 0; i < parents.size(); i += 2) {
            // S'il reste au moins deux individus dans la liste, on effectue le croisement
            if (i + 1 < parents.size()) {
                Individu parent1 = parents.get(i);
                Individu parent2 = parents.get(i + 1);

                // Génération d'un point de croisement aléatoire
                int pointCroisement = random.nextInt(parent1.getTr().size());

                // Création d'un nouvel individu à partir des tronçons des deux parents
                List<Troncon> troncons = new ArrayList<>();
                troncons.addAll(parent1.getTr().subList(0, pointCroisement));
                troncons.addAll(parent2.getTr().subList(pointCroisement, parent2.getTr().size()));
                Individu enfant = new Individu(troncons);
                enfants.add(enfant);
                enfants.add(enfant);
                enfants.add(enfant);
                enfants.add(enfant);
            } else {
                // S'il ne reste qu'un individu dans la liste, on le copie directement dans la liste des enfants
                enfants.add(parents.get(i));
                enfants.add(parents.get(i));
                enfants.add(parents.get(i));
                enfants.add(parents.get(i));
            }
        }
    }

    return enfants;
}
    
     public List<Individu> muter(List<Individu> input) {
         List<Individu> output = new ArrayList<>();
         for(Individu ind : input){
             List<Troncon> ttemp = new ArrayList<>();
             for(Troncon t : ind.getTr()){
                 if(random.nextInt()%(int)(1.0/mut_rate) == 0){
                   ttemp.add(data.get(random.nextInt(data.size())));
                 }
                 else{
                     ttemp.add(t);
                 }
             }
             Individu temp = new Individu(ttemp);
             output.add(temp);
         }
     return output;
     }
    
    
    
    
    
    
    public List<Individu> selectionner(List<Individu> pop) {
    List<Individu> selection = new ArrayList<>();

    // Tri de la population en fonction du score des individus
    pop.sort((i1, i2) -> Double.compare(i2.getFitness(), i1.getFitness()));

    // Sélection de la moitié des individus les mieux adaptés
    for (int i = 0; i < pop.size() / 2; i++) {
        selection.add(pop.get(i));
    }

    return selection;
}
    
    
    
    public Individu creerIndAlea(Gare depart,int nbmaxstep){
       
        
        List<Troncon> trs;
        trs = new ArrayList<>();
        
        for(int i = 0;i<nbmaxstep;i++){
           Troncon t = data.get(random.nextInt(data.size()));
           trs.add(t);
        }
       trs.set(0,dataGareDep.get(random.nextInt(dataGareDep.size())));
       Individu t = new Individu(trs);
       return t;
    }

public double evaluerIndividu(Individu ind, Gare depart, Gare arrivee, Date dep,double coeftemps,double coefcout) {
    double score = 0;

    // Conversion de l'heure de départ en minutes
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
    String heureEtMinutes = dateFormat.format(dep);
    int heured = Integer.parseInt(heureEtMinutes.split(":")[0]);
    int minutesd = Integer.parseInt(heureEtMinutes.split(":")[1]);
    int timedep = heured * 60 + minutesd;

    
    
    if(!ind.getTr().get(0).getGareDepart().equals(depart) || (timedep > ind.getTr().get(0).getTimeDep())){
        return 0;
    }
    
    
    // Calcul du score en fonction de la durée du trajet
    Gare garePrec = depart;
    List<Gare> visited = new ArrayList<>();
    for (Troncon troncon : ind.getTr()) {
        // Si la gare de départ du tronçon courant est la gare précédente, le tronçon est valide
        if(!visited.contains(troncon.getGareArrivee())){
            if(troncon.getGareDepart().equals(garePrec) && troncon.getTimeDep()>=timedep){

            score+=1000/(1+coeftemps*(troncon.getTimeDep()-timedep));
            score+=1000/(1+coefcout*troncon.getPrix());
            }
            score+=1;
        }
        timedep += troncon.getTime();
        garePrec = troncon.getGareArrivee();
        visited.add(troncon.getGareArrivee());
    }
    if(verifierTroncons(ind.getTr(),arrivee,depart)){
      score+=400;
    }
    return score;
}  


public boolean verifierTroncons(List<Troncon> troncons,Gare arrivee,Gare depart) {
    // Initialize a variable to keep track of the previous troncon
    Troncon prevTroncon = troncons.get(0);
    if(!prevTroncon.getGareDepart().equals(depart)){
        return false;
    }
    // Loop through the rest of the troncons in the list
    for (int i = 1; i < troncons.size(); i++) {
        Troncon currentTroncon = troncons.get(i);
        
        // If the current troncon is not connected to the previous troncon, return false
        if (!currentTroncon.getGareDepart().equals(prevTroncon.getGareArrivee())) {
            return false;
        }
        
        // If the arrival time of the previous troncon is after the departure time of the current troncon, return false
        if (prevTroncon.getHeureArrivee().after(currentTroncon.getHeureDepart())) {
            return false;
        }
        if(currentTroncon.getGareArrivee().equals(arrivee)){
            return true;
        }
        
        // Update the previous troncon
        prevTroncon = currentTroncon;
    }
    
    // If all troncons are connected and the times are logical, return true
    return false;
}

}