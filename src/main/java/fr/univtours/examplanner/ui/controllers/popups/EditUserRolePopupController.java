package fr.univtours.examplanner.ui.controllers.popups;

import fr.univtours.examplanner.controllers.DepartmentController;
import fr.univtours.examplanner.controllers.ManagerController;
import fr.univtours.examplanner.controllers.UserController;
import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.enums.UserRole;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.BasicViewController;
import fr.univtours.examplanner.ui.PopupController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EditUserRolePopupController extends BasicViewController {

    private final @NotNull SimpleObjectProperty< UserRole > role = new SimpleObjectProperty<>();

    private final @NotNull UserDTO user;

    @FXML
    private ComboBox< UserRole > userRoleDropDown;

    @FXML
    private ComboBox< Object > departmentOrManagerDropdown;

    public EditUserRolePopupController( @NotNull UserDTO user, @NotNull UserRole role ) {
        super();
        this.user = user;
        this.role.set(role);
    }

    @FXML
    private void handleCancel() {
        PopupController.close();
    }

    @FXML
    private void handleSave() throws ControllerException {
        switch ( role.get() ) {
            case Department, Manager -> {
                if ( Objects.isNull(departmentOrManagerDropdown.getValue()) ) {
                    return;
                }
            }
            case Schooling -> {}
        }
        UserController.save(user);
        PopupController.close();
    }

    @Override
    protected void init() {
        userRoleDropDown.getItems().addAll(UserRole.values());
        userRoleDropDown.setValue(role.get());
        userRoleDropDown.valueProperty().addListener(( observable, oldValue, newValue ) -> {
            role.set(newValue);
        });

        role.addListener(( observable, oldValue, newValue ) -> {
            handleRoleChange();
        });
        handleRoleChange();
    }

    private void handleRoleChange() {
        departmentOrManagerDropdown.getItems().clear();
        departmentOrManagerDropdown.setDisable(false);
        userRoleDropDown.setValue(null);
        switch ( role.get() ) {
            case Department -> {
                departmentOrManagerDropdown.getItems().addAll(DepartmentController.getAll());
                departmentOrManagerDropdown.setValue(user.getDepartment());
                departmentOrManagerDropdown.setConverter(new DepartmentDTOStringConverter());
                departmentOrManagerDropdown.valueProperty().addListener(( observable, oldValue, newValue ) -> {
                    user.setManager(null);
                    user.setDepartment((DepartmentDTO) newValue);
                });
            }
            case Manager -> {
                departmentOrManagerDropdown.getItems().addAll(ManagerController.getAll());
                departmentOrManagerDropdown.setValue(user.getManager());
                departmentOrManagerDropdown.setConverter(new ManagerDTOStringConverter());
                departmentOrManagerDropdown.valueProperty().addListener(( observable, oldValue, newValue ) -> {
                    user.setDepartment(null);
                    user.setManager((ManagerDTO) newValue);
                });
            }
            case Schooling -> {
                departmentOrManagerDropdown.setDisable(true);
                user.setDepartment(null);
                user.setManager(null);
            }
        }
    }

    @Override
    protected void onLanguageUpdate() {

    }

    private static class ManagerDTOStringConverter extends StringConverter< Object > {

        @Override
        public String toString( Object object ) {
            if ( Objects.isNull(object) ) return "";
            return ( (ManagerDTO) object ).getLastName().toUpperCase() + " " + ( (ManagerDTO) object ).getFirstName();
        }

        @Override
        public ManagerDTO fromString( String s ) {
            if ( Objects.isNull(s) ) return null;
            String lastName = s.split(" ")[0];
            String firstName = s.split(" ")[1];
            return ManagerController.getByFullName(lastName, firstName);
        }
    }

    private static class DepartmentDTOStringConverter extends StringConverter< Object > {

        @Override
        public String toString( Object object ) {
            if ( Objects.isNull(object) ) return "";
            return ( (DepartmentDTO) object ).getName();
        }

        @Override
        public DepartmentDTO fromString( String s ) {
            if ( Objects.isNull(s) ) return null;
            return DepartmentController.getByName(s);
        }
    }
}
