/*
 * All components of this software is dual licensed under GNU General Public License v2 (GPL-2) 
 * for personal usage for commercial usage you must contact the author prior distribution, usage.
 *
 */
package gammalottery;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * FXML TableView data class
 * 
 * @author:Rodrigo Mansueli Nunes  {@literal <mansueli@ualberta.ca>}
 * <a href="http://kyllo.com.br">kyllo.com.br</a>
 *
 **/

public class LotteryObject {
    private final BooleanProperty winner;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty entries;
    
    /**
     * Constructor
     * @param name of the participant
     * @param entries number of entries for this lottery
     */
    public LotteryObject(String name, int entries) {
        this.name = new SimpleStringProperty(name);
        this.winner = new SimpleBooleanProperty(false);
        this.entries = new SimpleIntegerProperty(entries);
    }
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public int getEntries() {
        return entries.get();
    }

    public void setEntries(int entries) {
        this.entries.set(entries);
    }

    public boolean getWinner() {
        return winner.get();
    }
    public void setWinner(boolean winner) {
        this.winner.set(winner);
    }
    public BooleanProperty winnerProperty(){
        return winner;
    }
}
