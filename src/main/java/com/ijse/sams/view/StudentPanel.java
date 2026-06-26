package com.ijse.sams.view;

import com.ijse.sams.controller.CourseController;
import com.ijse.sams.controller.StudentController;
import com.ijse.sams.dto.CourseDTO;
import com.ijse.sams.dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;

import java.util.List;

public class StudentPanel extends VBox {
    private final StudentController studentController = new StudentController();
    private final CourseController courseController = new CourseController();

    private TableView<StudentDTO> studentTable;
    private TextField nameField;
    private TextField regNumField;
    private TextField emailField;
    private TextField contactField;
    private FlowPane courseCheckboxContainer;
    private final java.util.Map<Integer, CheckBox> courseCheckBoxes = new java.util.HashMap<>();
    private StudentDTO selectedStudent;

    public StudentPanel() {
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f8fafc;");
        VBox.setVgrow(this, Priority.ALWAYS);

        VBox formContainer = new VBox(15);
        formContainer.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");

        Label title = new Label("Register / Edit Student");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        title.setStyle("-fx-text-fill: #1e293b;");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(12);

        Label nameLbl = new Label("Name");
        nameLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        nameField = new TextField();
        nameField.setPromptText("Enter full name");
        nameField.setStyle("-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");

        Label regLbl = new Label("Reg Number");
        regLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        regNumField = new TextField();
        regNumField.setPromptText("e.g. REG/2026/001");
        regNumField.setStyle("-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");

        Label emailLbl = new Label("Email");
        emailLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        emailField = new TextField();
        emailField.setPromptText("name@example.com");
        emailField.setStyle("-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");

        Label contactLbl = new Label("Contact");
        contactLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        contactField = new TextField();
        contactField.setPromptText("+94 XX XXX XXXX");
        contactField.setStyle("-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");

        Label courseLbl = new Label("Enrolled Courses");
        courseLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        courseCheckboxContainer = new FlowPane();
        courseCheckboxContainer.setHgap(15);
        courseCheckboxContainer.setVgap(10);
        courseCheckboxContainer.setPadding(new Insets(5));
        GridPane.setHgrow(courseCheckboxContainer, Priority.ALWAYS);

        formGrid.add(nameLbl, 0, 0);
        formGrid.add(nameField, 1, 0);
        formGrid.add(regLbl, 2, 0);
        formGrid.add(regNumField, 3, 0);
        formGrid.add(emailLbl, 0, 1);
        formGrid.add(emailField, 1, 1);
        formGrid.add(contactLbl, 2, 1);
        formGrid.add(contactField, 3, 1);
        formGrid.add(courseLbl, 0, 2);
        formGrid.add(courseCheckboxContainer, 1, 2, 3, 1);

        HBox actions = new HBox(10);
        actions.setAlignment(Pos.CENTER_LEFT);
        Button saveBtn = new Button("Save");
        saveBtn.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button updateBtn = new Button("Update");
        updateBtn.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button resetBtn = new Button("Reset");
        resetBtn.setStyle("-fx-background-color: #64748b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        actions.getChildren().addAll(saveBtn, updateBtn, deleteBtn, resetBtn);

        formContainer.getChildren().addAll(title, formGrid, actions);

        studentTable = new TableView<>();
        studentTable.setStyle("-fx-background-radius: 4px; -fx-border-radius: 4px;");
        VBox.setVgrow(studentTable, Priority.ALWAYS);

        TableColumn<StudentDTO, String> regCol = new TableColumn<>("Reg Number");
        regCol.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        regCol.setPrefWidth(120);

        TableColumn<StudentDTO, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(180);

        TableColumn<StudentDTO, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(180);

        TableColumn<StudentDTO, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        contactCol.setPrefWidth(120);

        TableColumn<StudentDTO, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseCol.setPrefWidth(180);

        studentTable.getColumns().addAll(regCol, nameCol, emailCol, contactCol, courseCol);

        getChildren().addAll(formContainer, studentTable);

        loadCourses();
        loadStudents();

        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedStudent = newSel;
                nameField.setText(selectedStudent.getName());
                regNumField.setText(selectedStudent.getRegNumber());
                emailField.setText(selectedStudent.getEmail());
                contactField.setText(selectedStudent.getContact());
                for (CheckBox cb : courseCheckBoxes.values()) {
                    cb.setSelected(false);
                }
                if (selectedStudent.getCourseIds() != null) {
                    for (int cid : selectedStudent.getCourseIds()) {
                        CheckBox cb = courseCheckBoxes.get(cid);
                        if (cb != null) {
                            cb.setSelected(true);
                        }
                    }
                }
            }
        });

        saveBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String reg = regNumField.getText().trim();
            String email = emailField.getText().trim();
            String contact = contactField.getText().trim();

            List<Integer> selectedCourseIds = new java.util.ArrayList<>();
            for (java.util.Map.Entry<Integer, CheckBox> entry : courseCheckBoxes.entrySet()) {
                if (entry.getValue().isSelected()) {
                    selectedCourseIds.add(entry.getKey());
                }
            }

            if (name.isEmpty() || reg.isEmpty() || selectedCourseIds.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Name, Registration number, and at least one Course are required.");
                return;
            }

            try {
                StudentDTO newStudent = new StudentDTO(0, name, reg, email, contact, 0);
                newStudent.setCourseIds(selectedCourseIds);
                if (studentController.saveStudent(newStudent)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Student saved successfully.");
                    clearForm();
                    loadStudents();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save student. Registration number may be duplicate.");
            }
        });

        updateBtn.setOnAction(e -> {
            if (selectedStudent == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a student to update.");
                return;
            }
            String name = nameField.getText().trim();
            String reg = regNumField.getText().trim();
            String email = emailField.getText().trim();
            String contact = contactField.getText().trim();

            List<Integer> selectedCourseIds = new java.util.ArrayList<>();
            for (java.util.Map.Entry<Integer, CheckBox> entry : courseCheckBoxes.entrySet()) {
                if (entry.getValue().isSelected()) {
                    selectedCourseIds.add(entry.getKey());
                }
            }

            if (name.isEmpty() || reg.isEmpty() || selectedCourseIds.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Name, Registration number, and at least one Course are required.");
                return;
            }

            try {
                selectedStudent.setName(name);
                selectedStudent.setRegNumber(reg);
                selectedStudent.setEmail(email);
                selectedStudent.setContact(contact);
                selectedStudent.setCourseIds(selectedCourseIds);
                if (studentController.updateStudent(selectedStudent)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Student updated successfully.");
                    clearForm();
                    loadStudents();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update student.");
            }
        });

        deleteBtn.setOnAction(e -> {
            if (selectedStudent == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a student to delete.");
                return;
            }
            try {
                if (studentController.deleteStudent(selectedStudent.getId())) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Student deleted successfully.");
                    clearForm();
                    loadStudents();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete student.");
            }
        });

        resetBtn.setOnAction(e -> clearForm());
    }

    private void loadCourses() {
        courseCheckboxContainer.getChildren().clear();
        courseCheckBoxes.clear();
        try {
            List<CourseDTO> list = courseController.getAllCourses();
            for (CourseDTO course : list) {
                CheckBox cb = new CheckBox(course.getName());
                cb.setStyle("-fx-text-fill: #1e293b; -fx-cursor: hand;");
                courseCheckboxContainer.getChildren().add(cb);
                courseCheckBoxes.put(course.getId(), cb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudents() {
        try {
            List<StudentDTO> list = studentController.getAllStudents();
            studentTable.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        nameField.clear();
        regNumField.clear();
        emailField.clear();
        contactField.clear();
        for (CheckBox cb : courseCheckBoxes.values()) {
            cb.setSelected(false);
        }
        selectedStudent = null;
        studentTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
