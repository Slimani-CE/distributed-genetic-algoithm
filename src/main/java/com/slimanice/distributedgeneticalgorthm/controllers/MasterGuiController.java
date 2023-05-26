package com.slimanice.distributedgeneticalgorthm.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MasterGuiController implements Initializable {

    @FXML
    private TextField targetField;
    @FXML
    private TextField nbrIslands;
    @FXML
    private TextField maxGenField;
    @FXML
    private TextField populationSizeField;
    @FXML
    private Slider mutationRateSlider;
    @FXML
    private Button startButton;
    @FXML
    private Button resetBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
