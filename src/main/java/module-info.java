module fr.univtours.examplanner {

    // Dépendances
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires commons.csv;
    requires java.sql;
    requires json.simple;

    // JavaFX
    opens fr.univtours.examplanner to javafx.fxml;
    opens fr.univtours.examplanner.ui to javafx.fxml;
    opens fr.univtours.examplanner.ui.controllers to javafx.fxml;
    opens fr.univtours.examplanner.ui.components to javafx.fxml;

    // Exports (nécessaire pour documentation)
    exports fr.univtours.examplanner;
    exports fr.univtours.examplanner.controllers;
    exports fr.univtours.examplanner.entities;
    exports fr.univtours.examplanner.entities.dtos;
    exports fr.univtours.examplanner.enums;
    exports fr.univtours.examplanner.exceptions;
    exports fr.univtours.examplanner.mappers;
    exports fr.univtours.examplanner.repositories;
    exports fr.univtours.examplanner.translations;
    exports fr.univtours.examplanner.ui;
    exports fr.univtours.examplanner.ui.controllers;
    exports fr.univtours.examplanner.ui.states;
    exports fr.univtours.examplanner.ui.views;
    exports fr.univtours.examplanner.utils;
}