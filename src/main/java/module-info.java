module fr.montreuil.iut.Lucas_Adrien_Imman {
    requires javafx.controls;
    requires javafx.fxml;

   // requires com.dlsc.formsfx;

    opens fr.montreuil.iut.Lucas_Adrien_Imman to javafx.fxml;
    exports fr.montreuil.iut.Lucas_Adrien_Imman;
    exports fr.montreuil.iut.Lucas_Adrien_Imman.controller;
    opens fr.montreuil.iut.Lucas_Adrien_Imman.controller to javafx.fxml;
    exports fr.montreuil.iut.Lucas_Adrien_Imman.modele;
    opens fr.montreuil.iut.Lucas_Adrien_Imman.modele to javafx.fxml;


}