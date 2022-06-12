package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.enums.Tela;
import view.utils.UILoader;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(UILoader.loadFXML(Tela.INITIAL.getNomeTela()));
        UILoader.setScene(scene);

        stage.setResizable(false);
        stage.setMaxHeight(450);
        stage.setMaxWidth(650);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }
}
