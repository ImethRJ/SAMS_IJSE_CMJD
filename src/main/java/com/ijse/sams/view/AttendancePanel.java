package com.ijse.sams.view;

import com.ijse.sams.controller.AttendanceController;
import com.ijse.sams.controller.LecturerController;
import com.ijse.sams.controller.ScheduleController;
import com.ijse.sams.controller.StudentController;
import com.ijse.sams.dto.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class AttendancePanel extends VBox {
    private final AttendanceController attendanceController = new AttendanceController();
    private final ScheduleController scheduleController = new ScheduleController();
    private final StudentController studentController = new StudentController();
    private final LecturerController lecturerController = new LecturerController();
    private final UserDTO currentUser;

    private ComboBox<ScheduleDTO> sessionComboBox;
    private TableView<AttendanceRow> studentTable;

    public static class AttendanceRow {
        private final int studentId;
        private final String regNumber;
        private final String name;
        private final ComboBox<String> statusCombo;

        public AttendanceRow(int studentId, String regNumber, String name, String currentStatus) {
            this.studentId = studentId;
            this.regNumber = regNumber;
            this.name = name;
            this.statusCombo = new ComboBox<>(FXCollections.observableArrayList("Present", "Absent", "Late"));
            this.statusCombo.setStyle("-fx-background-radius: 4px;");
            if (currentStatus != null && !currentStatus.isEmpty()) {
                this.statusCombo.setValue(currentStatus);
            } else {
                this.statusCombo.setValue("Present");
            }
        }

        public int getStudentId() { return studentId; }
        public String getRegNumber() { return regNumber; }
        public String getName() { return name; }
        public ComboBox<String> getStatusCombo() { return statusCombo; }
        public String getStatus() { return statusCombo.getValue(); }
    }

    public AttendancePanel(UserDTO user) {
        this.currentUser = user;
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f8fafc;");
        VBox.setVgrow(this, Priority.ALWAYS);

        HBox selectorBar = new HBox(15);
        selectorBar.setAlignment(Pos.CENTER_LEFT);
        selectorBar.setStyle("-fx-background-color: white; -fx-padding: 15px 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");

        Label sessionLbl = new Label("Class Session");
        sessionLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");

        sessionComboBox = new ComboBox<>();
        sessionComboBox.setPromptText("Select a Class Session");
        sessionComboBox.setPrefWidth(450);
        sessionComboBox.setStyle("-fx-padding: 5px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");

        selectorBar.getChildren().addAll(sessionLbl, sessionComboBox);
        getChildren().add(selectorBar);

        VBox tableContainer = new VBox(15);
        tableContainer.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");
        VBox.setVgrow(tableContainer, Priority.ALWAYS);

        Label tableTitle = new Label("Student Roster & Attendance");
        tableTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        tableTitle.setStyle("-fx-text-fill: #1e293b;");

        studentTable = new TableView<>();
        studentTable.setStyle("-fx-background-radius: 4px; -fx-border-radius: 4px;");
        VBox.setVgrow(studentTable, Priority.ALWAYS);

        TableColumn<AttendanceRow, String> regCol = new TableColumn<>("Reg Number");
        regCol.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        regCol.setPrefWidth(150);

        TableColumn<AttendanceRow, String> nameCol = new TableColumn<>("Student Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(250);

        TableColumn<AttendanceRow, ComboBox<String>> statusCol = new TableColumn<>("Attendance Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("statusCombo"));
        statusCol.setPrefWidth(200);

        studentTable.getColumns().addAll(regCol, nameCol, statusCol);

        HBox actions = new HBox(10);
        actions.setAlignment(Pos.CENTER_RIGHT);
        Button saveBtn = new Button("Submit Attendance");
        saveBtn.setStyle("-fx-background-color: #4f46e5; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 6px; -fx-cursor: hand;");
        actions.getChildren().add(saveBtn);

        tableContainer.getChildren().addAll(tableTitle, studentTable, actions);
        getChildren().add(tableContainer);

        configureSessionComboBox();
        loadSessions();

        sessionComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadStudentsForSession(newVal);
            } else {
                studentTable.getItems().clear();
            }
        });

        saveBtn.setOnAction(e -> {
            ScheduleDTO session = sessionComboBox.getValue();
            if (session == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a class session first.");
                return;
            }

            List<AttendanceDTO> list = new ArrayList<>();
            for (AttendanceRow row : studentTable.getItems()) {
                list.add(new AttendanceDTO(0, session.getId(), row.getStudentId(), row.getStatus()));
            }

            try {
                if (attendanceController.saveAttendanceList(list)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Attendance recorded successfully.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to record attendance.");
            }
        });
    }

    private void configureSessionComboBox() {
        sessionComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(ScheduleDTO object) {
                return object == null ? "" : object.getDate().toString() + " [" + object.getTimeSlot() + "] - " + object.getCourseName() + " (" + object.getSubjectName() + ")";
            }
            @Override
            public ScheduleDTO fromString(String string) { return null; }
        });
    }

    private void loadSessions() {
        try {
            List<LecturerDTO> lecturers = lecturerController.getAllLecturers();
            int currentLecturerId = 0;
            for (LecturerDTO l : lecturers) {
                if (l.getUserId() != null && l.getUserId() == currentUser.getId()) {
                    currentLecturerId = l.getId();
                    break;
                }
            }
            List<ScheduleDTO> list = scheduleController.getSchedulesByLecturer(currentLecturerId);
            sessionComboBox.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentsForSession(ScheduleDTO session) {
        try {
            List<StudentDTO> students = studentController.getStudentsByCourse(session.getCourseId());
            List<AttendanceDTO> marked = attendanceController.getAttendanceBySchedule(session.getId());

            List<AttendanceRow> rowList = new ArrayList<>();
            for (StudentDTO s : students) {
                String currentStatus = "";
                for (AttendanceDTO a : marked) {
                    if (a.getStudentId() == s.getId()) {
                        currentStatus = a.getStatus();
                        break;
                    }
                }
                rowList.add(new AttendanceRow(s.getId(), s.getRegNumber(), s.getName(), currentStatus));
            }
            studentTable.setItems(FXCollections.observableArrayList(rowList));
        } catch (Exception e) {
            e.printStackTrace();
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
