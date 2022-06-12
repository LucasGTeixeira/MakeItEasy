module view {
    requires javafx.controls;
    requires javafx.fxml;

    opens view;
    opens view.enums to javafx.fxml;
    opens view.utils to javafx.fxml, javafx.base;
    opens view.controller to javafx.fxml, javafx.base;
    opens view.controller.venda to javafx.fxml, javafx.base;
    opens view.controller.relatorios to javafx.fxml, javafx.base;
    opens view.controller.gerenciamento.cliente to javafx.fxml, javafx.base;
    opens view.controller.gerenciamento.campanha to javafx.fxml, javafx.base;
    opens view.controller.gerenciamento.empresa to javafx.fxml, javafx.base;
    opens view.controller.gerenciamento.produto to javafx.fxml, javafx.base;
    exports view;
}