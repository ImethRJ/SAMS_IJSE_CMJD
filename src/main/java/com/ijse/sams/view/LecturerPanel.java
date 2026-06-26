package com.ijse.sams.view;

import com.ijse.sams.controller.CourseController;
import com.ijse.sams.controller.LecturerController;
import com.ijse.sams.dto.CourseDTO;
import com.ijse.sams.dto.LecturerDTO;
import com.ijse.sams.dto.SubjectDTO;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LecturerPanel extends HBox {
    private final LecturerController lecturerController = new LecturerController();
    private final CourseController courseController = new CourseController();

    private TableView<LecturerDTO> lecturerTable;
    private TextField nameField;
    private TextField emailField;
    private TextField contactField;
    private TextField usernameField;
    private PasswordField passwordField;
    private LecturerDTO selectedLecturer;

    private VBox checkboxContainer;
    private final Map<Integer, CheckBox> subjectCheckBoxes = new HashMap<>();

    public LecturerPanel() {
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f8fafc;");
        HBox.setHgrow(this, Priority.ALWAYS);

        VBox leftBox = new VBox(15);
        leftBox.setStyle(
                "-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");
        leftBox.setPrefWidth(550);
        HBox.setHgrow(leftBox, Priority.ALWAYS);

        Label title = new Label("Lecturer Profiles");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        title.setStyle("-fx-text-fill: #1e293b;");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        Label nameLbl = new Label("Full Name");
        nameLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        nameField = new TextField();
        nameField.setPromptText("Mr. Kavindu Perera");
        nameField.setStyle(
                "-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        GridPane.setHgrow(nameField, Priority.ALWAYS);

        Label emailLbl = new Label("Email");
        emailLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        emailField = new TextField();
        emailField.setPromptText("kavindu.perera@ijse.lk");
        emailField.setStyle(
                "-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        GridPane.setHgrow(emailField, Priority.ALWAYS);

        Label contactLbl = new Label("Contact");
        contactLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        contactField = new TextField();
        contactField.setPromptText("+94 XX XXX XXXX");
        contactField.setStyle(
                "-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        GridPane.setHgrow(contactField, Priority.ALWAYS);

        Label usernameLbl = new Label("Username");
        usernameLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        usernameField = new TextField();
        usernameField.setPromptText("kavindu.perera@ijse.lk");
        usernameField.setStyle(
                "-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        GridPane.setHgrow(usernameField, Priority.ALWAYS);

        Label passwordLbl = new Label("Password");
        passwordLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        passwordField = new PasswordField();
        passwordField.setPromptText("••••••••");
        passwordField.setStyle(
                "-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        GridPane.setHgrow(passwordField, Priority.ALWAYS);

        form.add(nameLbl, 0, 0);
        form.add(nameField, 1, 0);
        form.add(emailLbl, 0, 1);
        form.add(emailField, 1, 1);
        form.add(contactLbl, 0, 2);
        form.add(contactField, 1, 2);
        form.add(usernameLbl, 0, 3);
        form.add(usernameField, 1, 3);
        form.add(passwordLbl, 0, 4);
        form.add(passwordField, 1, 4);

        HBox actions = new HBox(10);
        actions.setAlignment(Pos.CENTER_LEFT);
        Button saveBtn = new Button("Save");
        saveBtn.setStyle(
                "-fx-background-color: #10b981; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button updateBtn = new Button("Update");
        updateBtn.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle(
                "-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button resetBtn = new Button("Reset");
        resetBtn.setStyle(
                "-fx-background-color: #64748b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        actions.getChildren().addAll(saveBtn, updateBtn, deleteBtn, resetBtn);

        lecturerTable = new TableView<>();
        lecturerTable.setStyle("-fx-background-radius: 4px; -fx-border-radius: 4px;");
        TableColumn<LecturerDTO, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);

        TableColumn<LecturerDTO, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);

        TableColumn<LecturerDTO, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(180);

        TableColumn<LecturerDTO, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        contactCol.setPrefWidth(120);

        lecturerTable.getColumns().addAll(idCol, nameCol, emailCol, contactCol);
        VBox.setVgrow(lecturerTable, Priority.ALWAYS);

        leftBox.getChildren().addAll(title, form, actions, lecturerTable);

        VBox rightBox = new VBox(15);
        rightBox.setStyle(
                "-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");
        rightBox.setPrefWidth(350);

        Label assignmentTitle = new Label("Subject Assignments");
        assignmentTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        assignmentTitle.setStyle("-fx-text-fill: #1e293b;");

        checkboxContainer = new VBox(10);
        checkboxContainer.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(checkboxContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: white;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Button assignBtn = new Button("Save Assignments");
        assignBtn.setMaxWidth(Double.MAX_VALUE);
        assignBtn.setStyle(
                "-fx-background-color: #4f46e5; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px; -fx-background-radius: 6px; -fx-cursor: hand;");

        rightBox.getChildren().addAll(assignmentTitle, scrollPane, assignBtn);

        getChildren().addAll(leftBox, rightBox);

        loadLecturers();
        loadSubjectsCheckboxes();

        lecturerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedLecturer = newSel;
                nameField.setText(selectedLecturer.getName());
                emailField.setText(selectedLecturer.getEmail());
                contactField.setText(selectedLecturer.getContact());
                usernameField.setText(selectedLecturer.getUsername());
                passwordField.setText(selectedLecturer.getPassword());
                loadLecturerAssignments(selectedLecturer.getId());
            }
        });

        saveBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String contact = contactField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (name.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Name is required.");
                return;
            }
            if (username.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Username is required.");
                return;
            }
            if (password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Password is required.");
                return;
            }

            try {
                if (lecturerController
                        .saveLecturer(new LecturerDTO(0, name, email, contact, null, username, password))) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Lecturer saved successfully.");
                    clearForm();
                    loadLecturers();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save lecturer: " + ex.getMessage());
            }
        });

        updateBtn.setOnAction(e -> {
            if (selectedLecturer == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a lecturer to update.");
                return;
            }
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String contact = contactField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (name.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Name is required.");
                return;
            }
            if (username.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Username is required.");
                return;
            }
            if (password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Password is required.");
                return;
            }

            try {
                selectedLecturer.setName(name);
                selectedLecturer.setEmail(email);
                selectedLecturer.setContact(contact);
                selectedLecturer.setUsername(username);
                selectedLecturer.setPassword(password);
                if (lecturerController.updateLecturer(selectedLecturer)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Lecturer updated successfully.");
                    clearForm();
                    loadLecturers();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update lecturer: " + ex.getMessage());
            }
        });

        deleteBtn.setOnAction(e -> {
            if (selectedLecturer == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a lecturer to delete.");
                return;
            }
            try {
                if (lecturerController.deleteLecturer(selectedLecturer.getId())) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Lecturer deleted successfully.");
                    clearForm();
                    loadLecturers();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete lecturer.");
            }
        });

        resetBtn.setOnAction(e -> clearForm());

        assignBtn.setOnAction(e -> {
            if (selectedLecturer == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a lecturer first.");
                return;
            }
            List<Integer> selectedIds = new ArrayList<>();
            for (Map.Entry<Integer, CheckBox> entry : subjectCheckBoxes.entrySet()) {
                if (entry.getValue().isSelected()) {
                    selectedIds.add(entry.getKey());
                }
            }
            try {
                if (lecturerController.saveSubjectAssignments(selectedLecturer.getId(), selectedIds)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Subject assignments saved successfully.");
                    loadLecturers();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save subject assignments.");
            }
        });
    }

    private void loadLecturers() {
        try {
            List<LecturerDTO> list = lecturerController.getAllLecturers();
            lecturerTable.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSubjectsCheckboxes() {
        checkboxContainer.getChildren().clear();
        subjectCheckBoxes.clear();
        try {
            List<CourseDTO> courses = courseController.getAllCourses();
            for (CourseDTO c : courses) {
                List<SubjectDTO> subjects = courseController.getSubjectsByCourse(c.getId());
                if (subjects.isEmpty())
                    continue;

                Label courseHeader = new Label(c.getName());
                courseHeader.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
                courseHeader.setStyle("-fx-text-fill: #475569; -fx-padding: 5 0 2 0;");
                checkboxContainer.getChildren().add(courseHeader);

                for (SubjectDTO s : subjects) {
                    CheckBox cb = new CheckBox(s.getName());
                    cb.setStyle("-fx-text-fill: #1e293b; -fx-cursor: hand;");
                    checkboxContainer.getChildren().add(cb);
                    subjectCheckBoxes.put(s.getId(), cb);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLecturerAssignments(int lecturerId) {
        for (CheckBox cb : subjectCheckBoxes.values()) {
            cb.setSelected(false);
        }
        try {
            List<Integer> assigned = lecturerController.getAssignedSubjectIds(lecturerId);
            for (Integer id : assigned) {
                CheckBox cb = subjectCheckBoxes.get(id);
                if (cb != null) {
                    cb.setSelected(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        contactField.clear();
        usernameField.clear();
        passwordField.clear();
        selectedLecturer = null;
        lecturerTable.getSelectionModel().clearSelection();
        for (CheckBox cb : subjectCheckBoxes.values()) {
            cb.setSelected(false);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
