package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.SlotController;
import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SlotView {

	public static final String TITLE = "app.title.slot";

	private static SlotView instance;

	private final SlotController controller;

	private static SlotView getInstance() {
		if ( Objects.isNull(instance) ) {
			instance = new SlotView();
		}
		return instance;
	}
	private SlotView() {
		super();
		controller = new SlotController();
	}

	public static @NotNull Scene getScene() throws IOException {
		DataView< SlotDTO > view = new DataView<>("images/Slot.png",
				"feature.slot",
				new DataTable<>(getColumns(), SlotView::getData)
		);
		view.setOnAddRequest(() -> new SlotDTO(null, null,1.0f));
		view.setOnSaveRequest(slot -> {
			try {
				getInstance().controller.save(slot);
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

	private static @NotNull List< TableColumnDeclaration< SlotDTO, ? > > getColumns() {
		List< TableColumnDeclaration< SlotDTO, ? > > columns = new ArrayList<>();
		columns.add(new TableColumnDeclaration<>("id", "feature.slot.id", false));
		columns.add(new TableColumnDeclaration<>("day", "feature.slot.day", false));
		columns.add(new TableColumnDeclaration<>("hour", "feature.slot.hour", false));
		columns.add(new TableColumnDeclaration<>("duration", "feature.slot.duration", false));

		return columns;
	}

	private static @NotNull TreeItem< SlotDTO > getData() throws ControllerException {
		TreeItem< SlotDTO > root = new TreeItem<>();
		root.setExpanded(true);
		List< SlotDTO > slots = getInstance().controller.getAll();
		for ( SlotDTO slot : slots ) {
			TreeItem< SlotDTO > item = new TreeItem<>(slot);
			root.getChildren().add(item);
		}
		return root;
	}

}