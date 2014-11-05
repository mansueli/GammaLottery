/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gammalottery;

/**
 *
 * @author Rodrigo
 */
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class LotteryObject {
    private BooleanProperty winner;
    private final SimpleStringProperty name;
    private SimpleIntegerProperty entries;
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
