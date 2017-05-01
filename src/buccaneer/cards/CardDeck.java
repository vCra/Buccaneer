package buccaneer.cards;

import buccaneer.enumData.CardColor;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//TODO: Javadoc
/**
 * Stores a stack of buccaneer.cards, and allows the buccaneer.cards to be added and removed at will.
 * @param Queue<CardObject> queue - the card deck
 *
 */

public class CardDeck<CardObject> {
    private Queue<CardObject> queue;

    public CardDeck() {
        this.queue = new LinkedList<>();
    }

    public void addCard(CardObject card) {
        queue.add(card);
    }

    private void setQueue(Queue q) {
        this.queue = q;
    }

    public CardObject removeCard() {
        return queue.poll();
    }

    public void shuffle() {
        Collections.shuffle((List) queue);
    }

    public void importFromFile() {
        try {
            ClassLoader classLoader = getClass().getClassLoader(); //allows us to use resources
            File file = new File(classLoader.getResource("data/chanceCards.csv").getFile());
            FileReader csvFile = new FileReader(file);
            CSVReader csvReader = new CSVReader(csvFile); //Uses the file reader in lib/opencsv-x.x.jar
            List<ChanceCard> tempArray = new ArrayList<>();
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                tempArray.add(new ChanceCard(Integer.parseInt(nextLine[0]), nextLine[1]));
            }
            Collections.shuffle(tempArray);
            this.setQueue(new LinkedList<>(tempArray));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Generates crew cards and inserts them into the deck
     * They should be shuffled in another call afterwards
     */
     public void genCrewCards(){
        int id = 1;
        for (CardColor color : CardColor.values()){
            for (int value=1; value<4; value++){
                for (int i=0; i<5; i++) {
                    CrewCard card = new CrewCard(id, color, value);
                    addCard((CardObject) card);
                    id++;
                }
            }
        }
    }
}
