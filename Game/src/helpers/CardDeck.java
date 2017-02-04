package helpers;

import cards.CardObject;

import java.util.Queue;

/**
 * Created by aaw13 on 02/02/2017.
 * Stores a stack of cards, and allows the cards to be added and removed at will.
 */

//TODO: Implement CardDeck to keep track of the stack of cards
public class CardDeck {
    private Queue<CardObject> queue;

    void addCard(CardObject card) {
        queue.add(card);
    }

    CardObject removeCard() {
        return queue.poll();
    }

    void shuffleCards() {
        //TODO
    }

    void importFromFile(){}
}
