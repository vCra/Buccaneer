import buccaneer.cards.CardDeck;
import buccaneer.cards.ChanceCard;
import buccaneer.cards.CrewCard;
import buccaneer.enumData.CardColor;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jakub Janas on 3/25/2017.
 */
public class CardDeckTest
{
    @Test
    public void addAndRemoveCardTest()
    {
        CardDeck<CrewCard> deck = new CardDeck<CrewCard>();
        CrewCard card = new CrewCard(1, CardColor.Black, 1);

        deck.addCard(card);

        assertEquals(deck.removeCard(), card);
    }

    @Test
    public void importFromFileTest()
    {
        CardDeck<ChanceCard> deck = new CardDeck<ChanceCard>();

        deck.importFromFile();

        ArrayList<ChanceCard> cards = new ArrayList<ChanceCard>();

        for (int i = 0; i < 28; i++)
        {
            cards.add(deck.removeCard());
        }

        assertEquals(cards.size(), 28);
    }

    @Test
    public void shuffleTest()
    {
        CardDeck<CrewCard> deck = new CardDeck<CrewCard>();

        CrewCard cardOne = new CrewCard(1, CardColor.Black, 1);
        CrewCard cardTwo = new CrewCard(2, CardColor.Black, 2);
        CrewCard cardThree = new CrewCard(3, CardColor.Black, 3);

        deck.addCard(cardOne);
        deck.addCard(cardTwo);
        deck.addCard(cardThree);

        deck.shuffle();

        ArrayList<CrewCard> list = new ArrayList<>();
        list.add(cardOne);
        list.add(cardTwo);
        list.add(cardThree);

        ArrayList<CrewCard> cards = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            cards.add(deck.removeCard());
        }

        assertNotSame(list, cards);
    }

    @Test
    public void fifoTest()
    {
        CardDeck<CrewCard> deck = new CardDeck<CrewCard>();

        CrewCard cardOne = new CrewCard(1, CardColor.Black, 1);
        CrewCard cardTwo = new CrewCard(2, CardColor.Black, 2);
        deck.addCard(cardOne);
        deck.addCard(cardTwo);

        assertEquals(deck.removeCard(), cardOne);
    }
}
