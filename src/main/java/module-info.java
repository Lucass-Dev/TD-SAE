module com.application.towerdefensesae {
    requires javafx.controls;
    requires javafx.fxml;

   // requires com.dlsc.formsfx;

    opens com.application.towerdefensesae to javafx.fxml;
    exports com.application.towerdefensesae;
    opens com.application.towerdefensesae.controller to javafx.fxml;
    exports  com.application.towerdefensesae.controller;


}