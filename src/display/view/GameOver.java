package display.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.breakout.Breakout;

public class GameOver extends JPanel {
    public static final long serialVersionUID = 51L;

    public static final Dimension SCREEN_FULL_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static double ratio = Scale.getRatioForResolution(SCREEN_FULL_SIZE.getWidth(), SCREEN_FULL_SIZE.getHeight());
    int scale = (int) (SCREEN_FULL_SIZE.getWidth() / SCREEN_FULL_SIZE.getHeight() * ratio);
    public static final Dimension BUTTON_SIZE = new Dimension((int)(ratio * 23),(int)(ratio * 8)); 
    private JButton exit = createStyledButton(" Exit ");
    private JButton backToMenu = createStyledButton(" Back to Menu ");
    private JButton restartLevel = createStyledButton(" Restart Level ");
    transient private BufferedImage backgroundImage; // background image 


    public GameOver(GameFrame frame){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(SCREEN_FULL_SIZE); 

         try {
            backgroundImage = ImageIO.read(new File(Breakout.ASSETS_PATH + "images" + java.io.File.separator + "entities" + java.io.File.separator + "GameOver2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

         //settings exit button 
         this.exit.setPreferredSize(BUTTON_SIZE);
         this.exit.addActionListener((event) ->{
             System.exit(0);
         });

         //setting back to menu button 
         this.backToMenu.setPreferredSize(BUTTON_SIZE);
         this.backToMenu.addActionListener(e -> {
            if (frame.getNumberOfTheGame() == 0){
                frame.getBreakoutGame().endGame();
                frame.getGamePanel().getGameZone().removeAll();
                
                frame.getGamePanel().getGameZone().removeAll();
                frame.getGamePanel().getMenu().setVisible(false);
                frame.getGamePanel().getGameZone().setVisible(true);
                //frame.getBreakoutGame().setLife(3);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //frame.getCardlayout().show(frame.getPanelContainer(), "menuPanel");
                        frame.getCardlayout().show(frame.getPanelContainer(), "homePage");
                    }
                });
            }else if(frame.getNumberOfTheGame() == 1 ){
                frame.getSpaceInvaderGame().clearGameComponents();
                frame.getGamePanel().getGameZone().removeAll();
                frame.getSpaceInvaderGame().setLife(5);
                frame.getSpaceInvaderGame().setnbEnemies(1);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        frame.getCardlayout().show(frame.getPanelContainer(), "homePage");
                    }
                });
            }

        });

        this.restartLevel.setPreferredSize(BUTTON_SIZE);
        this.restartLevel.addActionListener(e -> {
            if (frame.getNumberOfTheGame() == 0){
                frame.getBreakoutGame().clearGameComponents();
                frame.getGamePanel().getGameZone().removeAll();
                frame.getBreakoutGame().setLife(3);
                frame.getBreakoutGame().setNbBricks(1);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run (){
                        frame.getCardlayout().show(frame.getPanelContainer(), "gamePanel");
                        frame.startBreakoutGame(frame.getBreakoutGame().getLevel());
                    }
                });
            }
            else if(frame.getNumberOfTheGame() == 1 ){
                frame.getSpaceInvaderGame().clearGameComponents();
                frame.getGamePanel().getGameZone().removeAll();
                frame.getSpaceInvaderGame().setLife(5);
                frame.getSpaceInvaderGame().setnbEnemies(1);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        frame.getCardlayout().show(frame.getPanelContainer(), "gamePanel");
                        frame.startSpaceInvaderGame();
                    }
                });
            }
        });

        this.exit.addMouseListener(new ButtonMouseListener(this.exit));
        this.backToMenu.addMouseListener(new ButtonMouseListener(this.backToMenu));
        this.restartLevel.addMouseListener(new ButtonMouseListener(this.restartLevel));

        this.add(this.exit);
        this.add(this.backToMenu);
        this.add(this.restartLevel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Ubuntu", Font.BOLD, 22));
        button.setPreferredSize(new Dimension((int)(ratio * 30),(int)(ratio * 6)));
        button.setMaximumSize(new Dimension((int)(ratio * 30),(int)(ratio * 6)));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false); 
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false);
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
