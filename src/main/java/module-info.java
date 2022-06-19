module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens application.main;
    opens view.enums to javafx.fxml;
    opens view.utils to javafx.fxml, javafx.base;
    opens view.controller to javafx.fxml, javafx.base;
    opens view.controller.venda to javafx.fxml, javafx.base;
    opens view.controller.relatorios to javafx.fxml, javafx.base;
    opens view.controller.gerenciamento.cliente to javafx.fxml, javafx.base;
    opens view.controller.gerenciamento.campanha to javafx.fxml, javafx.base;
    opens view.controller.gerenciamento.empresa to javafx.fxml, javafx.base;
    opens view.controller.gerenciamento.produto to javafx.fxml, javafx.base;


    opens domain.entities.campanha to javafx.fxml, javafx.base;
    opens domain.entities.cliente to javafx.fxml, javafx.base;
    opens domain.entities.empresa to javafx.fxml, javafx.base;
    opens domain.entities.produto to javafx.fxml, javafx.base;
    opens domain.entities.venda to javafx.fxml, javafx.base;
    exports application.main;
}