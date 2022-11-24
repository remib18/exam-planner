module fr.univtours.examplanner.examplanner {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.jetbrains.annotations;
	requires commons.csv;


	opens fr.univtours.examplanner to javafx.fxml;
	exports fr.univtours.examplanner;
}