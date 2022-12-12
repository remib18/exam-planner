module fr.univtours.examplanner {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.jetbrains.annotations;
	requires commons.csv;
	requires java.sql;
	requires json.simple;

	opens fr.univtours.examplanner to javafx.fxml;
	opens fr.univtours.examplanner.ui to javafx.fxml;
	opens fr.univtours.examplanner.ui.controllers to javafx.fxml;

	exports fr.univtours.examplanner;
	//exports fr.univtours.examplanner.ui;
	//exports fr.univtours.examplanner.ui.controllers;
}