package display.view;

import display.view.brickbreakerview.GamePanel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.breakout.Breakout;



public class MenuInGame extends JPanel {
    public static final long serialVersionUID = 54L;

    public static final Dimension SCREEN_FULL_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static double ratio = Scale.getRatioForResolution(SCREEN_FULL_SIZE.getWidth(), SCREEN_FULL_SIZE.getHeight());
    int scale = (int) (SCREEN_FULL_SIZE.getWidth() / SCREEN_FULL_SIZE.getHeight() * ratio);

    private JButton resumeButton = createButton("Reprendre");
    private JButton BackToMenuButton = createButton("Retour Accueil");
    private JButton settings = createButton("Settings");
    private JPanel buttonContainer = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dessiner l'image de fond
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    };
    transient private BufferedImage backgroundImage; // background image 
	public static final Dimension MENU_ZONE = new Dimension(SCREEN_FULL_SIZE.width*4/5, SCREEN_FULL_SIZE.height*9/10);

    public MenuInGame(GameFrame frame, GamePanel pane){

        try {
            backgroundImage = ImageIO.read(new File(Breakout.ASSETS_PATH + "images" + java.io.File.separator + "entities" + java.io.File.separator + "MenuInGame.jpg")); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.resumeButton.addActionListener(e -> {
            if(frame.getNumberOfTheGame() == 0){
                frame.getBreakoutGame().resume();
            }else if( frame.getNumberOfTheGame() == 1){
                frame.getSpaceInvaderGame().resume();
            }
           pane.resumeGamePanel();
           pane.requestFocus();
        });
        this.resumeButton.addMouseListener(new MenuInGameListener(this.resumeButton));


        this.BackToMenuButton.addActionListener(e -> {
            if(frame.getNumberOfTheGame() == 0){
                frame.getBreakoutGame().clearGameComponents();
            }else if( frame.getNumberOfTheGame() == 1){
                frame.getSpaceInvaderGame().clearGameComponents();
            }else if (frame.getNumberOfTheGame() == 2){
                frame.getPongGame().clearGameComponents();
            }
            pane.getGameZone().removeAll();
            pane.getMenu().setVisible(false);
            pane.getGameZone().setVisible(true);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if(frame.getNumberOfTheGame() == 0){
                        frame.getCardlayout().show(frame.getPanelContainer(), "menuPanel");
                    }else if( frame.getNumberOfTheGame() == 1){
                        frame.getCardlayout().show(frame.getPanelContainer(), "homePage");
                    }else if (frame.getNumberOfTheGame() == 2){
                        frame.getCardlayout().show(frame.getPanelContainer(), "homePage");
                    }
                }
            });
        });
        this.BackToMenuButton.addMouseListener(new MenuInGameListener(this.BackToMenuButton));

        this.settings.addActionListener(e -> {
            frame.getCardlayout().show(frame.getPanelContainer(), "SettingsInGame");
        });
        this.settings.addMouseListener(new MenuInGameListener(this.settings));


        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
        buttonContainer.setBorder(new EmptyBorder((int)(ratio *1.5), (int)(ratio *1.5), (int)(ratio *1.5), (int)(ratio *1.5)));
        buttonContainer.add(Box.createVerticalGlue());
        buttonContainer.add(resumeButton);
        buttonContainer.add(Box.createVerticalStrut((int)(ratio * 3)));
        buttonContainer.add(BackToMenuButton);
        buttonContainer.add(Box.createVerticalStrut((int)(ratio * 3)));
        buttonContainer.add(settings);
        buttonContainer.add(Box.createVerticalGlue());

       


       this.setLayout(new BorderLayout());
       this.setPreferredSize(MENU_ZONE);
       this.add(buttonContainer, BorderLayout.CENTER);
    }


    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Ubuntu", Font.BOLD, (int)(ratio * 3)));
        button.setPreferredSize(new Dimension((int)(ratio * 30),(int)(ratio * 6)));
        button.setMaximumSize(new Dimension((int)(ratio * 30),(int)(ratio * 6)));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false); 
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        return button;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}


class MenuInGameListener extends MouseAdapter {
    private JButton button;

    public static final Dimension SCREEN_FULL_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static double ratio = Scale.getRatioForResolution(SCREEN_FULL_SIZE.getWidth(), SCREEN_FULL_SIZE.getHeight());
    int scale = (int) (SCREEN_FULL_SIZE.getWidth() / SCREEN_FULL_SIZE.getHeight() * ratio);

    public static final Dimension BUTTON_SIZE = new Dimension((int)(ratio * 38),(int)(ratio * 6)); 

    public MenuInGameListener(JButton button) {
        this.button = button;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Action à effectuer lorsque la souris entre dans la zone du bouton
        button.setFont(new Font("Ubuntu", Font.BOLD, (int)(ratio * 2.4)));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Action à effectuer lorsque la souris sort de la zone du bouton
        button.setFont(new Font("Ubuntu", Font.BOLD, (int)(ratio * 2)));
    }
}


