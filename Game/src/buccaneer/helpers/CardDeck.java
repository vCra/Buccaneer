package buccaneer.helpers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Stores a stack of buccaneer.cards, and allows the buccaneer.cards to be added and removed at will.
 */

//TODO: Implement CardDeck to keep track of the stack of buccaneer.cards
public class CardDeck<CardType> {
    private Queue<CardType> queue;

    public CardDeck() {
		this.queue = new LinkedList<CardType>();
	}

    public void createADeck(Queue<CardType> queue) {
		this.queue = queue;
	}

	void addCard(CardType card) {
        queue.add(card);
    }

    public CardType removeCard() {
        return queue.poll();
    }

    void shuffleCards() {
        //TODO
    }

    void importFromFile(){}
}
