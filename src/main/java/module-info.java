module com.slimanice.distributedgeneticalgorthm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.slimanice.distributedgeneticalgorthm to javafx.fxml;
    exports com.slimanice.distributedgeneticalgorthm;
}