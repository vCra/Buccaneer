package main;

import helpers.Position;
import islands.Island;
import ports.Port;

/**
 * Created by awalker on 04/02/2017.
 */
class GameSquare {

	private Position position;
	private Port port;
	private Island island;
	
	/**
	 * Constructor.
	 * Takes two parameters which are used to create
	 * an object of class Position.
	 * Port and island instance variables are 
	 * initially set to null.
	 * 
	 * @param x
	 * @param y
	 */
	public GameSquare (int x, int y)
	{
		position = new Position(x, y);
		
		port = null;
		island = null;
	}

	/**
	 * Getter for position.
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Getter for port.
	 * @return port
	 */
	public Port getPort() {
		return port;
	}

	/**
	 * Getter for island.
	 * @return island
	 */
	public Island getIsland() {
		return island;
	}

	/**
	 * Setter for port.
	 * @param port becomes this.port
	 */
	public void setPort(Port port) {
		this.port = port;
	}

	/**
	 * Setter for island
	 * @param island becomes this.island
	 */
	public void setIsland(Island island) {
		this.island = island;
	}
	
	
}
