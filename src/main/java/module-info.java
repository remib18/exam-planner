module fr.univtours.examplanner {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.jetbrains.annotations;
	requires commons.csv;
	requires java.sql;

	opens fr.univtours.examplanner to javafx.fxml;
	exports fr.univtours.examplanner;
	exports fr.univtours.examplanner.ui;
	opens fr.univtours.examplanner.ui to javafx.fxml;
}