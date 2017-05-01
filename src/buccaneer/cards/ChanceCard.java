package buccaneer.cards;

import buccaneer.helpers.Receivable;
import buccaneer.main.Game;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Chance Card
 */
//TODO: Manage storing of buccaneer.cards and card data/methods
//TODO: Javadoc


public class ChanceCard extends Receivable implements CardObject {
    private int id;
    private String text;

    public ChanceCard(int id, String text) {
        this.id = id;
        this.text = text;
        super.image = null;
        loadImage();
    }
    // Returns the ID
    public int getID() {
        return id;
    }
    // Returns the text on the card
    public String getText() {
        return text;
    }
    // Loads the image of the chance card
    private void loadImage() {
        try {
            //Using this while we don't have a chance card picture
            File file = new File(getClass().getResource("/images/crewcards/CrewCard_Black1.png").toURI());
            super.image = ImageIO.read(file);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    /**
     * Excutes the chance cards effect
     * @param g - The game that being played
     */
    public void executeChanceCard(Game g) {
        //TODO: add in method calls to perform correct functionality, some more methods may need to be written
        //FIXME: Reduce Cyclomatic Complecity if posible
        switch(id) {
            case 1:        //Move ship 5 squares away, choose direction at end
                break;
            case 2:        //Pick other player, they give over 3 crew cards
                break;
            case 3:        //Move to mud bay, if crew cards < 3 then gain 4 crew cards
                g.moveShip(g.getCurrentPlayer().getPlayerShip(), g.getGameBoard().getMudBay().getPosition());

            case 4:        //Move to cliff creak, if crew cards < 3 then gain 4 crew cards
                g.moveShip(g.getCurrentPlayer().getPlayerShip(), g.getGameBoard().getCliffCreek().getPosition());
            case 5:        //Move to home port, if crew cards < 3 then gain 4 crew cards
                g.moveShip(g.getCurrentPlayer().getPlayerShip(), g.getCurrentPlayer().getPort().getLocation());

            case 6:        //Move to port in direction facing, if crew cards < 3 then gain 4 crew cards
                break;
            case 7:        //1 treasure OR 2 crew cards are given to nearest ship
                break;
            case 8:        //1 treasure OR 2 crew cards are sent to flat island
                break;
            case 9:        //Most valuable treasure (if null highest crew card) sent to flat island
                break;
            case 10:       //Highest crew card sent to pirate island
                break;
            case 11:       //Take treasure up to 5 in value OR 2 crew cards
                break;
            case 12:       //Take treasure up to 4 in value OR 2 crew cards
                break;
            case 13:       //Take treasure up to 5 in value OR 2 crew cards
                break;
            case 14:       //Take treasure up to 7 in value OR 3 crew cards
                break;
            case 15:       //Take 2 crew cards
                g.getCurrentPlayer().addCrewCard(g.getGameBoard().getPirateIsland().getTopCard());
            case 16:       //Take treasure up to 7 in value AND reduce crew to 10
                break;
            case 17:       //Take treasure up to 6 in value AND reduce crew to 11
                break;
            case 18:       //Take treasure up to 4 in value AND if crew total < 7 take 2 crew cards
                break;
            case 19:       //Replace crew cards in hand for same amount of crew cards
                break;
            case 20:       //if other player at treasure island {exchange 2 crew cards with player} else {lose 2 crew cards}
                break;
            case 21:       //Long John Silver
                break;
            case 22:       //All players if amount of crew cards > 7 then lose crew cards until = 7
                break;
            case 23:       //Keep this card, can be traded at port for (treasure OR crew) up to 5 value
                g.getCurrentPlayer().addChanceCard(this);
                break;
            case 24:       //Keep this card, can be traded at port for (treasure OR crew) up to 4 value
                g.getCurrentPlayer().addChanceCard(this);
            case 25:       //This is identical to 26 so remove break as it has the same functionality
                break;
            case 26:       //Keep this card, if at pirate island then take treasure up to 7 in value
                g.getCurrentPlayer().addChanceCard(this);
            case 27:       //Take treasure up to 5 in value OR 3 crew cards
                break;
            case 28:       //Take 2 crew cards
                break;
        }
    }

    /**
     *Adds Crew cards to the player's hand
     *@param player - The player with the chance card
     *@param numOfCards - The number of cards being added
     */
    //TODO: take crew cards from deck
    private void gainCrewCards(buccaneer.main.Player player, int numOfCards) {

    }
    /**
     *Removes Crew cards from the player's hand
     *@param player - the player with the chance card
     */
    //TODO: return crew cards from player to deck
    private void loseNumOfCrewCards(buccaneer.main.Player player, int numOfCards) {

    }
    /**
     *Return the value of the crew cards in the player's hand
     @param player - The player with the chance card
     */
    //TODO: gets amount of crew cards player has
    private int getValueOfCrewCards(buccaneer.main.Player player) {
        return 0;
    }

    /**
     *Return the amount of crew cards in the player's hand
     @param player - The player with the chance card
     */
    //TODO: get value of crew cards player has
    private int getNumOfCrewCards(buccaneer.main.Player player) {
        return 0;
    }

    /**
     *Add treasure to the player's hold
     @param player - The player with the chance card
     @param numOfTreasures - The number of treasures the player gains
     @param valueOfTreasure - The value of the treasure the player gains
     */
    //TODO: player chooses treasure and then gives treasure to player
    private void gainTreasure(buccaneer.main.Player player, int valueOfTreasure, int numOfTreasures) {

    }
    /**
     *Takes treasure from the player's hand and return it to Treasure Island
     *@param player - The player with the chance card
     *@param mostValuable - The most valuable treasure the player has
     */
    //TODO: treasure is returned to treasure island
    private void loseTreasure(buccaneer.main.Player player, boolean mostValuable) {

    }
    /**
     *Returns the player closet to the player with the chance card
     *@param player - The player with the chance card
     */
    //TODO: get closest player to player with chance card
    private buccaneer.main.Player getClosestPlayer(buccaneer.main.Player player) {
        return null;
    }

    /**
     *Returns the other player at Treasure Island
     *@param player - The player with the chance card
     */
    //TODO: gets other player at treasure island if multiple player with chance card chooses
    private buccaneer.main.Player getOtherPlayerAtTreasureIsland(buccaneer.main.Player player) {
        return null;
    }

    /**
     *Player with the chance card chooses another player
     */
    //TODO: Player with chance card picks another player
    private buccaneer.main.Player chooseOtherPlayer() {
        return null;
    }

    /**
     *Adds the chance to the player's hand
     *@param player - The player with the chance card
     */
    //TODO: Chance card is added to player object
    private void keepCard(buccaneer.main.Player player) {

    }
    /**
     *Takes treasure out of the player's hold and places it on flat Island
     *@param player - The player with the chance card
     *@param treasure - The treasure that is being sent back to flat island
     */
    //TODO: treasure is taken from player and added to flat island
    private void sendTreasureToFlatIsland(buccaneer.main.Player player, buccaneer.treasure.Treasure treasure) {

    }
    /**
     *Takes a crew card out of the player's hand and adds it to Flat Island
     *@param player - The player with the chance card
     *@param card - The crew card that is being sent back to flat island
     */
    //TODO: crew card is taken from player and added to flat island
    private void sendCrewCardToFlatIsland(buccaneer.main.Player player, buccaneer.cards.CrewCard card) {

    }

    /**
     *Allows the player to choose a treasure or crew card
     *@param player - The player with the chance card
     */
    //TODO: player makes a choice treasure or crew
    private Boolean treasureORcrew(buccaneer.main.Player player) {
        return null;
    }
}
