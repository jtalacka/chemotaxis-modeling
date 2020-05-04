package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Logic l=new Logic();
        Group root = new Group();
        Scene s = new Scene(root, 1000, 1000, Color.BLACK);

        final Canvas canvas = new Canvas(1000,1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        primaryStage.setScene(s);
        primaryStage.show();
        l.results(gc);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
