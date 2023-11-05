module com.dsa.triageapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;
    requires java.sql;

    opens com.dsa.triageapp to javafx.fxml;
    exports com.dsa.triageapp;
}