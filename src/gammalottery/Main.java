/*
 * All components of this software is licensed under GNU General Public License v2 (GPL-2) 
 * for personal usage. For commercial usage you must contact the author prior distribution or usage.
 *
 */

package gammalottery;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * JavaFX Application class
 *
 * @author:Rodrigo Mansueli Nunes  {@literal <mansueli@ualberta.ca>}
 * <a href="http://kyllo.com.br">kyllo.com.br</a>
 *
 **/
public class Main extends Application {
    /**
     * 
     * @param stage JavaFX primary stage
     * @throws Exception load exception
     **/
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gamma.fxml"));
        Parent root = (Parent)loader.load();
        GammaFXMLController myController = loader.getController();
        myController.init();
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((WindowEvent e) -> {
            File f = new File("names.dat");
            f.delete();
            try {
                f.createNewFile();
                try (FileWriter writer = new FileWriter("names.dat")) {
                    for(String str: myController.getNameBox()) {
                        writer.write(str + "\n");
                    }
                }
            }
             catch (IOException ex) {
                //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
