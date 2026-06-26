package com.ijse.sams.view;

import com.ijse.sams.controller.CourseController;
import com.ijse.sams.dto.CourseDTO;
import com.ijse.sams.dto.SubjectDTO;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class CoursePanel extends HBox {
    private final CourseController courseController = new CourseController();

    private TableView<CourseDTO> courseTable;
    private TextField courseNameField;
    private TextField courseDescField;
    private CourseDTO selectedCourse;

    private TableView<SubjectDTO> subjectTable;
    private TextField subjectNameField;
    private SubjectDTO selectedSubject;

    public CoursePanel() {
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f8fafc;");
        HBox.setHgrow(this, Priority.ALWAYS);

        VBox courseBox = new VBox(15);
        courseBox.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");
        HBox.setHgrow(courseBox, Priority.ALWAYS);
        courseBox.setPrefWidth(450);

        Label courseTitle = new Label("Courses");
        courseTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        courseTitle.setStyle("-fx-text-fill: #1e293b;");

        GridPane courseForm = new GridPane();
        courseForm.setHgap(10);
        courseForm.setVgap(10);

        Label nameLbl = new Label("Course Name");
        nameLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        courseNameField = new TextField();
        courseNameField.setPromptText("e.g. Software Engineering");
        courseNameField.setStyle("-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        courseNameField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(courseNameField, Priority.ALWAYS);

        Label descLbl = new Label("Description");
        descLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        courseDescField = new TextField();
        courseDescField.setPromptText("Brief details");
        courseDescField.setStyle("-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        courseDescField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(courseDescField, Priority.ALWAYS);

        courseForm.add(nameLbl, 0, 0);
        courseForm.add(courseNameField, 1, 0);
        courseForm.add(descLbl, 0, 1);
        courseForm.add(courseDescField, 1, 1);

        HBox courseActions = new HBox(10);
        courseActions.setAlignment(Pos.CENTER_LEFT);
        Button addBtn = new Button("Save");
        addBtn.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button updateBtn = new Button("Update");
        updateBtn.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button clearBtn = new Button("Reset");
        clearBtn.setStyle("-fx-background-color: #64748b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");

        courseActions.getChildren().addAll(addBtn, updateBtn, deleteBtn, clearBtn);

        courseTable = new TableView<>();
        courseTable.setStyle("-fx-background-radius: 4px; -fx-border-radius: 4px;");
        TableColumn<CourseDTO, Integer> courseIdCol = new TableColumn<>("ID");
        courseIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        courseIdCol.setPrefWidth(50);

        TableColumn<CourseDTO, String> courseNameCol = new TableColumn<>("Name");
        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        courseNameCol.setPrefWidth(150);

        TableColumn<CourseDTO, String> courseDescCol = new TableColumn<>("Description");
        courseDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        courseDescCol.setPrefWidth(200);

        courseTable.getColumns().addAll(courseIdCol, courseNameCol, courseDescCol);
        VBox.setVgrow(courseTable, Priority.ALWAYS);

        courseBox.getChildren().addAll(courseTitle, courseForm, courseActions, courseTable);

        VBox subjectBox = new VBox(15);
        subjectBox.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");
        HBox.setHgrow(subjectBox, Priority.ALWAYS);
        subjectBox.setPrefWidth(450);

        Label subjectTitle = new Label("Subjects");
        subjectTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        subjectTitle.setStyle("-fx-text-fill: #1e293b;");

        GridPane subjectForm = new GridPane();
        subjectForm.setHgap(10);
        subjectForm.setVgap(10);

        Label subNameLbl = new Label("Subject Name");
        subNameLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        subjectNameField = new TextField();
        subjectNameField.setPromptText("e.g. Database Systems");
        subjectNameField.setStyle("-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        subjectNameField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(subjectNameField, Priority.ALWAYS);

        subjectForm.add(subNameLbl, 0, 0);
        subjectForm.add(subjectNameField, 1, 0);

        HBox subjectActions = new HBox(10);
        subjectActions.setAlignment(Pos.CENTER_LEFT);
        Button addSubBtn = new Button("Add Subject");
        addSubBtn.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button updateSubBtn = new Button("Update Subject");
        updateSubBtn.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button deleteSubBtn = new Button("Remove");
        deleteSubBtn.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button clearSubBtn = new Button("Reset");
        clearSubBtn.setStyle("-fx-background-color: #64748b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");

        subjectActions.getChildren().addAll(addSubBtn, updateSubBtn, deleteSubBtn, clearSubBtn);

        subjectTable = new TableView<>();
        subjectTable.setStyle("-fx-background-radius: 4px; -fx-border-radius: 4px;");
        TableColumn<SubjectDTO, Integer> subIdCol = new TableColumn<>("ID");
        subIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        subIdCol.setPrefWidth(50);

        TableColumn<SubjectDTO, String> subNameCol = new TableColumn<>("Subject Name");
        subNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        subNameCol.setPrefWidth(300);

        subjectTable.getColumns().addAll(subIdCol, subNameCol);
        VBox.setVgrow(subjectTable, Priority.ALWAYS);

        subjectBox.getChildren().addAll(subjectTitle, subjectForm, subjectActions, subjectTable);

        getChildren().addAll(courseBox, subjectBox);

        loadCourses();

        courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedCourse = newSelection;
                courseNameField.setText(selectedCourse.getName());
                courseDescField.setText(selectedCourse.getDescription());
                loadSubjects(selectedCourse.getId());
            }
        });

        subjectTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedSubject = newSelection;
                subjectNameField.setText(selectedSubject.getName());
            }
        });

        addBtn.setOnAction(e -> {
            String name = courseNameField.getText().trim();
            String desc = courseDescField.getText().trim();
            if (name.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Course name is required.");
                return;
            }
            try {
                if (courseController.saveCourse(new CourseDTO(0, name, desc))) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Course saved successfully.");
                    clearCourseForm();
                    loadCourses();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save course.");
            }
        });

        updateBtn.setOnAction(e -> {
            if (selectedCourse == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a course to update.");
                return;
            }
            String name = courseNameField.getText().trim();
            String desc = courseDescField.getText().trim();
            if (name.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Course name is required.");
                return;
            }
            try {
                selectedCourse.setName(name);
                selectedCourse.setDescription(desc);
                if (courseController.updateCourse(selectedCourse)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Course updated successfully.");
                    clearCourseForm();
                    loadCourses();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update course.");
            }
        });

        deleteBtn.setOnAction(e -> {
            if (selectedCourse == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a course to delete.");
                return;
            }
            try {
                if (courseController.deleteCourse(selectedCourse.getId())) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Course deleted successfully.");
                    clearCourseForm();
                    loadCourses();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete course.");
            }
        });

        clearBtn.setOnAction(e -> clearCourseForm());

        addSubBtn.setOnAction(e -> {
            if (selectedCourse == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a course first.");
                return;
            }
            String name = subjectNameField.getText().trim();
            if (name.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Subject name is required.");
                return;
            }
            try {
                if (courseController.saveSubject(new SubjectDTO(0, name, selectedCourse.getId()))) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Subject added successfully.");
                    subjectNameField.clear();
                    loadSubjects(selectedCourse.getId());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add subject.");
            }
        });

        deleteSubBtn.setOnAction(e -> {
            if (selectedSubject == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a subject to delete.");
                return;
            }
            try {
                if (courseController.deleteSubject(selectedSubject.getId())) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Subject removed successfully.");
                    subjectNameField.clear();
                    selectedSubject = null;
                    subjectTable.getSelectionModel().clearSelection();
                    loadSubjects(selectedCourse.getId());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to remove subject.");
            }
        });

        updateSubBtn.setOnAction(e -> {
            if (selectedSubject == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a subject to update.");
                return;
            }
            String name = subjectNameField.getText().trim();
            if (name.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Subject name is required.");
                return;
            }
            try {
                selectedSubject.setName(name);
                if (courseController.updateSubject(selectedSubject)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Subject updated successfully.");
                    subjectNameField.clear();
                    selectedSubject = null;
                    subjectTable.getSelectionModel().clearSelection();
                    loadSubjects(selectedCourse.getId());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update subject.");
            }
        });

        clearSubBtn.setOnAction(e -> {
            subjectNameField.clear();
            selectedSubject = null;
            subjectTable.getSelectionModel().clearSelection();
        });
    }

    private void loadCourses() {
        try {
            List<CourseDTO> list = courseController.getAllCourses();
            courseTable.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSubjects(int courseId) {
        try {
            List<SubjectDTO> list = courseController.getSubjectsByCourse(courseId);
            subjectTable.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearCourseForm() {
        courseNameField.clear();
        courseDescField.clear();
        selectedCourse = null;
        selectedSubject = null;
        courseTable.getSelectionModel().clearSelection();
        subjectTable.getItems().clear();
        subjectNameField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
