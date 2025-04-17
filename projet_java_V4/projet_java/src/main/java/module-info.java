module com.projet.projet_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    // 👇 Ouvre le package du contrôleur FXML à JavaFX
    opens com.projet.projet_java.controller to javafx.fxml;
    opens com.projet.projet_java to javafx.fxml;

    exports com.projet.projet_java;
}
