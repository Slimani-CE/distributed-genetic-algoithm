module com.slimanice.distributedgeneticalgorthm {
    requires javafx.controls;
    requires javafx.fxml;
    requires jade;

    opens com.slimanice.distributedgeneticalgorthm to javafx.fxml;
    exports com.slimanice.distributedgeneticalgorthm;
    exports com.slimanice.distributedgeneticalgorthm.agents;
    exports com.slimanice.distributedgeneticalgorthm.controllers;
}