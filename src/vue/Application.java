package vue;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;
import metier.*;
import modele.*;


/**
 *
 * @author komaeda
 */
public class Application extends javax.swing.JFrame {
//    private final EntityManagerFactory emf;
    private EntityManager em;
    private Utilisateur user;
    private boolean searching = true;

    public Application() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenceVoyagePU");
        this.em = emf.createEntityManager();
        this.user = new Utilisateur("", "", "guest", "guest", "", null, false, null);
    }
    
    private Application(boolean debug) {
        this();
        initComponents();
        initFenetre();
    }

    public Application(EntityManager em, Utilisateur user) {
        this();
        if(em!=null) this.em = em;
        if(user!=null) this.user=user;
        initComponents();
        initFenetre();
    }
    
    private void initFenetre(){
        Color bgColor = new Color(245,250,235);
        this.setVisible(true);
        this.setLocation(500, 500);
        this.setTitle("sncf.gouv");
        initFond(bgColor);
        cacherOnglets();
        initJTree();
        initJTreeMesTrajets();
        if(user==null || !user.isAdmin()) adminButton.setVisible(false); //cache le bouton admin si on ne l'est pas
    }
    
    private void initFond(Color c){
        this.getContentPane().setBackground(c);
        jLayeredPane1.setBackground(c);
        homePage.setBackground(c);
        rechercheTrajet.setBackground(c);
        listeTrajets.setBackground(c);
        adminPage.setBackground(c);
    }
    
    private void cacherOnglets(){
        rechercheTrajet.setVisible(false);
        listeTrajets.setVisible(false);
        adminPage.setVisible(false);
    }
    
    private void initJTree(){
        Fichier f1 = new Fichier("/icons/icons8-circled-16.png");
        Fichier f2 = new Fichier("/icons/icons8-travel-signpost-16.png");
        
        rechercheList.setRootVisible(false);
        rechercheList.setShowsRootHandles(true);
        rechercheList.setModel(null);
        
        rechercheList.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        rechercheList.addTreeSelectionListener(new TreeSelectionListener(){
            @Override
            public void valueChanged(TreeSelectionEvent e) {
               DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)rechercheList.getLastSelectedPathComponent();   
               if(selectedNode == null || selectedNode.isLeaf()) rechercheList.clearSelection();
              }
        });
        
        ImageIcon leafIcon = f1.creerImageIcon();
        ImageIcon nodeIcon = f2.creerImageIcon();
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        if (leafIcon != null) renderer.setLeafIcon(leafIcon);
        if (nodeIcon != null) {
            renderer.setOpenIcon(nodeIcon);
            renderer.setClosedIcon(nodeIcon);
        }
        rechercheList.setCellRenderer(renderer);
        
    }   
    
    private void initJTreeMesTrajets(){
        Fichier f1 = new Fichier("/icons/icons8-circled-16.png");
        Fichier f2 = new Fichier("/icons/icons8-travel-signpost-16.png");
        
        mesTrajets.setRootVisible(false);
        mesTrajets.setShowsRootHandles(true);
        mesTrajets.setModel(null);
        
        mesTrajets.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        mesTrajets.addTreeSelectionListener(new TreeSelectionListener(){
            @Override
            public void valueChanged(TreeSelectionEvent e) {
               DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)mesTrajets.getLastSelectedPathComponent();   
               if(selectedNode == null || selectedNode.isLeaf()) mesTrajets.clearSelection();
              }
        });

        ImageIcon leafIcon = f1.creerImageIcon();
        ImageIcon nodeIcon = f2.creerImageIcon();
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        if (leafIcon != null) renderer.setLeafIcon(leafIcon);
        if (nodeIcon != null) {
            renderer.setOpenIcon(nodeIcon);
            renderer.setClosedIcon(nodeIcon);
        }
        mesTrajets.setCellRenderer(renderer);
        
    }   
    
    
    
    
    
    
    
    private void ajoutTrajet(DefaultMutableTreeNode top, TrajetUtilisateur tr) {
        DefaultMutableTreeNode trajet = null;
        DefaultMutableTreeNode gare = null;

        trajet = new DefaultMutableTreeNode(tr);
        top.add(trajet);

        gare = new DefaultMutableTreeNode(tr.getTrajet().get(0).getGareDepart().getNom() + "   Départ : " + tr.getTrajet().get(0).getHeureDepart());
      
        trajet.add(gare);   
        
        for(Troncon t : tr.getTrajet()){
            gare = new DefaultMutableTreeNode(t.getGareArrivee().getNom() + "   Arrivée : " + t.getHeureArrivee());
            trajet.add(gare); 
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLayeredPane1 = new javax.swing.JLayeredPane();
        homePage = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        adminButton = new javax.swing.JButton();
        reviewButton = new javax.swing.JButton();
        disconnectButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        rechercheTrajet = new javax.swing.JPanel();
        recherche = new javax.swing.JButton();
        sliderParam = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        gareDepart = new javax.swing.JComboBox<>();
        gareArrivee = new javax.swing.JComboBox<>();
        checkPrix = new javax.swing.JCheckBox();
        checkDuree = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        dateDepart = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        heureDepart = new javax.swing.JFormattedTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        rechercheList = new javax.swing.JTree();
        arretRech = new javax.swing.JButton();
        sauvRech = new javax.swing.JButton();
        adminPage = new javax.swing.JPanel();
        listeTrajets = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mesTrajets = new javax.swing.JTree();
        jLabel8 = new javax.swing.JLabel();
        supMestrajets = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 480));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLayeredPane1.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane1.setForeground(new java.awt.Color(245, 250, 235));
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(800, 450));

        homePage.setForeground(new java.awt.Color(245, 250, 235));
        homePage.setMaximumSize(new java.awt.Dimension(1120, 630));
        homePage.setMinimumSize(new java.awt.Dimension(800, 450));
        homePage.setPreferredSize(new java.awt.Dimension(800, 450));
        homePage.setLayout(new java.awt.GridBagLayout());

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-rechercher-plus-100.png"))); // NOI18N
        searchButton.setToolTipText("Recherche de trajets");
        searchButton.setFocusPainted(false);
        searchButton.setMinimumSize(new java.awt.Dimension(200, 200));
        searchButton.setPreferredSize(new java.awt.Dimension(200, 200));
        searchButton.setRequestFocusEnabled(false);
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        homePage.add(searchButton, new java.awt.GridBagConstraints());

        adminButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-add-100.png"))); // NOI18N
        adminButton.setToolTipText("Création d'utilisateurs");
        adminButton.setMinimumSize(new java.awt.Dimension(200, 200));
        adminButton.setPreferredSize(new java.awt.Dimension(200, 200));
        adminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        homePage.add(adminButton, gridBagConstraints);

        reviewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-list-100.png"))); // NOI18N
        reviewButton.setToolTipText("Vos trajets");
        reviewButton.setMinimumSize(new java.awt.Dimension(200, 200));
        reviewButton.setPreferredSize(new java.awt.Dimension(200, 200));
        reviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        homePage.add(reviewButton, gridBagConstraints);

        disconnectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-disconnect-100.png"))); // NOI18N
        disconnectButton.setToolTipText("Déconnexion");
        disconnectButton.setMinimumSize(new java.awt.Dimension(200, 200));
        disconnectButton.setPreferredSize(new java.awt.Dimension(200, 200));
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        homePage.add(disconnectButton, gridBagConstraints);

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-close-100.png"))); // NOI18N
        exitButton.setToolTipText("Quiiter l'application");
        exitButton.setMinimumSize(new java.awt.Dimension(200, 200));
        exitButton.setPreferredSize(new java.awt.Dimension(200, 200));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        homePage.add(exitButton, gridBagConstraints);

        rechercheTrajet.setPreferredSize(new java.awt.Dimension(800, 450));

        recherche.setText("Rechercher");
        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });

        jLabel1.setText("Durée");

        jLabel2.setText("Prix");

        jLabel3.setText("Gare de Départ :");

        jLabel4.setText("Gare d'Arrivée :");

        gareDepart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gareDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gareDepartActionPerformed(evt);
            }
        });

        gareArrivee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        checkPrix.setText("Prix");
        checkPrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPrixActionPerformed(evt);
            }
        });

        checkDuree.setText("Durée");
        checkDuree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDureeActionPerformed(evt);
            }
        });

        jLabel5.setText("Trier par :");

        dateDepart.setText("DD/MM/YYYY");
        dateDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateDepartActionPerformed(evt);
            }
        });

        jLabel6.setText("Date : DD/MM/YYYY");

        jLabel7.setText("Heure : HH:MM");

        heureDepart.setText("HH:MM");
        heureDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heureDepartActionPerformed(evt);
            }
        });

        rechercheList.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                rechercheListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(rechercheList);

        arretRech.setText("Arret Recherche");
        arretRech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arretRechActionPerformed(evt);
            }
        });

        sauvRech.setText("Sauvgarder Selection");
        sauvRech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauvRechActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rechercheTrajetLayout = new javax.swing.GroupLayout(rechercheTrajet);
        rechercheTrajet.setLayout(rechercheTrajetLayout);
        rechercheTrajetLayout.setHorizontalGroup(
            rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sliderParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gareDepart, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gareArrivee, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(dateDepart, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(heureDepart, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGroup(rechercheTrajetLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(105, 105, 105)))
                    .addComponent(recherche, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rechercheTrajetLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkPrix)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkDuree))
                            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(64, 64, 64))
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addComponent(arretRech, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sauvRech, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        rechercheTrajetLayout.setVerticalGroup(
            rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                                .addGap(250, 250, 250)
                                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sliderParam, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel1))))
                            .addGroup(rechercheTrajetLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gareDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gareArrivee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(heureDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))
                    .addGroup(rechercheTrajetLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkPrix)
                            .addComponent(checkDuree))
                        .addGap(18, 18, 18)))
                .addGroup(rechercheTrajetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arretRech, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sauvRech, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        adminPage.setMaximumSize(new java.awt.Dimension(1120, 630));
        adminPage.setMinimumSize(new java.awt.Dimension(800, 450));
        adminPage.setPreferredSize(new java.awt.Dimension(800, 450));
        adminPage.setLayout(new java.awt.GridBagLayout());

        listeTrajets.setMaximumSize(new java.awt.Dimension(1120, 630));
        listeTrajets.setMinimumSize(new java.awt.Dimension(800, 450));
        listeTrajets.setPreferredSize(new java.awt.Dimension(800, 450));

        jScrollPane1.setViewportView(mesTrajets);

        jLabel8.setText("Vos Trajets: ");

        supMestrajets.setText("Supprimer Selection");
        supMestrajets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supMestrajetsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout listeTrajetsLayout = new javax.swing.GroupLayout(listeTrajets);
        listeTrajets.setLayout(listeTrajetsLayout);
        listeTrajetsLayout.setHorizontalGroup(
            listeTrajetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listeTrajetsLayout.createSequentialGroup()
                .addGroup(listeTrajetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(listeTrajetsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(supMestrajets, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(listeTrajetsLayout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(jLabel8)))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        listeTrajetsLayout.setVerticalGroup(
            listeTrajetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listeTrajetsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(listeTrajetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supMestrajets, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        jLayeredPane1.setLayer(homePage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(rechercheTrajet, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(adminPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(listeTrajets, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rechercheTrajet, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(listeTrajets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(adminPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(homePage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rechercheTrajet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(listeTrajets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(adminPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(homePage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jLayeredPane1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        //gestion des GARES
        homePage.setVisible(false);
        rechercheTrajet.setVisible(true);

        DefaultComboBoxModel gareSelection1 = new  DefaultComboBoxModel();
        DefaultComboBoxModel gareSelection2 = new  DefaultComboBoxModel();
        Requetes r = new Requetes(em);
        List<Gare> gares = r.getGares();
        for(Gare g : gares){
            gareSelection1.addElement(g);
            gareSelection2.addElement(g);
        }
        gareDepart.setModel(gareSelection1);
        gareArrivee.setModel(gareSelection2);

        //gestion des DATES

        MaskFormatter dateFormatter = null;
        try {
            dateFormatter = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        dateDepart.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
        try {
            dateFormatter = new MaskFormatter("##:##");
        } catch (ParseException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        heureDepart.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));

        //Gestion des checkbox
        checkDuree.setSelected(true);
    }                                                      

    private void checkDureeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDureeActionPerformed
        // TODO add your handling code here:
        checkPrix.setSelected(false);
    }//GEN-LAST:event_checkDureeActionPerformed

    private void gareDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gareDepartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gareDepartActionPerformed

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        this.rechercheTrajet.setEnabled(false);
        try {
            searching = true;
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Trajets");
            
            DefaultTreeModel treeModel = new DefaultTreeModel(root);
            rechercheList.setModel(treeModel);
            
            AlgoGenetique algo = new AlgoGenetique(em);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            
            Date date = dateFormat.parse(dateDepart.getText());
            Date time = timeFormat.parse(heureDepart.getText());
            
            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);
            Calendar timeCalendar = Calendar.getInstance();
            timeCalendar.setTime(time);
            
            dateCalendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
            dateCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
            
            SimpleDateFormat finalFormat = new SimpleDateFormat("dd:MM:yyyy:HH:mm");
            Date finalDate = finalFormat.parse(finalFormat.format(dateCalendar.getTime()));
            double svalue = ((double)sliderParam.getValue())/((double)sliderParam.getMaximum()-(double)sliderParam.getMinimum());
            
            
            
            Gare gDepart = (Gare)gareDepart.getSelectedItem();
            Gare gArrivee = (Gare)gareArrivee.getSelectedItem();
            
            System.out.println(String.valueOf(svalue));
            

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                TrajetUtilisateur tr = null;
                List<TrajetUtilisateur> trajets = new ArrayList<>();
                while(searching) {
                    tr = algo.trouverCheminCourt(gDepart,gArrivee, finalDate,9,1-svalue,svalue);
                        if(tr!=null && !tr.getTrajet().isEmpty()){
                            tr.calculate();
                            trajets.add(tr);
                            
                        }
                        Collections.sort(trajets, new Comparator<TrajetUtilisateur>() {
                            @Override
                            public int compare(TrajetUtilisateur t1, TrajetUtilisateur t2) {
                                if(checkPrix.isSelected()) {
                                    return Double.compare(t1.getCout(), t2.getCout());
                                } else {
                                    return Double.compare(t1.getTemps(), t2.getTemps());
                                }
                            }
                        });
                        root.removeAllChildren();
                        for(TrajetUtilisateur t : trajets){
                            ajoutTrajet(root,t);
                        }
                        treeModel.reload();
                    }
                rechercheTrajet.setVisible(true);
                return null;
            }
            };
            worker.execute();
    
        
           
        } catch (ParseException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_rechercheActionPerformed

    private void dateDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateDepartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateDepartActionPerformed

    private void heureDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heureDepartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_heureDepartActionPerformed

    private void checkPrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPrixActionPerformed
        // TODO add your handling code here:
        checkDuree.setSelected(false);
    }//GEN-LAST:event_checkPrixActionPerformed

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed
        new Login();
        this.dispose();
        
    }//GEN-LAST:event_disconnectButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void adminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminButtonActionPerformed

    private void reviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewButtonActionPerformed
        // TODO add your handling code here:
       
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("MesTrajets"); 
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        mesTrajets.setModel(treeModel);
        homePage.setVisible(false);
        listeTrajets.setVisible(true);
        Requetes r = new Requetes(em);
        List<TrajetUtilisateur> trs = new ArrayList<>();
        trs = r.getTrajetUtilisateur(user);
        if(trs.get(0)!=null){
            for(TrajetUtilisateur tr : trs){
                ajoutTrajet(root,tr);
            }
        }
        treeModel.reload();   
    }//GEN-LAST:event_reviewButtonActionPerformed

    private void arretRechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arretRechActionPerformed
        // TODO add your handling code here:
         searching = false;
    }//GEN-LAST:event_arretRechActionPerformed

    private void supMestrajetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supMestrajetsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supMestrajetsActionPerformed

    private void sauvRechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sauvRechActionPerformed
        // TODO add your handling code here:
        Requetes r = new Requetes(em);
        DefaultMutableTreeNode tr = (DefaultMutableTreeNode)rechercheList.getLastSelectedPathComponent();
        TrajetUtilisateur t = (TrajetUtilisateur)tr.getUserObject();
        if(t!=null){
        user.addTrajetUser(t);
        r.addTrajetUtilisateur(user);
        }
        
    }//GEN-LAST:event_sauvRechActionPerformed

    public static void main(String args[]) {   
        Application a = new Application(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminButton;
    private javax.swing.JPanel adminPage;
    private javax.swing.JButton arretRech;
    private javax.swing.JCheckBox checkDuree;
    private javax.swing.JCheckBox checkPrix;
    private javax.swing.JFormattedTextField dateDepart;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JComboBox<String> gareArrivee;
    private javax.swing.JComboBox<String> gareDepart;
    private javax.swing.JFormattedTextField heureDepart;
    private javax.swing.JPanel homePage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel listeTrajets;
    private javax.swing.JTree mesTrajets;
    private javax.swing.JButton recherche;
    private javax.swing.JTree rechercheList;
    private javax.swing.JPanel rechercheTrajet;
    private javax.swing.JButton reviewButton;
    private javax.swing.JButton sauvRech;
    private javax.swing.JButton searchButton;
    private javax.swing.JSlider sliderParam;
    private javax.swing.JButton supMestrajets;
    // End of variables declaration//GEN-END:variables
}
