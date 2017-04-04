package buccaneer.cards;

import buccaneer.helpers.TurnTracker;
import buccaneer.main.Game;
import buccaneer.main.GameApp;
import buccaneer.main.GameBoard;

/**
 * Chance Card
 */
//TODO: Manage storing of buccaneer.cards and card data/methods
//TODO: Javadoc

public class ChanceCard implements CardObject {
    private int id;
    private String text;

    public ChanceCard(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getID() {
        return id;
    }

    public String getText() {
        return text;
    }


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

    //TODO: take crew cards from deck
    private void gainCrewCards(buccaneer.main.Player player, int numOfCards) {

    }

    //TODO: return crew cards from player to deck
    private void loseNumOfCrewCards(buccaneer.main.Player player, int numOfCards) {

    }

    //TODO: gets amount of crew cards player has
    private int getValueOfCrewCards(buccaneer.main.Player player) {
        return 0;
    }

    //TODO: get value of crew cards player has
    private int getNumOfCrewCards(buccaneer.main.Player player) {
        return 0;
    }

    //TODO: player chooses treasure and then gives treasure to player
    private void gainTreasure(buccaneer.main.Player player, int valueOfTreasure, int numOfTreasures) {

    }

    //TODO: treasure is returned to treasure island
    private void loseTreasure(buccaneer.main.Player player, boolean mostValuable) {

    }

    //TODO: get closest player to player with chance card
    private buccaneer.main.Player getClosestPlayer(buccaneer.main.Player player) {
        return null;
    }

    //TODO: gets other player at treasure island if multiple player with chance card chooses
    private buccaneer.main.Player getOtherPlayerAtTreasureIsland(buccaneer.main.Player player) {
        return null;
    }

    //TODO: Player with chance card picks another player
    private buccaneer.main.Player chooseOtherPlayer() {
        return null;
    }

    //TODO: Chance card is added to player object
    private void keepCard(buccaneer.main.Player player) {

    }

    //TODO: treasure is taken from player and added to flat island
    private void sendTreasureToFlatIsland(buccaneer.main.Player player, buccaneer.treasure.Treasure treasure) {

    }

    //TODO: crew card is taken from player and added to flat island
    private void sendCrewCardToFlatIsland(buccaneer.main.Player player, buccaneer.cards.CrewCard card) {

    }

    //TODO: player makes a choice treasure or crew
    private Boolean treasureORcrew(buccaneer.main.Player player) {
        return null;
    }
}
