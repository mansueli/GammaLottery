/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gammalottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

/**
 *
 * @author Rodrigo
 */
public class TestRandomness {

    ObservableList<LotteryObject> gammaList = FXCollections.observableArrayList();
    Map<String, Integer> map = new HashMap<>();

    /**
     * Test of get method, of class RandomOrg.
     *
     * @throws java.lang.Exception
     */
    @Test
    @SuppressWarnings("empty-statement")
    public void testDistribution() throws Exception {
        final int iteractions = 2000;
        final int int_10 = iteractions/10;
        map.put("a", 0);
        map.put("b", 0);
        map.put("c", 0);
        map.put("d", 0);
        map.put("e", 0);
        map.put("f", 0);
        String[] entities = new String[]{"a", "b", "c", "d", "e", "f"};
        int[] entries = new int[]{15, 5, 20, 1, 8, 1};
        gammaList.clear();
        for (int i = 0; i < entries.length; i++) {
            gammaList.add(new LotteryObject(entities[i], entries[i]));
        }
        int count = 0;
        int aux = 0;
        System.out.println("0%........100%\n");
        while (count < iteractions) {
            drawAction();
            count++;
            aux++;
            if (aux >= int_10) {
                aux = 0;
                System.out.print(".");
            }

        }
        System.out.println("\nExpected %");
        for (int i = 0; i < entries.length; i++) {
            System.out.println(entities[i] + " : " + ((double) entries[i]) / 100.0);
        }
        final SimpleIntegerProperty winners = new SimpleIntegerProperty(0);
        System.out.println("\nActualResults %");
        map.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey() + " : " + entry.getValue() + "--> " + ((double) entry.getValue()) / 1000.0 + "%");
            winners.setValue(winners.getValue() + entry.getValue());
        });
    }

    private void drawAction() {
        List<String> actualList = new ArrayList<>();
        gammaList.stream().forEach((o) -> {
            o.setWinner(false);
            for (int i = 0; i < o.getEntries(); i++) {
                actualList.add(o.getName());
            }
        });
        Collections.shuffle(actualList, new Random(RandomOrg.getRandLong()));
        int winner = (int) RandomOrg.getRandInteger(0, gammaList.size() - 1);
        String lucky = actualList.get(winner);
        map.put(lucky, map.get(lucky) + 1);
    }
}
