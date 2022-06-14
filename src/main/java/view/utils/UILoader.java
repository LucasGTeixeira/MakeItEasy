package view.utils;

import application.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class UILoader {

    private static Bundle bundle;

    private static Scene scene;

    public static void setScene(Scene scene) {
        UILoader.scene = scene;
    }

    public static void substituirTela(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void substituirTela(String fxml, Bundle bundleParam) throws IOException {
        bundle = bundleParam;
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Bundle getBundle() {
        return bundle;
    }

    public static void freeBundle() {
        bundle = null;
    }
}
