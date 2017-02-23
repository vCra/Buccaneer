package buccaneer.islands;

import buccaneer.helpers.Position;
import buccaneer.main.GameObject;

import java.util.ArrayList;

/**
 * Island Base Class
 */
/**
 * @author outca_000
 *
 */
public class Island implements GameObject {
	private ArrayList<Position> spaceOccupied;
	
	/**
	 * Constructor.
	 * Adds an ArrayList and fills it up with positions
	 * between the parameters.
	 * @param startPos
	 * @param endPos
	 */
	public Island (Position startPos, Position endPos)
	{
		spaceOccupied = new ArrayList<Position>();
		
		int startX = startPos.getX(), startY = startPos.getY();
		int endX = endPos.getX(), endY = endPos.getY();
		
		for (int i = startX; i < endX; i++)
		{
			for (int j = startY; j < endY; j++)
			{
				spaceOccupied.add(new Position(i,j));
			}
		}
	}

	/**
	 * Returns the spaceOccupied
	 * @return spaceOccupied
	 */
	public ArrayList<Position> getSpaceOccupied()
	{
		return spaceOccupied;
	}
}