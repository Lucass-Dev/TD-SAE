module fr.montreuil.iut.Lucas_Adrien_Imman {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;

    // requires com.dlsc.formsfx;

    opens fr.montreuil.iut.Lucas_Adrien_Imman to javafx.fxml;
    exports fr.montreuil.iut.Lucas_Adrien_Imman;
    exports fr.montreuil.iut.Lucas_Adrien_Imman.controller;
    opens fr.montreuil.iut.Lucas_Adrien_Imman.controller to javafx.fxml;
    exports fr.montreuil.iut.Lucas_Adrien_Imman.modele;
    opens fr.montreuil.iut.Lucas_Adrien_Imman.modele to javafx.fxml;
    exports fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;
    opens fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours to javafx.fxml;
    exports fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;
    opens fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis to javafx.fxml;
    exports fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;
    opens fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles to javafx.fxml;
    exports fr.montreuil.iut.Lucas_Adrien_Imman.test;
    opens fr.montreuil.iut.Lucas_Adrien_Imman.test to javafx.fxml;


}