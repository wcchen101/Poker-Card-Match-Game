/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Alan
 */
public class Match extends javax.swing.JFrame {

    // Instance of our Frame
    protected static javax.swing.JFrame matchgameFrame;   
    
    // Main lists for the matches and the labels
    private ArrayList<String> matchesList = new ArrayList<String>(); // list for storing the matches made
    private ArrayList<JLabel> labelsList = new ArrayList<JLabel>(); // List with all our labels
    private ArrayList<String> labelsImageIconList = new ArrayList<String>(); // List with all our labels' image icons
    
    // Lists to help us to know which cards has been shown or matched
    private ArrayList<JLabel> unmatchedShownLabelsList = new ArrayList<JLabel>(2);
    private ArrayList<JLabel> cardsShowingList = new ArrayList<JLabel>(); // Stores all the cards which are showing to the user
    
    // List with our images
    private ArrayList<String> imagesList = new ArrayList<String>();
    
    // Labels and int to keep track of users actions 
    private JLabel currentLabelwaitingForAMatch,labelClicked; 
    private int labelClickedNum = 0;
    
    // Boolean data member to help us flip back two cards that were not matched
    private Boolean thereIsAnUnmatchedPairShown = false;
     
    
    /**
     * Creates new form Match
     */
    public Match() {
        initComponents();     
        
        newGame();
    }
    
