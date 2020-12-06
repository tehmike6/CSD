
package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controller.Controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

        
/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class GraphicUI extends JFrame  {
  
    private    Controller game;
    private int next=45,p=0;
    private int p1[][]=new int [4][3];
    private int a[][]=new int [56][2];
    private int h[]=new int[14];
    private  LinkedList <JButton>keys_list=new LinkedList<JButton>();
    private ArrayList <JButton>k_list=new ArrayList<JButton>();
    private ArrayList <Integer> p_list=new ArrayList<Integer>();
    private ArrayList <JButton>but_list=new ArrayList<JButton>();
    private FlowLayout experimentLayout = new FlowLayout();
    private Image image;
    private JButton GrandTichuButton;
    private JButton TichuButton;
    private JButton PlayButton,newGame,Exit,HundButton;
    private JButton FoldButton;
    private JButton jButton5;
    private JButton jButton6;
    private myDesktopPane basic_panel;
    private JDesktopPane player3_field;
    private JDesktopPane player2_field;
    private JDesktopPane player1_field;
    private JDesktopPane player4_field;
    private JDesktopPane player2_field2;
    private JDesktopPane player1_field2;
    private JDesktopPane player3_field2;
    private JDesktopPane player4_field2,tablo;
    private JPanel jPanel1;
    private JTextField jTextField1;
    private JTextField score,euxi;
    private JLabel choice1,choice2,choice3,choice4;
    private URL imageURL;
    private ClassLoader cldr;  
    private JButton[] buttons = new JButton[56];
    /**
	 * <b>constructor</b>: Creates a new Window and initializes some buttons and panels <br />
	 * <b>postconditions</b>: Creates a new Window and initializes some buttons and panels
	 * starting a new game.
	 */
    public GraphicUI() {
        
        basic_panel = new myDesktopPane();
        player3_field = new JDesktopPane();
        player2_field = new JDesktopPane();
        player1_field = new JDesktopPane();
        player4_field = new JDesktopPane();
        player2_field2 = new JDesktopPane();
        player1_field2 = new JDesktopPane();
        player3_field2 = new JDesktopPane();
        player4_field2 = new JDesktopPane();
        tablo = new JDesktopPane();
        jTextField1 = new JTextField();
        score=new JTextField();
        euxi=new JTextField();
        GrandTichuButton = new JButton();
        TichuButton = new JButton();
        PlayButton = new JButton();
        FoldButton = new JButton();
        HundButton=new JButton();
        choice1=new JLabel();
        choice2=new JLabel();
        choice3=new JLabel();
        choice4=new JLabel();
        newGame=new JButton();
        Exit=new JButton();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cldr = this.getClass().getClassLoader();  
        this.getContentPane().setBackground(new Color(0,0,0));
        this.setResizable(false);
        this.setTitle("Tichu Game"); 
        imageURL= cldr.getResource("images/tichu.jpg");
        this.setIconImage(new ImageIcon(imageURL).getImage());
        game=new Controller();
        this.setPreferredSize(new Dimension(910, 710));
        initComponents();
        
    }
   
    
    /**
	 * <b>transformer(mutative)</b>:initializes some buttons and labels <br />
	 * <p><b>Postcondition:</b> initializes some buttons and labels </p>
	 *
     */
    private void initComponents() {
        
         
        int j;
         
        
        choice1.setBounds(690,580,34,100);
        choice2.setBounds(770,615,100,34);
        choice3.setBounds(140,30,34,100);
        choice4.setBounds(0,615,100,34);
        
        basic_panel.add(choice1, JLayeredPane.DEFAULT_LAYER);
        basic_panel.add(choice2, JLayeredPane.DEFAULT_LAYER);
        basic_panel.add(choice3, JLayeredPane.DEFAULT_LAYER);
        basic_panel.add(choice4, JLayeredPane.DEFAULT_LAYER);
        Exit.setBounds(790,30,60,60);
        Exit.setOpaque(false);
        Exit.setFocusPainted(false);
        Exit.setBorderPainted(false);
        Exit.setContentAreaFilled(false);
        Exit.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        imageURL= cldr.getResource("images/exit.png");
        Exit.setIcon(new ImageIcon(imageURL));
        Exit.addActionListener(new SettingsListener());
        basic_panel.add(Exit);
        newGame.setBounds(20,30,61,60);
        imageURL= cldr.getResource("images/new.png");
        newGame.setIcon(new ImageIcon(imageURL));
        newGame.setOpaque(false);
        newGame.setFocusPainted(false);
        newGame.setBorderPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        newGame.addActionListener(new SettingsListener());
        basic_panel.add(newGame, JLayeredPane.DEFAULT_LAYER);
       
        player3_field.setBounds(174, 30, 509, 100);
        basic_panel.add(player3_field, new JLayeredPane());
        player2_field.setBounds(770, 100, 100, 509);
        basic_panel.add(player2_field, JLayeredPane.DEFAULT_LAYER);
        player1_field.setBounds(174, 580, 509, 100);
        basic_panel.add(player1_field, JLayeredPane.DEFAULT_LAYER);
        player4_field.setBounds(0, 100, 100, 509);
        basic_panel.add(player4_field, JLayeredPane.DEFAULT_LAYER);
        player2_field2.setBounds(650, 175, 100, 350);
        basic_panel.add(player2_field2, JLayeredPane.DEFAULT_LAYER);
        player1_field2.setBounds(260, 460, 350, 100);
        basic_panel.add(player1_field2, JLayeredPane.DEFAULT_LAYER);
        player3_field2.setBounds(260, 150, 350, 100);
        basic_panel.add(player3_field2, JLayeredPane.DEFAULT_LAYER);
        player4_field2.setBounds(120, 175, 100, 350);
        basic_panel.add(player4_field2, JLayeredPane.DEFAULT_LAYER);
        tablo.setBounds(285, 280, 300, 100);
        basic_panel.add(tablo, JLayeredPane.DEFAULT_LAYER);
       
        euxi.setBounds(285,255, 300,20);
        euxi.setOpaque(false);
        euxi.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14));
        euxi.setBorder(null);
        euxi.setEditable(false);
        euxi.setForeground(new java.awt.Color(255, 255, 255));
        euxi.setHorizontalAlignment(JTextField.CENTER);
        basic_panel.add(euxi, JLayeredPane.DEFAULT_LAYER);
        score.setBounds(0,0, 910,25);
        basic_panel.add(score, JLayeredPane.DEFAULT_LAYER);
        score.setBackground(Color.white);
        score.setText(game.GetScore());
        score.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12));
        score.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.ORANGE));
        score.setEditable(false);
        jTextField1.setBounds(285, 390, 300, 44);
        basic_panel.add(jTextField1, JLayeredPane.DEFAULT_LAYER);
        jTextField1.setBackground(Color.orange);
        jTextField1.setEditable(false);
        GrandTichuButton.setBounds(230, 300, 44, 44);
        basic_panel.add(GrandTichuButton, JLayeredPane.DEFAULT_LAYER);
        GrandTichuButton.addActionListener(new TichuListener());
        imageURL= cldr.getResource("images/gt_43x44.png");
        GrandTichuButton.setIcon(new ImageIcon(imageURL));
        
        
        TichuButton.setBounds(598, 300, 44, 44);
        imageURL= cldr.getResource("images/t_43x44.png");
        TichuButton.setIcon(new ImageIcon(imageURL));
        TichuButton.addActionListener(new TichuListener());
        basic_panel.add(TichuButton, JLayeredPane.DEFAULT_LAYER);
        TichuButton.setVisible(false);
        
        PlayButton.setBounds(598, 390, 44, 44);
        PlayButton.addActionListener(new PlayListener());
        basic_panel.add(PlayButton, JLayeredPane.DEFAULT_LAYER);
        imageURL= cldr.getResource("images/play_43x44.png");
        PlayButton.setIcon(new ImageIcon(imageURL));
        PlayButton.setVisible(false);

        
        FoldButton.setBounds(230, 390, 44, 44);
        FoldButton.addActionListener(new FoldListener());
        imageURL= cldr.getResource("images/fold2_43x44.png");
        FoldButton.setIcon(new ImageIcon(imageURL));
        basic_panel.add(FoldButton, JLayeredPane.DEFAULT_LAYER);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(basic_panel, GroupLayout.PREFERRED_SIZE, 910, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(basic_panel, GroupLayout.PREFERRED_SIZE, 685, GroupLayout.PREFERRED_SIZE)
        );

        pack();
       
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        imageURL= cldr.getResource("images/Green G.png");
        image=new ImageIcon(imageURL).getImage();
        player1_field.setBackground(Color.yellow);
        player1_field2.setOpaque(false);
        player1_field2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
        player2_field2.setOpaque(false);
        player2_field2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
        player3_field2.setOpaque(false);
        player3_field2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
        player4_field2.setOpaque(false);
        player4_field2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
        player2_field.setBackground(Color.yellow);
        player3_field.setBackground(Color.yellow);
        player4_field.setBackground(Color.yellow);
        tablo.setBackground(Color.ORANGE);
        tablo.setOpaque(false);
        tablo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.ORANGE));
        jTextField1.setOpaque(false);
        jTextField1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.ORANGE));
        jTextField1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12));
        jTextField1.setHorizontalAlignment(JTextField.CENTER);
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
       
         Graphics g1 = basic_panel.getGraphics();
        basic_panel.paintComponent(g1);
        basic_panel.repaint();  
                                
                                 
                            
        
          
        for (int i=0;i<56;i++){
            
                buttons[i]=new JButton();
           
            buttons[i].addActionListener(new CardListener());
            k_list.add(buttons[i]);
            
        }
        
       
       
           playSound("sd.wav");
           try {
        Thread.sleep(800);
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    } 
        for ( j=0;j<8;j++){
          
        
                
          
          imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(j)+"_67x100.png");
          buttons[j].setIcon(new ImageIcon(imageURL));
          buttons[j].setBounds(0+34*j,0,67,100);
          
          a[j][0]=0+34*j;
          a[j][1]=0;
          player1_field.add(buttons[j], JLayeredPane.DEFAULT_LAYER);
          
           
         imageURL= cldr.getResource("cardsR/"+game.Get_all_Cards().get(j+14)+"_67x100.png");
         buttons[j+14].setIcon(new ImageIcon(imageURL));
         
          buttons[j+14].setBounds(0,34*((j+14)-14),100,67);
          a[j+14][1]=34*((j+14)-14);
          a[j+14][0]=0;
           player2_field.add(buttons[j+14], JLayeredPane.DEFAULT_LAYER);
         
         imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(j+28)+"_67x100.png");
         buttons[j+28].setIcon(new ImageIcon(imageURL));
          buttons[j+28].setBounds(34*((j+28)-28),0,67,100);
          a[j+28][0]=34*((j+28)-28);
          a[j+28][1]=0;
          player3_field.add(buttons[j+28], JLayeredPane.DEFAULT_LAYER);
      
          imageURL= cldr.getResource("cardsR/"+game.Get_all_Cards().get(j+42)+"_67x100.png");
          buttons[j+42].setIcon(new ImageIcon(imageURL));
       
          buttons[j+42].setBounds(0,0+34*((j+42)-42),100,67);
          a[j+42][1]=0+34*((j+42)-42);
          a[j+42][0]=0;
          player4_field.add(buttons[j+42], JLayeredPane.DEFAULT_LAYER);
          
     
    
    
        }
         basic_panel.repaint();  
         setVisible(true);
         jTextField1.setText("Player 1 : Grand Tichu or FOLD");
        
    }
        
    /**
	 * <b>transformer(mutative)</b>:sets some buttons and labels for a new deal<br />
	 * <p><b>Postcondition:</b> sets some buttons and labels for a new deal </p>
	 *
     */
    public void init_buttons(){
        choice1.setVisible(false); 
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
        jTextField1.setText("Player 1 : Grand Tichu or FOLD");
        for (int j=0;j<42;j++){
          
          if (j<8){
            imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
          buttons[j].setBounds(0+34*j,0,67,100);
          
          a[j][0]=0+34*j;
          a[j][1]=0;
          player1_field.add(buttons[j], JLayeredPane.DEFAULT_LAYER);
          }
          else if ((j>13)&&(j<22)){
         imageURL= cldr.getResource("cardsR/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
          buttons[j].setBounds(0,34*(j-14),100,67);
          a[j][1]=34*(j-14);
          a[j][0]=0;
           player2_field.add(buttons[j], JLayeredPane.DEFAULT_LAYER);
          }
          else if ((j<36) && (j>27)){
              imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
          buttons[j].setBounds(34*(j-28),0,67,100);
          a[j][0]=34*(j-28);
          a[j][1]=0;
          player3_field.add(buttons[j], JLayeredPane.DEFAULT_LAYER);
          }
    }
   
    
      for (int j=42;j<50;j++){
          imageURL= cldr.getResource("cardsR/"+game.Get_all_Cards().get(j)+"_67x100.png");
          buttons[j].setIcon(new ImageIcon(imageURL));
          buttons[j].setBounds(0,0+34*(j-42),100,67);
          a[j][1]=0+34*(j-42);
          a[j][0]=0;
          player4_field.add(buttons[j], JLayeredPane.DEFAULT_LAYER);
          
    }   
    basic_panel.repaint();   
     
    }
    /**
	 * <b>transformer(mutative)</b>:sets some buttons and labels after every player has 14 cards<br />
	 * <p><b>Postcondition:</b> sets some buttons and labels after every player has 14 cards</p>
	 *
     */
    public void mirasma(){
       game.sort_cards();
            try {
        playSound("sd.wav");
        Thread.sleep(1000);
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    } 
        
               
        
        for (int j=0;j<50;j++){
            
    
        
        
          if ((j<8)){
              imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
                         
          }
          else if (j<14){
              
          buttons[j].setBounds(0+34*j,0,67,100);
          imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
          a[j][0]=34*j;
          a[j][1]=0;
          player1_field.add(buttons[j], JLayeredPane.DEFAULT_LAYER);
          }
          else if (j<22){
              
              imageURL= cldr.getResource("cardsR/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
              
          }
          else if (j<28){
         imageURL= cldr.getResource("cardsR/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
          buttons[j].setBounds(0,34*(j-14),100,67);
          a[j][1]=34*(j-14);
          a[j][0]=0;
          player2_field.add(buttons[j], JLayeredPane.DEFAULT_LAYER);
          }
          else if (j<36){
              imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
          }
          else if ((j<42)){
             imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
          buttons[j].setBounds(34*(j-28),0,67,100);
          a[j][0]=34*(j-28);
          a[j][1]=0;
          player3_field.add(buttons[j], JLayeredPane.DEFAULT_LAYER);
          }
          else if (j<50){
              imageURL= cldr.getResource("cardsR/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
          }
              basic_panel.repaint(); 
    }
   
    
      for (int j=50;j<56;j++){
          imageURL= cldr.getResource("cardsR/"+game.Get_all_Cards().get(j)+"_67x100.png");
              buttons[j].setIcon(new ImageIcon(imageURL));
          buttons[j].setBounds(0,0+34*(j-42),100,67);
          a[j][1]=0+34*(j-42);
          a[j][0]=0;
          player4_field.add(buttons[j], JLayeredPane.DEFAULT_LAYER);
          
    }   
       
      PlayButton.setVisible(true);
      TichuButton.setVisible(true);
   }
  /* a class which is used for putting a background image to a jdesktoppane*/  
public class myDesktopPane extends JDesktopPane
{
   private Image backImage = null;
 
   public myDesktopPane()
   {      
      
   }
 
        @Override
   public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
 } 
/* a class which is used for doing some action after a card button has been pushed */  
 private class CardListener implements ActionListener 	{
        @Override
                 /**
	 * <b>transformer(mutative)</b>:doing some action after a card button has been pushed<br />
	 * <p><b>Postcondition:</b> doing some action after a card button has been pushed</p>
	 *
     */
		public void actionPerformed(ActionEvent e) 	{
                    if (game.not_started()==true){
                        return;
                    }
                       
                    String choice = e.getActionCommand();
                    int index,i=0,j,m;
                    
                    JButton but = ((JButton) e.getSource());
                    index=k_list.indexOf(e.getSource());
                    
                    if ((index<14) && (game.seeTurn() ==0)) {
                       playSound("m.wav");   
                       if (p_list.contains(index)==true){
                          but.setBounds(a[index][0],a[index][1],67,100);
                          player1_field.add(but);
                            for(p=13;p>-1;p--){
                            if (player1_field.isAncestorOf(buttons[p]) ){
                                    player1_field.setComponentZOrder(buttons[p],0);
                            }
                            }
                        
                          
                          p_list.remove(p_list.indexOf(index));
                          m=p_list.size();
                          if ((game.antallages()==false)){ 
                              for(m=0;m<p_list.size();m++){
                                    buttons[p_list.get(m)].setBounds(40+100*m,0,67,100);
                              }
                          }  
                          else{
                              
                              for(m=0;m<p_list.size();m++){
                              buttons[p_list.get(m)].setBounds(22*m,0,67,100);
                             
                          }
                          }
                          h[m]=0;
                          
                          basic_panel.repaint();
                          
                          
                     }
                     else{ 
                         if ((game.antallages()==false) && p_list.size()==3){ 
                           return;
                         }  
                         while (h[i]!=0){
                         i++;
                       
                        }
                        h[i]=1;
                         player1_field.remove(but);
                        basic_panel.repaint();
                        if ((game.antallages()==false)){ 
                           but.setBounds(40+100*i,0, 67, 100);
                        }  
                        else{
                            but.setBounds(22*i,0, 67, 100);
                        }    
                        player1_field2.add(but);
                    
                        p_list.add(i, index);
                        
                         }
                     
                 }    
                 
                    if ((index>13)&& (index<28) && (game.seeTurn() ==1)) {
                        
                      playSound("m.wav");  
                        
                      if (p_list.contains(index)==true){
                          but.setBounds(a[index][0],a[index][1],100,67);
                          player2_field.add(but);
                          for(p=27;p>13;p--){
                            if (player2_field.isAncestorOf(buttons[p]) ){
                                    player2_field.setComponentZOrder(buttons[p],0);
                            }
                            }
                          p_list.remove(p_list.indexOf(index));
                          m=p_list.size();
                          if ((game.antallages()==false)){ 
                              for(m=3;m<p_list.size();m++){
                                    buttons[p_list.get(m)].setBounds(0,40+100*(m-3),100,67);
                              }
                          }
                          else{
                          for(m=0;m<p_list.size();m++){
                              buttons[p_list.get(m)].setBounds(0,22*m,100,67);
                          }
                          }
                          h[m]=0;
                          basic_panel.repaint();
                     }
                     else{ 
                          if ((game.antallages()==false) && p_list.size()==6){ 
                           return;
                         }  
                         while (h[i]!=0){
                         i++;
                       
                        }
                        h[i]=1;
                         player2_field.remove(but);
                        basic_panel.repaint();
                        if ((game.antallages()==false)){ 
                           but.setBounds(0,40+100*(i-3), 100, 67);
                        }  
                        else{
                            but.setBounds(0,22*i, 100, 67);
                        }    
                        
                    
                        player2_field2.add(but);
                    
                        p_list.add(i, index);
                   
                         }  
                    
                }
                    
                if ((index>27)&& (index<42) && (game.seeTurn() ==2)) {
                       playSound("m.wav");  
                       if (p_list.contains(index)==true){
                          but.setBounds(a[index][0],a[index][1],67,100);
                          player3_field.add(but);
                          for(p=41;p>27;p--){
                            if (player3_field.isAncestorOf(buttons[p]) ){
                                    player3_field.setComponentZOrder(buttons[p],0);
                            }
                            }
                          p_list.remove(p_list.indexOf(index));
                          
                          m=p_list.size();
                          if ((game.antallages()==false)){ 
                              for(m=6;m<p_list.size();m++){
                                    buttons[p_list.get(m)].setBounds(40+100*(m-6),0,67,100);
                              }
                          }  
                          else{
                              for(m=0;m<p_list.size();m++){
                              buttons[p_list.get(m)].setBounds(22*m,0,67,100);
                             
                          }
                          }
                          h[m]=0;
                          basic_panel.repaint();
                     }
                     else{ 
                         if ((game.antallages()==false) && p_list.size()==9){ 
                           return;
                         }  
                         while (h[i]!=0){
                         i++;
                       
                        }
                        h[i]=1;
                         player3_field.remove(but);
                        basic_panel.repaint();
                        if ((game.antallages()==false)){ 
                           but.setBounds(40+100*(i-6),0, 67, 100);
                        }  
                        else{
                            but.setBounds(22*i,0, 67, 100);
                        }   
                    
                        player3_field2.add(but);
                    
                        p_list.add(i, index);
                   
                         }
                     
                 }    
                if ((index>41)&& (index<56) && (game.seeTurn()==3)) {
                        
                    
                    playSound("m.wav");  
                      if (p_list.contains(index)==true){
                          but.setBounds(a[index][0],a[index][1],100,67);
                          player4_field.add(but);
                          for(p=55;p>41;p--){
                            if (player4_field.isAncestorOf(buttons[p]) ){
                                    player4_field.setComponentZOrder(buttons[p],0);
                            }
                            }
                          p_list.remove(p_list.indexOf(index));
                          m=p_list.size();
                          if ((game.antallages()==false)){ 
                              for(m=9;m<p_list.size();m++){
                                    buttons[p_list.get(m)].setBounds(0,40+100*(m-9),100,67);
                              }
                          }
                          else{
                          for(m=0;m<p_list.size();m++){
                              buttons[p_list.get(m)].setBounds(0,22*m,100,67);
                          }
                          }
                          h[m]=0;
                          basic_panel.repaint();
                     }
                     else{ 
                           if ((game.antallages()==false) && p_list.size()==12){ 
                           return;
                         }  
                         while (h[i]!=0){
                         i++;
                       
                        }
                        h[i]=1;
                         player4_field.remove(but);
                        basic_panel.repaint();
                        if ((game.antallages()==false)){ 
                           but.setBounds(0,40+100*(i-9), 100, 67);
                        }  
                        else{
                            but.setBounds(0,22*i, 100, 67);
                        }    
                    
                        player4_field2.add(but);
                    
                        p_list.add(i, index);
                   
                         }  
                    
                }
                }
                
                    
                    
 }
     /* a class which is used for doing some action after play button has been pushed */
      private class PlayListener implements ActionListener 	{
         /**
	 * <b>transformer(mutative)</b>:doing some action after Play button has been pushed<br />
	 * <p><b>Postcondition:</b> doing some action after Play button has been pushed</p>
	 *
         */
          @Override
        
		public void actionPerformed(ActionEvent e) 	{
                        int gg,pp=0;
                        if (game.antallages()==false){
                            if (p_list.size()==(3+game.seeTurn()*3)){
                                game.isready2();
                                jTextField1.setText("Player "+ (game.seeTurn()+1)+ ":Choose 3 Cards for Exchange");
                                
                            }
                            else{
                                 playSound("buzz.wav");
                            }
                            if (game.Get_isready2()==4){
                                game.Setantallages(true);
                                game.makeAntallages(p_list);
                            for (p=0;p<12;p++){
                                h[p]=0;
                                if (p<3){
                                    buttons[p_list.get(p)].setBounds(a[p_list.get(p)][0],a[p_list.get(p)][1],67,100);
                                    player1_field2.remove(buttons[p_list.get(p)]);
                                    player1_field.add(buttons[p_list.get(p)]);
                                }
                                else if (p<6){
                                    buttons[p_list.get(p)].setBounds(a[p_list.get(p)][0],a[p_list.get(p)][1],100,67);
                                    player2_field2.remove(buttons[p_list.get(p)]);
                                    player2_field.add(buttons[p_list.get(p)]);
                                }
                                else if (p<9){
                                    buttons[p_list.get(p)].setBounds(a[p_list.get(p)][0],a[p_list.get(p)][1],67,100);
                                    player3_field2.remove(buttons[p_list.get(p)]);
                                    player3_field.add(buttons[p_list.get(p)]);
                                }
                                else if (p<12){
                                    buttons[p_list.get(p)].setBounds(a[p_list.get(p)][0],a[p_list.get(p)][1],100,67);
                                    player4_field2.remove(buttons[p_list.get(p)]);
                                    player4_field.add(buttons[p_list.get(p)]);
                                }
                            }
                            for (p=0;p<56;p++){
                                if ((p<14) || ((p>27) && (p<42))){
                                    imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(p)+"_67x100.png");
                                    buttons[p].setIcon(new ImageIcon(imageURL));
                                    
                                   
                                }
                                else{
                                     imageURL= cldr.getResource("cardsR/"+game.Get_all_Cards().get(p)+"_67x100.png");
                                    buttons[p].setIcon(new ImageIcon(imageURL));
                                     
                                }
                            }
                            for(p=13;p>-1;p--){
                                player1_field.setComponentZOrder(buttons[p],0);
                                player2_field.setComponentZOrder(buttons[14+p],0);
                                player3_field.setComponentZOrder(buttons[28+p],0);
                                player4_field.setComponentZOrder(buttons[42+p],0);
                            }
                            game.sort_cards();
                            game.set_Turn();
                            jTextField1.setText("It's Player's "+ (game.seeTurn()+1)+ " Turn");
                            p_list.clear();
                            basic_panel.repaint();
                            
                            }
                            return;
                            
                        }
                        if (!p_list.isEmpty()){
                            game.PlayCollection(p_list);
                            if (game.GetCollectionHasPlayed()==true){
                            jTextField1.setText("It's Player's "+ (game.seeTurn()+1)+ " Turn");
                            tablo.removeAll();
                            
                            basic_panel.repaint();
                           
                            if (game.euxiMeMahjong()>0){
                                euxi.setText("Exei zitithei: " + game.euxi());
                            }
                            else{
                                euxi.setText("");
                            }
                            if (p_list.size()==1){
                                gg=117;
                            }
                            else if(p_list.size()<=3){
                                gg=95;
                                
                            }
                             else if(p_list.size()<=6){
                                gg=50;
                                pp=30;
                            }
                            else if(p_list.size()<=9){
                                gg=0;
                                pp=27;
                            } 
                            else if (p_list.size()<=12) {
                                gg=0;
                                pp=20;
                            }
                            else{
                                gg=0;
                                pp=16;
                                        
                            }
                            if (p_list.size()<=3){
                                for (int j=0;j<p_list.size();j++){
                                tablo.add(buttons[p_list.get(j)]);
                                imageURL= cldr.getResource("cards/"+game.Get_all_Cards().get(p_list.get(j))+"_67x100.png");
                                buttons[p_list.get(j)].setIcon(new ImageIcon(imageURL));
                                if (game.Get_all_Cards().get(p_list.get(j)).charAt(0)=='H'){
                                     playSound("dogs.wav");
                                     tablo.remove(buttons[p_list.get(j)]);
                                     imageURL= cldr.getResource("cards/HUND_67x100.png");
                                     HundButton.setIcon(new ImageIcon(imageURL));
                                     HundButton.setBounds(gg+30*j, 0, 67, 100);
                                     tablo.add(HundButton);
                                }
                                else if (game.Get_all_Cards().get(p_list.get(j)).charAt(0)=='D'){
                                     playSound("dragon.wav");
                                }
                                else if (game.Get_all_Cards().get(p_list.get(j)).charAt(0)=='P'){
                                     playSound("Peacock.wav");
                                }
                                buttons[p_list.get(j)].setBounds(gg+30*j, 0, 67, 100);
                                
                                }    
                            }
                            else{
                                String[] cards=new String[game.PreviousMove().length];
                                cards=game.PreviousMove();
                                for (int j=0;j<p_list.size();j++){
                                if (cards[j].equals("Phoenix")==true){
                                    playSound("Peacock.wav");
                                }   
                                else if (game.Ret_Round().bomb_round()==true){
                                    playSound("Bomb.WAV");
                                }
                                tablo.add(buttons[p_list.get(j)]);
                                imageURL= cldr.getResource("cards/"+cards[j]+"_67x100.png");
                                buttons[p_list.get(j)].setIcon(new ImageIcon(imageURL));
                                
                                buttons[p_list.get(j)].setBounds(gg+pp*j, 0, 67, 100);
                                
                                }    
                            }
                            basic_panel.repaint();
                            p_list.clear();
                            if (game.partida_has_finished()==true){
                                game.setScore();
                                score.setText(game.GetScore());
                                tablo.removeAll();
                                player3_field.removeAll();
                                player2_field.removeAll();
                                player1_field.removeAll();
                                player4_field.removeAll();
                                p_list.clear();
                                TichuButton.setVisible(false);
                                PlayButton.setVisible(false);
                                euxi.setText("");
                                basic_panel.repaint();
                                  
       
            
                                if (game.game_has_finished().charAt(0)!='n'){
                                    jTextField1.setText(game.game_has_finished());
                                    
                                    GrandTichuButton.setVisible(false);
                                    FoldButton.setVisible(false);
                                    
                                    basic_panel.repaint();
                                    return;
                                }
                                game.init_table();
                               init_buttons();
                               
                               game.Setantallages(false);
                                
        
                            }
                            for (int j=0;j<14;j++){
                            h[j]=0;
                        }
                        
                           
                        }
                        else{
                            playSound("buzz.wav");
                        }
                        
                }
                
     
                }
      }
      /* a class which is used for doing some action after a Fold button has been pushed */  
      private class FoldListener implements ActionListener 	{
                        /**
	 * <b>transformer(mutative)</b>:doing some action after Fold button has been pushed<br />
	 * <p><b>Postcondition:</b> doing some action after Fold button has been pushed</p>
	 *
     */
		public void actionPerformed(ActionEvent e) 	{
                        if (game.not_started()==true){
                            game.isready();
                            jTextField1.setText("Player "+ (game.seeTurn()+1)+ ": Grand Tichu or Fold");
                           
                            if (game.Get_isready()==4){
                                game.set_started();
                                jTextField1.setText("Player "+ (game.seeTurn()+1)+ ":Choose 3 Cards for Exchange");
                                mirasma();
                                
                            }
                        }
                        else if ((p_list.isEmpty()==true) && (game.tableIsEmpty()==false)){
                        if (game.set_Fold()==false){
                            playSound("buzz.wav");
                            return;
                        }
                        for (int j=0;j<14;j++){
                            h[j]=0;
                        }
                        
                        jTextField1.setText("It's Player's "+ (game.seeTurn()+1)+ " Turn");
                        if (game.Get_new_round()==true){
                            tablo.removeAll();
                            
                            basic_panel.repaint();
                            
                        }
                        }
                        else{
                            playSound("buzz.wav");
                        }
                }
      }
    /* a class which is used for doing some action after a Tichu button has been pushed */    
    private class TichuListener implements ActionListener 	{
                      /**
	 * <b>transformer(mutative)</b>:doing some action after Tichu or GrandTichu button has been pushed<br />
	 * <p><b>Postcondition:</b> doing some action after Tichu or GrandTichu button has been pushed</p>
	 *
     */
        @Override
		public void actionPerformed(ActionEvent e) 	{
                        
                         
                        if ((game.not_started()==true) && (e.getSource()==GrandTichuButton)) {
                            if (game.seeTurn()==0){
                                    
                                choice1.setVisible(true); 
                                imageURL= cldr.getResource("images/gt2.png");
                                choice1.setIcon(new ImageIcon(imageURL));
                                
                                }
                                else if (game.seeTurn()==1){
                                    
                                choice2.setVisible(true);       
                                imageURL= cldr.getResource("images/gt.png");
                                choice2.setIcon(new ImageIcon(imageURL));
                                
                                }
                                else if (game.seeTurn()==2){
                                    
                                choice3.setVisible(true);       
                                imageURL= cldr.getResource("images/gt2.png");
                                choice3.setIcon(new ImageIcon(imageURL));
                               
                                }
                                else if (game.seeTurn()==3){
                                    
                                choice4.setVisible(true);       
                                imageURL= cldr.getResource("images/gt.png");
                                choice4.setIcon(new ImageIcon(imageURL));
                                
                                }
                            game.setGrandTichu();
                            playSound("accept.wav");
                            jTextField1.setText("Player "+ (game.seeTurn()+1)+ ": Grand Tichu or Fold");
                             if (game.Get_isready()==4){
                                game.set_started();
                                mirasma();
                               jTextField1.setText("Player "+ (game.seeTurn()+1)+ ":Choose 3 Cards for Exchange");
                            }
                        }
                        else if (e.getSource()==GrandTichuButton){
                             playSound("buzz.wav");
                        }
                        if ((game.not_started()==false)&& (e.getSource()==TichuButton)){
                            if (game.setTichu()==true){
                                playSound("accept.wav");
                                if (game.seeTurn()==0){
                                    
                                choice1.setVisible(true);      
                                imageURL= cldr.getResource("images/tiic.png");
                                choice1.setIcon(new ImageIcon(imageURL));
                               
                                }
                                else if (game.seeTurn()==1){
                                    
                                choice2.setVisible(true);       
                                imageURL= cldr.getResource("images/tic.png");
                                choice2.setIcon(new ImageIcon(imageURL));
                               
                                
                                }
                                else if (game.seeTurn()==2){
                                    
                                choice3.setVisible(true);       
                                imageURL= cldr.getResource("images/tiic.png");
                                choice3.setIcon(new ImageIcon(imageURL));
                                
                                }
                                else if (game.seeTurn()==3){
                                    
                                choice4.setVisible(true);       
                                imageURL= cldr.getResource("images/tic.png");
                                choice4.setIcon(new ImageIcon(imageURL));
                                
                                }
                            }
                            else{
                                playSound("buzz.wav");
                            }
                        }
                }
      }
    /* a class which is used for doing some action after a Settings button has been pushed */    
    private class SettingsListener implements ActionListener 	{
                    /**
	 * <b>transformer(mutative)</b>:doing some action after New Game or Exit button has been pushed<br />
	 * <p><b>Postcondition:</b> doing some action after New Game or Exit button has been pushed</p>
	 *
     */
        @Override
		public void actionPerformed(ActionEvent e) 	{
                    if (e.getSource()==newGame){
                    MenuDialog J=new MenuDialog("Yes","No",
                            "Do you really want to play a new game?","New" );
                    if (J.choice()==1){
                            setVisible(false);    
                            new GraphicUI();
                    }
                        
                    }
                    else if (e.getSource()==Exit){
                        MenuDialog J=new MenuDialog("Yes","No",
                            "Do you really want to exit?","Exit" );
                        if (J.choice()==1){
                        System.exit(0);
                    }
                    }    
                }
    }  
               /**
	 * <b>transformer(mutative)</b>:play a sound<br />
	 * <p><b>Postcondition:</b> play a sound</p>
     *   *@ param soundName string
	 *
     */
   public void playSound(String soundName)
 {
   try 
   {
    imageURL= cldr.getResource("sounds/"+soundName);   
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(imageURL);
    Clip clip = AudioSystem.getClip( );
    clip.open(audioInputStream);
    clip.start( );
   }
   catch(Exception ex)
   {
     System.out.println("Error with playing sound.");
     ex.printStackTrace( );
   }
 }

}
