package org.joshuaharris.visualisation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.joshuaharris.visualisation.UI.View;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View(10,10);

        BorderPane root = new BorderPane(view.getView());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Visualising Path Finding Algorithms");
        primaryStage.show();
    }
}