    public void newGame(){
        for (int d =0 ; d<labelsImageIconList.size();d++ ){
        labelsList.get(d).setEnabled(true);
        }
        levelCheckBox.setSelected(false);
        // Clearing some lists in case there is game ongoing
            // Clear the showings card list
            cardsShowingList.clear();

            // Clear the matches list
            matchesList.clear();

            // Clear the lits which deals with the image icons
            labelsList.clear();
            labelsImageIconList.clear();
            imagesList.clear();

            //Erase the message
            jLabel26.setText("");
        
            // Enable the key again
            jLabel8.setEnabled(true);
            levelCheckBox.setEnabled(true);
        
        // Adding our labels to the Labels list
        labelsList.add(jLabel1);labelsList.add(jLabel2);labelsList.add(jLabel3);
        labelsList.add(jLabel4);labelsList.add(jLabel5);labelsList.add(jLabel6);
        labelsList.add(jLabel7);labelsList.add(jLabel8);labelsList.add(jLabel9);
        
        // Initializing the labelsImageIconList
        for(int c=0; c<=8; c++)
            labelsImageIconList.add(""); // All the labels start with no icon
        
        // Adding the images to the images List
        imagesList.add("1.png");
        imagesList.add("1.png");
        imagesList.add("14.png");
        imagesList.add("14.png");
        imagesList.add("27.png");
        imagesList.add("27.png");
        imagesList.add("40.png");
        imagesList.add("40.png");
        imagesList.add("joker.png"); // joker
        
        // Assigning imageIcons to the labels in a sorted way
        // We are going to start from each image trying to assign them to the 9 labels
        int randomIndexAtLabelsImageIconList;
        Boolean iconAssigned;
        String imagePath; // String which contains the name of the file which has the image icon (png file)
 
        // Shuffling the images List
        Collections.shuffle(imagesList);
        
        for(int i=0; i<imagesList.size(); i++){
            
            // Another loop to try to find a empty index at the labelsImageIconList
            iconAssigned = false;
            
            do{
                randomIndexAtLabelsImageIconList = (int) (Math.random() * 9); // it will generate a random int from 0 to 8   
System.out.print(randomIndexAtLabelsImageIconList + ",");
                // Let's check if that this index has already received an imageIcon
                if(labelsImageIconList.get(randomIndexAtLabelsImageIconList).equals("")){
                    // We found a spot
                    
                    // Setting the image for the label
                    imagePath = imagesList.get(randomIndexAtLabelsImageIconList);
                    
                    labelsImageIconList.set(randomIndexAtLabelsImageIconList, imagePath);
                    
                    // Since we found a spot for this image, we change the boolean to true, which 
                    // will make the outer loop gets the next image
                    iconAssigned = true;
                } 
                
            }while(iconAssigned == false); // If it can`t find in the first place, it will try until it finds

        }
         
        // Flipping back the cards
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
        
        // DEBUG
        System.out.println("ImageIcon Labels list:");
        for(int c=0; c<labelsImageIconList.size(); c++){
            System.out.println("["+c+"] " + labelsImageIconList.get(c));
        }
        
        System.out.println("Images list:");
        for(int c=0; c<imagesList.size(); c++){
            System.out.println("["+c+"] " + imagesList.get(c));
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                matchgameFrame = new Match();
                matchgameFrame.setVisible(true);
            }
        });
    }
    
    public void anyLabelClicked(int labelClickedNum, JLabel labelClicked) {

            // We check if is there any unmatched pair of cards shown to the user 
            if(thereIsAnUnmatchedPairShown){
                // Hiding back the cards
                unmatchedShownLabelsList.get(0).setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
                cardsShowingList.remove( unmatchedShownLabelsList.get(0) ); // remove the label from the cards showing
                unmatchedShownLabelsList.get(1).setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
                cardsShowingList.remove( unmatchedShownLabelsList.get(1) );
                
                unmatchedShownLabelsList.clear();
                thereIsAnUnmatchedPairShown = false;
            } 

            // After treating the unmatching situation, we can try making pairs again
            if ( (matchesList.isEmpty() || matchesList.size() % 2 == 0) 
                        && !labelsImageIconList.get(labelClickedNum-1).equals("joker.png") ){ 
                // --> If no matches made or some matches made but he didn`t chose the joker
                
                // Add the image name to the matches list
                matchesList.add( labelsImageIconList.get(labelClickedNum-1) );
                
                // Set the added card as a current label waiting for a match
                currentLabelwaitingForAMatch = labelClicked;     
                 
             } else if (matchesList.size() % 2 == 1 && !labelsImageIconList.get(labelClickedNum-1).equals("joker.png") ) {
                 // --> If there is a label waiting for a match and he didn't click the joker
                 
                 if (matchesList.get( matchesList.size()-1 ).equals(labelsImageIconList.get(labelClickedNum-1)) ) { // checks if the new label clicked matches with the last added to matchesList
                     
                     // Add the image name to the matches list
                     matchesList.add( labelsImageIconList.get(labelClickedNum-1) );
                     
                 } else {

                     thereIsAnUnmatchedPairShown = true;
                     unmatchedShownLabelsList.clear();
                     unmatchedShownLabelsList.add(currentLabelwaitingForAMatch);
                     unmatchedShownLabelsList.add(labelClicked);

                     // Removing the last card from the matches list
                     matchesList.remove( matchesList.size()-1 );       // remove the last card added
                 }

             }else if (labelsImageIconList.get(labelClickedNum-1).equals("joker.png") ) {
                // --> User clicked the joker
                 
                 //System.err.println("Lose");
                 final JDialog dialog = new JDialog();
                 dialog.setSize(800, 500);
                 dialog.setModal(true);
                 dialog.setLocationRelativeTo(this);
                 
                 JLabel label = new JLabel("        You choose the Joker Card, you lose!!!"
                         +" \n If you want to leave please press Y or \n stay for new Game, please press N");

                 label.addKeyListener(new KeyAdapter() {

                     @Override
                     public void keyPressed(KeyEvent event) {

                         if (event.getKeyChar() == 'Y' || event.getKeyChar() == 'y') {
                             // add windowEvent
                             matchgameFrame.dispatchEvent(new WindowEvent(matchgameFrame, WindowEvent.WINDOW_CLOSING));
                         }
                         if (event.getKeyChar() == 'N' || event.getKeyChar() == 'n') {
                             dialog.setVisible(false);
                             
                             newGame();
                         }
                     }
                 });
           
               
                 dialog.add(label);
                 label.setFocusable(true);
                 dialog.setVisible(true);
             }
            
            // Debug
            System.out.println("** -------- **");
            System.out.println("Showing Cards List: ");
            for(int i=0; i<=cardsShowingList.size()-1; i++){
                System.out.println("["+i+"]" + cardsShowingList.get(i).getText());
            }
            System.out.println("");
            System.out.println("Matches List: ");
            for(int i=0; i<=matchesList.size()-1; i++){
                System.out.println("["+i+"]" + matchesList.get(i));
            }
            System.out.println("** -------- **");
            
            // Checking if the game is over
            if(matchesList.size() == 8){
                // The 4 pairs have been made. END OF THE GAME!
                
                jLabel26.setText("You Win!");
                
                for(int i=0; i<labelsList.size(); i++){
                    System.out.println(i);
                    if(labelsImageIconList.get(i).equals("joker.png"))           
                        labelsList.get(i).setEnabled(false);
                   
                    
                }
                
                levelCheckBox.setEnabled(false);
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButtonNewGame = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        levelCheckBox = new javax.swing.JCheckBox();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png"))); // NOI18N
        jLabel9.setText("jLabel9");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jButtonNewGame.setLabel("New Game");
        jButtonNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewGameActionPerformed(evt);
            }
        });

        jButtonClose.setLabel("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });
        jButtonClose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButtonCloseKeyTyped(evt);
            }
        });

        levelCheckBox.setText("Easy Level");
        levelCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                levelCheckBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(levelCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jButtonNewGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonClose)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(levelCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNewGame)
                    .addComponent(jButtonClose))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       
        if(!cardsShowingList.contains(jLabel1)){
            // Getting the image icon which is in the labelsImageIconList
            String imageIcon = labelsImageIconList.get(0);
            
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/" + imageIcon)));
            cardsShowingList.add(jLabel1);
            
            anyLabelClicked(1,jLabel1);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

        if(!cardsShowingList.contains(jLabel2)){
            // Getting the image icon which is in the labelsImageIconList
            String imageIcon = labelsImageIconList.get(1);
            
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/" + imageIcon)));
            cardsShowingList.add(jLabel2);
            
            anyLabelClicked(2,jLabel2);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        
        if(!cardsShowingList.contains(jLabel3)){
            // Getting the image icon which is in the labelsImageIconList
            String imageIcon = labelsImageIconList.get(2);
            
            jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/" + imageIcon)));
            cardsShowingList.add(jLabel3);
            
            anyLabelClicked(3,jLabel3);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        
        if(!cardsShowingList.contains(jLabel4)){
            // Getting the image icon which is in the labelsImageIconList
            String imageIcon = labelsImageIconList.get(3);
            
            jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/" + imageIcon)));
            cardsShowingList.add(jLabel4);
            
            anyLabelClicked(4,jLabel4);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

        if(!cardsShowingList.contains(jLabel5)){
            // Getting the image icon which is in the labelsImageIconList
            String imageIcon = labelsImageIconList.get(4);
            
            jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/" + imageIcon)));
            cardsShowingList.add(jLabel5);
            
            anyLabelClicked(5,jLabel5);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        if(!cardsShowingList.contains(jLabel6)){
            // Getting the image icon which is in the labelsImageIconList
            String imageIcon = labelsImageIconList.get(5);
            
            jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/" + imageIcon)));
            cardsShowingList.add(jLabel6);
            
            anyLabelClicked(6,jLabel6);
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
 
        if(!cardsShowingList.contains(jLabel7)){
            // Getting the image icon which is in the labelsImageIconList
            String imageIcon = labelsImageIconList.get(6);
            
            jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/" + imageIcon)));
            cardsShowingList.add(jLabel7);
            
            anyLabelClicked(7,jLabel7);
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked

        if(!cardsShowingList.contains(jLabel8)){
            // Getting the image icon which is in the labelsImageIconList
            String imageIcon = labelsImageIconList.get(7);
            
            jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/" + imageIcon)));
            cardsShowingList.add(jLabel8);
            
            anyLabelClicked(8,jLabel8);
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked

        if(!cardsShowingList.contains(jLabel9)){
            // Getting the image icon which is in the labelsImageIconList
            String imageIcon = labelsImageIconList.get(8);
            
            jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/" + imageIcon)));
            cardsShowingList.add(jLabel9);
            
            anyLabelClicked(9,jLabel9);
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed

        final JDialog dialog = new JDialog();
        dialog.setSize(500, 500);
        dialog.setModal(true);

        JLabel label = new JLabel("      If you wanna leave, please press 'Y', if you don't, please press 'N'");

        label.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyChar() == 'Y' || e.getKeyChar() == 'y') {
                    // add windowEvent
                    matchgameFrame.dispatchEvent(new WindowEvent(matchgameFrame, WindowEvent.WINDOW_CLOSING));
                }
                if (e.getKeyChar() == 'N' || e.getKeyChar() == 'n') {
                    dialog.setVisible(false);
                   
                }
            }
        });

        label.setFocusable(true);
        dialog.add(label);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonCloseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonCloseKeyTyped

    }//GEN-LAST:event_jButtonCloseKeyTyped

    private void levelCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_levelCheckBoxItemStateChanged
      
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            
            for(int i=0; i<labelsImageIconList.size(); i++){
 
                if(labelsImageIconList.get(i).equals("joker.png"))           
                    labelsList.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/joker.png")));
            }
            
        } else {
            for(int i=0; i<labelsImageIconList.size(); i++){
 
                if(labelsImageIconList.get(i).equals("joker.png"))           
                    labelsList.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/b1fv.png")));
            }
            
        }
    }//GEN-LAST:event_levelCheckBoxItemStateChanged

    private void jButtonNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewGameActionPerformed
        newGame();
    }//GEN-LAST:event_jButtonNewGameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonNewGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JCheckBox levelCheckBox;
    // End of variables declaration//GEN-END:variables
}
