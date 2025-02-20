package display.view.brickbreakerview;

import display.view.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Collectors;
import java.nio.file.Path;

public class SavedGames extends JPanel {
    public static final long serialVersionUID = 111L;
	
    public static final Dimension SCREEN_FULL_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static double ratio = Scale.getRatioForResolution(SCREEN_FULL_SIZE.getWidth(), SCREEN_FULL_SIZE.getHeight());
    int scale = (int) (SCREEN_FULL_SIZE.getWidth() / SCREEN_FULL_SIZE.getHeight() * ratio);

    public ArrayList<String> saveFileNames = getSaveFileNames("src"+java.io.File.separator+"Saves");
    private GameFrame game_frame;
  
    private JButton menu = createStyledButton(" Back to Menu ");

    public SavedGames(GameFrame gameFrame){
        this.game_frame = gameFrame; 

        this.setLayout(new GridLayout(2,4));
        this.setBackground(new Color(30,30,30));
        for (String saveFileName : saveFileNames) {
            JButton saveButton = createStyledButton(saveFileName.substring(0, saveFileName.length() - 4)); // substring is used here to remove the ".txt" 
            saveButton.addActionListener((event) -> {
                game_frame.getCardlayout().show(game_frame.getPanelContainer(), "gamePanel");
                game_frame.loadGame(saveFileName);
            });
            this.add(saveButton);
            addMouseListener(saveButton);
            addImageToButton(saveButton,saveFileName.substring(0, saveFileName.length() - 4));
            saveButton.repaint();



            JButton deleteButton = createStyledButton("Delete: " + saveFileName.substring(0, saveFileName.length() - 4));
            deleteButton.addActionListener((event) -> {
                deleteSaveFile(saveFileName);
                deleteSaveFileImage(saveFileName.substring(0, saveFileName.length() - 4));
                game_frame.getCardlayout().show(game_frame.getPanelContainer(), "menuPanel");
                updateSaveFileNames();
            });
            this.add(deleteButton);
            addMouseListener(deleteButton);
            deleteButton.repaint();
        }

        this.menu.addActionListener((event) -> {
            gameFrame.getCardlayout().show(gameFrame.getPanelContainer(), "menuPanel");
        });
        addMouseListener(menu);

        this.add(menu);
    }

    
    public ArrayList<String> getSaveFileNames(String directory) {
        ArrayList<String> saveFileNames = new ArrayList<>();
        try {
            saveFileNames = (ArrayList<String>) Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveFileNames;
    }
    
    public void updateSaveFileNames() {
        this.saveFileNames = getSaveFileNames("src"+java.io.File.separator+"Saves");
        this.removeAll();
        menu = createStyledButton(" Back to Menu "); 

        this.setLayout(new GridLayout(2,4));
        this.setBackground(new Color(30,30,30));
        for (String saveFileName : saveFileNames) {
            JButton saveButton = createStyledButton(saveFileName.substring(0, saveFileName.length() - 4)); // substring is used here to remove the ".txt" 
            saveButton.addActionListener((event) -> {
                game_frame.getCardlayout().show(game_frame.getPanelContainer(), "gamePanel");
                game_frame.loadGame(saveFileName);
            });
            this.add(saveButton);
            addMouseListener(saveButton);
            addImageToButton(saveButton,saveFileName.substring(0, saveFileName.length() - 4));
            saveButton.repaint();



            JButton deleteButton = createStyledButton("Delete: " + saveFileName.substring(0, saveFileName.length() - 4));
            deleteButton.addActionListener((event) -> {
                deleteSaveFile(saveFileName);
                deleteSaveFileImage(saveFileName.substring(0, saveFileName.length() - 4));
                game_frame.getCardlayout().show(game_frame.getPanelContainer(), "menuPanel");
                updateSaveFileNames();
            });
            this.add(deleteButton);
            addMouseListener(deleteButton);
            deleteButton.repaint();
        }

        this.menu.addActionListener((event) -> {
            this.game_frame.getCardlayout().show(this.game_frame.getPanelContainer(), "classicGame");
        });
        addMouseListener(menu);

        this.add(menu);
    }

    private void deleteSaveFile(String fileName) {
        Path filePath = Paths.get("src"+java.io.File.separator+"Saves"+java.io.File.separator + fileName);
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteSaveFileImage(String fileName) {
        Path filePath = Paths.get("src"+java.io.File.separator+"SavesPics"+java.io.File.separator + fileName+".jpg");
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void addMouseListener(JButton b){
        b.addMouseListener(new ButtonMouseListener(b));
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

    private void addImageToButton(JButton button,String text){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src"+java.io.File.separator+"SavesPics"+java.io.File.separator+text+".jpg").getImage().getScaledInstance(200, 130, Image.SCALE_DEFAULT));
        button.setIcon(imageIcon);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
    }

}

