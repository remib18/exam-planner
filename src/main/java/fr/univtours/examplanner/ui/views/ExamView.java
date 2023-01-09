package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.ExamController;
import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.enums.ExamType;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExamView {

	public static final String TITLE = "app.title.exam";
	private static ExamView instance;

	private final ExamController controller;

	private static ExamView getInstance() {
		if ( Objects.isNull(instance) ) {
			instance = new ExamView();
		}
		return instance;
	}
	private ExamView() {
		super();
		controller = new ExamController();
	}

	public static @NotNull Scene getScene() throws IOException {
		DataView< ExamDTO > view = new DataView<>("images/Examen.png",
				"feature.exam",
				new DataTable<>(getColumns(), ExamView::getData)
		);
		view.setOnAddRequest(() -> new ExamDTO(null, null,null, null,null,null,1.0f,null));
		view.setOnSaveRequest(exam -> {
			try {
				getInstance().controller.save(exam);
			} catch ( ControllerException e ) {
				e.printStackTrace();
			}
		});
		view.setOnDeleteRequest(() -> {
			view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
				try {getInstance().controller.delete(item.getValue());} catch ( ControllerException e ) {e.printStackTrace();}
			});
		});
		return new Scene(view);
	}

	private static @NotNull List< TableColumnDeclaration< ExamDTO, ? > > getColumns() {
		List< TableColumnDeclaration< ExamDTO, ? > > columns = new ArrayList<>();
		columns.add(new TableColumnDeclaration<>("id", "feature.exam.id", false));
		columns.add(new TableColumnDeclaration<>("name", "feature.exam.day", false));
		columns.add(new TableColumnDeclaration<>("duration", "feature.exam.duration", false));
		columns.add(new TableColumnDeclaration<>("type", "feature.exam.type", false));
		columns.add(new TableColumnDeclaration<>("subject", "feature.exam.subject", false));
		return columns;
	}

	private static @NotNull TreeItem< ExamDTO > getData() throws ControllerException {
		TreeItem< ExamDTO > root = new TreeItem<>();
		root.setExpanded(true);
		List< ExamDTO > exams = getInstance().controller.getAll();
		for ( ExamDTO exam : exams ) {
			TreeItem< ExamDTO > item = new TreeItem<>(exam);
			root.getChildren().add(item);
		}
		return root;
	}

	private static void handleTypeChange( TreeTableColumn.CellEditEvent< ExamDTO, ExamType > event ) {
		ExamDTO exam = event.getRowValue().getValue();
		ExamType type = event.getNewValue();
		try {
			exam.set("type", type);
		} catch ( ControllerException ignored ) {}
	}
}