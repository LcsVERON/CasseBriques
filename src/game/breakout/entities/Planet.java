package game.breakout.entities;

import java.awt.Image;

import javax.swing.ImageIcon;

import display.engine.rules.PhysicalObject;
import display.engine.shapes.Circle;
import display.engine.utils.Vector2D;
import game.breakout.Breakout;
import game.breakout.entities.rules.Entity;
import javax.swing.Timer;
import java.awt.event.ActionEvent;

/**
 * Represents a planet entity in the breakout game.
 */
public class Planet extends Entity {
    public static final long serialVersionUID = 1234L;
    transient public static final Image PLANET_IMAGE = new ImageIcon(Breakout.ASSETS_PATH + "images" + java.io.File.separator + "entities" + java.io.File.separator + "etoileNoire.png").getImage();
    private int health=5;

    /**
     * Constructs a Planet object.
     * 
     * @param image    The image of the planet.
     * @param size     The size of the planet.
     * @param mass     The mass of the planet.
     * @param position The position of the planet.
     * @param movable  Indicates if the planet is movable.
     * @param hp       The health points of the planet.
     */
    public Planet(
        Image image,
        int size,
        double mass,
        Vector2D position,
        boolean movable,
        int hp
    ) {
        super(mass, position, movable, new Circle(image, (int)position.getX(), (int)position.getY(), size, size));
        this.health = hp;
    }

    /**
     * Performs the planet explosion animation and destroys the planet after a delay.
     */
    public void planetExplosion() {
        this.setActive(false);
        Image image = new ImageIcon(
            Breakout.ASSETS_PATH + "images" + java.io.File.separator + "entities" + java.io.File.separator + "gifExplosion.gif"
        ).getImage();
        this.getRepresentation().setImage(image);
        Timer timer = new Timer(2000, (ActionEvent e) -> {
            this.destroy();
        });

        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Handles the collision between the planet and another physical object.
     * 
     * @param objectB The physical object that collided with the planet.
     */
    @Override
    public void collided(PhysicalObject objectB) {
        this.health--;

        if (this.health <= 0) {
            Vector2D distance = objectB.getPosition().subtract(this.getPosition());
            objectB.applyForce(distance.normalize().multiply(50000));
            objectB.setAcceleration(new Vector2D(0, 0));
            this.planetExplosion();
        }
    }
}
