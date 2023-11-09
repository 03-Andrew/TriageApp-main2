module com.dsa.triageapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;
    requires java.sql;

    opens com.dsa.triageapp to javafx.fxml;
    exports com.dsa.triageapp;
    exports com.dsa.triageapp.ADT;
    opens com.dsa.triageapp.ADT to javafx.fxml;
    exports com.dsa.triageapp.NotInUse;
    opens com.dsa.triageapp.NotInUse to javafx.fxml;
}