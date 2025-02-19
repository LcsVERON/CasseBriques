package game.pong.entities;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import display.engine.rules.PhysicalObject;
import display.engine.shapes.Rectangle;
import display.engine.utils.Vector2D;
import game.pong.Pong;
import game.pong.entities.rules.Entity;

public class Player extends Entity {
	public static final Image DEFAULT_IMAGE = new ImageIcon(Pong.ASSETS_PATH + "images" + java.io.File.separator + "entities" + java.io.File.separator + "player.png").getImage();
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final int DEFAULT_SIZE = 100;
	public static final int MIN_SIZE = DEFAULT_SIZE - (int)(0.2f * DEFAULT_SIZE);
	public static final int MAX_SIZE = DEFAULT_SIZE + (int)(0.3f * DEFAULT_SIZE);
	public static final int DEFAULT_POS_X = 300;
	public static final int DEFAULT_POS_Y = 200;
	public static final int DEFAULT_SPEED = 5;
	public static final int MIN_SPEED = DEFAULT_SPEED - (int)(0.5f * DEFAULT_SPEED);
	public static final int MAX_SPEED = DEFAULT_SPEED + (int)(1.0f * DEFAULT_SPEED);
	private Vector2D lastPos = new Vector2D(630, 700);
	private int intSpeed = DEFAULT_SPEED;

	/**
	 * Instantiates a new Player object with the specified color, size, speed, mass, position, and movability.
	 * 
	 * @param color the color of the player object
	 * @param size the size of the player
	 * @param speed the speed of the player
	 * @param mass the mass of the player
	 * @param position the initial position of the player
	 * @param movable indicates whether the player is movable or not
	 */
	public Player(
		Color color,
		int size,
		int speed,
		double mass,
		Vector2D position,
		boolean movable
	) {
		super(mass, position, movable, new Rectangle(color, (int)position.getX(), (int)position.getY(), size/5, size));
		this.intSpeed = speed;
	}

	/**
	 * Instantiates a new Player object with the specified image, size, speed, mass, position, and movability.
	 * 
	 * @param image the image representing the player
	 * @param size the size of the player
	 * @param speed the speed of the player
	 * @param mass the mass of the player
	 * @param position the initial position of the player
	 * @param movable indicates whether the player is movable or not
	 */
	public Player(
		Image image,
		int size,
		int speed,
		double mass,
		Vector2D position,
		boolean movable
	) {
		super(mass, position, movable, new Rectangle(image, (int)position.getX(), (int)position.getY(), size/5, size));
		this.intSpeed = speed;
	}

	/**
	 * Instantiates a new Player object with the specified size, mass, position, and movability.
	 * 
	 * @param size the size of the player
	 * @param mass the mass of the player
	 * @param position the initial position of the player
	 * @param movable indicates whether the player is movable or not
	 */
	public Player(
		int size,
		double mass,
		Vector2D position,
		boolean movable
	) {
		this(DEFAULT_IMAGE, size, DEFAULT_SPEED, mass, position, movable);
	}

	/**
	 * Instantiates a new Player object with the default image, size, speed, mass, position, and movability.
	 * 
	 * @param mass the mass of the player
	 * @param position the initial position of the player
	 * @param movable indicates whether the player is movable or not
	 */
	public Player(
		double mass,
		Vector2D position,
		boolean movable
	) {
		this(DEFAULT_IMAGE, DEFAULT_SIZE, DEFAULT_SPEED, mass, position, movable);
	}

	/**
	 * Instantiates a new Player object with the specified color, position, size, and speed.
	 * 
	 * @param color the color of the player object
	 * @param posX the initial x position of the player
	 * @param posY the initial y position of the player
	 * @param size the size of the player
	 * @param speed the speed of the player
	 */
	public Player(
		Color color,
		int posX,
		int posY,
		int size,
		int speed
	) {
		super(new Rectangle(color, posX, posY, size/5, size));
		this.intSpeed = speed;
	}

	/**
	 * Instantiates a new Player object with the specified image, position, size, and speed.
	 * 
	 * @param image the image representing the player
	 * @param posX the initial x position of the player
	 * @param posY the initial y position of the player
	 * @param size the size of the player
	 * @param speed the speed of the player
	 */
	public Player(
		Image image,
		int posX,
		int posY,
		int size,
		int speed
	) {
		super(new Rectangle(image, posX, posY, size/5, size));
		this.intSpeed = speed;
	}

	/**
	 * Instantiates a new Player object with the specified position and size.
	 * 
	 * @param posX the initial x position of the player
	 * @param posY the initial y position of the player
	 * @param size the size of the player
	 */
	public Player(
		int posX,
		int posY,
		int size
	) {
		this(DEFAULT_IMAGE, posX, posY, size, DEFAULT_SPEED);
	}

	/**
	 * Instantiates a new Player object with the specified position.
	 * 
	 * @param posX the initial x position of the player
	 * @param posY the initial y position of the player
	 */
	public Player(
		int posX,
		int posY
	) {
		this(DEFAULT_IMAGE, posX, posY, DEFAULT_SIZE, DEFAULT_SPEED);
	}

	/**
	 * Instantiates a new Player object with the default values.
	 */
	public Player() {
		this(DEFAULT_IMAGE, DEFAULT_POS_X, DEFAULT_POS_Y, DEFAULT_SIZE, DEFAULT_SPEED);
	}

	/**
	 * Returns the speed of the player.
	 * 
	 * @return the speed in int of the player
	 */
	public int getIntSpeed() {
		return this.intSpeed;
	}

	/**
	 * Returns the minimal speed of the player.
	 * 
	 * @return the minimal speed in int of the player
	 */
	public int getMinSpeed() {
		return MIN_SPEED;
	}

	/**
	 * Returns the maximal speed of the player.
	 * 
	 * @return the maximal speed in int of the player
	 */
	public int getMaxSpeed() {
		return MAX_SPEED;
	}

	/**
	 * Returns the last position of the player.
	 * 
	 * @return a Vector2D corresponding to the player's last position
	 */
	public Vector2D getLastPos(){
		return this.lastPos;
	}

	/**
	 * Sets the last position of the player.
	 * 
	 * @param vect a Vector2D corresponding to the player's last position
	 */
	public void setLastPos(Vector2D vect){
		this.lastPos = vect;
	}

	/**
	 * Sets the speed of the player.
	 * 
	 * @param intSpeed the speed in int of the player
	 */
	public void setIntSpeed(int intSpeed) {
		this.intSpeed = intSpeed;
	}

	/**
	 * Updates the velocity of the player using the previous position and time.
	 * 
	 * @param deltaTime the time difference between the current and previous frame
	 */
	@Override
	public void updateVelocity(double deltaTime) {
		this.speed = ((this.position.add(this.getLastPos().multiply(-1))).multiply(1/deltaTime));
	}

	/**
	 * Handles the collision between the player and another physical object.
	 * 
	 * @param object the physical object that the player collided with
	 */
	@Override
	public void collided(PhysicalObject object) {
		super.collided();
	}
}
