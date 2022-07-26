module com.example.financeplayground {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires json.simple;
    requires MaterialFX;

    opens com.example.financeplayground to javafx.fxml;
    exports com.example.financeplayground;
}