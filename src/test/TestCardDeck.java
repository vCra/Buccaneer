import buccaneer.cards.CardDeck;
import buccaneer.cards.CrewCard;
import buccaneer.enumData.CardColor;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestCardDeck {

    @Test
    public void testAddCard() {
        CardDeck<CrewCard> cards = new CardDeck<>();
        CrewCard card = new CrewCard(1, CardColor.Black, 2);
        cards.addCard(card);
        assertTrue(cards.removeCard() == card);
    }
}