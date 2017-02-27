package buccaneer.cards;

import buccaneer.cards.ChanceCard;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.*;

/**
 * Stores a stack of buccaneer.cards, and allows the buccaneer.cards to be added and removed at will.
 */

//TODO: Implement CardDeck to keep track of the stack of buccaneer.cards
public class CardDeck<CardObject> {
    private Queue<CardObject> queue;

    public CardDeck() {
		this.queue = new LinkedList<>();
	}

	void addCard(CardObject card) {
        queue.add(card);
    }

    private void setQueue(Queue q){
        this.queue = q;
    }
    public CardObject removeCard() {
        return queue.poll();
    }

    public void importFromFile(){
        try {
            ClassLoader classLoader = getClass().getClassLoader(); //allows us to use resources
            File file = new File(classLoader.getResource("chanceCards.csv").getFile());
            FileReader csvFile = new FileReader(file);
            CSVReader csvReader = new CSVReader(csvFile); //Uses the file reader in lib/opencsv-x.x.jar
            List<ChanceCard> tempArray = new ArrayList<>();
            String [] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                tempArray.add(new ChanceCard( Integer.parseInt(nextLine[0]), nextLine[1]));
            }
            Collections.shuffle(tempArray);
            this.setQueue(new LinkedList<>(tempArray));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
