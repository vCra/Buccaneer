package buccaneer.cards;

import buccaneer.helpers.Receivable;
import buccaneer.main.Game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static buccaneer.cards.ChanceCardHelper.*;

/**
 * ChanceCard.java 23/02/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Handles all the chance card functionality
 *
 * @author aaw13
 * @author jaj48
 * @version 1.0
 * @see CardObject
 * @see Receivable
 *
 */
//TODO: Javadoc

public class ChanceCard extends Receivable implements CardObject {
    private final int id;
    private final String text;

    public ChanceCard(int id, String text) {
        this.id = id;
        this.text = text;
        super.image = null;

        loadImage();

    }

    public int getID() {
        return id;
    }

    public String getText() {
        return text;
    }

    private void loadImage() {
        try {
            //Using this while we don't have a chance card picture
            File file = new File(getClass().getResource("/images/cards/chanceCards/hook.png").toURI());
            super.image = ImageIO.read(file);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void executeChanceCard(Game g) {
        //TODO: add in method calls to perform correct functionality, some more methods may need to be written
        System.out.println("ChanceCard #" + id);

        boolean keep = false;
        switch(id) {
            case 1:        //Move ship 5 squares away, choose direction at end
                chanceCard1(g);
                break;
            case 2:        //Pick other player, they give over 3 crew cards
                chanceCard2(g);
                break;
            case 3:        //Move to mud bay, if crew cards < 3 then gain 4 crew cards
                chanceCard3(g);
                break;
            case 4:        //Move to cliff creak, if crew cards < 3 then gain 4 crew cards
                chanceCard4(g);
                break;
            case 5:        //Move to home port, if crew cards < 3 then gain 4 crew cards
                chanceCard5(g);
                break;
            case 6:        //Move to port in direction facing, if crew cards < 3 then gain 4 crew cards
                chanceCard6(g);
                break;
            case 7:        //1 treasure OR 2 crew cards are given to nearest ship
                chanceCard7(g);
                break;
            case 8:        //1 treasure OR 2 crew cards are sent to flat island
                chanceCard8(g);
                break;
            case 9:        //Most valuable treasure (if null highest crew card) sent to flat island
                chanceCard9(g);
                break;
            case 10:       //Highest crew card sent to pirate island
                chanceCard10(g);
                break;
            case 11:       //Take treasure up to 5 in value OR 2 crew cards
                chanceCard11(g);
                break;
            case 12:       //Take treasure up to 4 in value OR 2 crew cards
                chanceCard12(g);
                break;
            case 13:       //Take treasure up to 5 in value OR 2 crew cards
                chanceCard13(g);
                break;
            case 14:       //Take treasure up to 7 in value OR 3 crew cards
                chanceCard14(g);
                break;
            case 15:       //Take 2 crew cards
                chanceCard15(g);
                break;
            case 16:       //Take treasure up to 7 in value AND reduce crew to 10
                chanceCard16(g);
                break;
            case 17:       //Take treasure up to 6 in value AND reduce crew to 11
                chanceCard17(g);
                break;
            case 18:       //Take treasure up to 4 in value AND if crew total < 7 take 2 crew cards
                chanceCard18(g);
                break;
            case 19:       //Replace crew cards in hand for same amount of crew cards
                chanceCard19(g);
                break;
            case 20:       //if other player at treasure island {exchange 2 crew cards with player} else {lose 2 crew cards}
                chanceCard20(g); // I NEED HELP WITH THIS - aaw13
                break;
            case 21:       //Long John Silver
                g.getCurrentPlayer().addChanceCard(this);
                keep = true;
                break;
            case 22:       //All players if amount of crew cards > 7 then lose crew cards until = 7
                chanceCard22(g);
                break;
            case 23:       //Keep this card, can be traded at port for (treasure OR crew) up to 5 value
                g.getCurrentPlayer().addChanceCard(this);
                keep = true;
                break;
            case 24:       //Keep this card, can be traded at port for (treasure OR crew) up to 4 value
                g.getCurrentPlayer().addChanceCard(this);
                keep = true;
                break;
            case 25:       //This is identical to 26 so remove break as it has the same functionality
                g.getCurrentPlayer().addChanceCard(this);
                keep= true;
                break;
            case 26:       //Keep this card, if at pirate island then take treasure up to 7 in value
                g.getCurrentPlayer().addChanceCard(this);
                keep = true;
                break;
            case 27:       //Take treasure up to 5 in value OR 3 crew cards
                chanceCard27(g);
                break;
            case 28:       //Take 2 crew cards
                chanceCard28(g);
            break;
        }
        if (!keep){
            g.getGameBoard().getTreasureIsland().addChanceCard(this);
        }
    }

}
