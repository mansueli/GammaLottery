/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gammalottery;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Rodrigo
 */
public class GammaFXMLController {

    private final Set<String> nameslist = new HashSet<>();
    @FXML
    private ComboBox<String> nameBox;
    @FXML
    private ComboBox<Integer> entryBox;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button drawButton;
    @FXML
    private TableView<LotteryObject> table;
    @FXML
    private TableColumn<LotteryObject, String> nameColumn;
    @FXML
    private TableColumn<LotteryObject, Integer> entryColumn;
    private ObservableList<LotteryObject> gammaList = FXCollections.observableArrayList();

    public ObservableList<String> getNameBox() {
        return nameBox.getItems();
    }

    @SuppressWarnings("empty-statement")
    public void init() {
        InputStream fis;
        BufferedReader br;
        String line;
        try {
            fis = new FileInputStream("names.dat");
            br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
            while ((line = br.readLine()) != null) {
                nameslist.add(line);
            }
            br.close();
        } catch (IOException ex) {
           // Logger.getLogger(GammaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            nameBox.getItems().addAll(nameslist);
            for (int i = 0; i <= 20; entryBox.getItems().add(++i));
            nameColumn.setCellValueFactory(new PropertyValueFactory<LotteryObject, String>("name"));
            entryColumn.setCellValueFactory(new PropertyValueFactory<>("entries"));
            table.setRowFactory(new Callback<TableView<LotteryObject>, TableRow<LotteryObject>>() {
                @Override
                public TableRow<LotteryObject> call(TableView<LotteryObject> tableView) {
                    final TableRow<LotteryObject> row = new TableRow<LotteryObject>() {
                        @Override
                        protected void updateItem(LotteryObject row, boolean empty) {
                            super.updateItem(row, empty);
                            if (!empty) {
                                styleProperty().bind(Bindings.when(row.winnerProperty())
                                        .then("-fx-font-weight: bold; -fx-font-size: 16; -fx-background-color: #CCFFCC;")
                                        .otherwise(""));
                            }
                        }
                    };
                    return row;
                }
            });
            nameBox.setOnAction((ActionEvent event) -> {
                gammaList.stream().forEach((o) -> {
                    if (o.getName().equalsIgnoreCase(nameBox.getValue())) {
                        removeButton.setDisable(false);
                    } else {
                        removeButton.setDisable(false);
                    }
                });
            });

        }
        table.setItems(gammaList);
    }

    @FXML
    private void addToLottery() {

        boolean flag = false;
        for (LotteryObject o : gammaList) {
            if (o.getName().equalsIgnoreCase(nameBox.getValue())) {
                flag = true;
                int entries = o.getEntries() + Integer.parseInt("" + entryBox.getValue());;
                if (entries < 0) {
                    gammaList.remove(o);
                } else {
                    o.setEntries(entries);
                }
                break;
            }
        }

        int value = Integer.parseInt("" + entryBox.getValue());
        if (!flag && (value > 0) && !nameBox.getValue().isEmpty()) {
            gammaList.add(new LotteryObject(nameBox.getValue(), value));
            nameBox.getItems().add(nameBox.getValue());
            nameBox.setValue("");
            entryBox.setValue(1);
        }
    }

    @FXML
    private void removeFromLottery() {
        List<LotteryObject> dummy = new ArrayList<>(gammaList);
        dummy.stream().filter((o) -> (o.getName().
                equalsIgnoreCase(nameBox.getValue()))).forEach((o) -> {
                    gammaList.remove(o);
                });
        table.setItems(gammaList);
        table.sort();
    }

    @FXML
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
        gammaList.stream().filter((o) -> (o.getName().equalsIgnoreCase(lucky))).forEach((o) -> {
            o.setWinner(true);
        });
    }
}
