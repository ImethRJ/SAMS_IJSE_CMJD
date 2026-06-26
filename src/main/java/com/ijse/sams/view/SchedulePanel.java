package com.ijse.sams.view;

import com.ijse.sams.controller.CourseController;
import com.ijse.sams.controller.LecturerController;
import com.ijse.sams.controller.ScheduleController;
import com.ijse.sams.dto.CourseDTO;
import com.ijse.sams.dto.LecturerDTO;
import com.ijse.sams.dto.ScheduleDTO;
import com.ijse.sams.dto.SubjectDTO;
import com.ijse.sams.dto.UserDTO;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class SchedulePanel extends VBox {
    private final ScheduleController scheduleController = new ScheduleController();
    private final CourseController courseController = new CourseController();
    private final LecturerController lecturerController = new LecturerController();
    private final UserDTO currentUser;

    private TableView<ScheduleDTO> scheduleTable;
    private ComboBox<CourseDTO> courseComboBox;
    private ComboBox<SubjectDTO> subjectComboBox;
    private ComboBox<LecturerDTO> lecturerComboBox;
    private DatePicker datePicker;
    private ComboBox<String> timeSlotComboBox;
    private TextField hallField;
    private ScheduleDTO selectedSchedule;

    public SchedulePanel(UserDTO user) {
        this.currentUser = user;
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f8fafc;");
        VBox.setVgrow(this, Priority.ALWAYS);

        boolean isAdmin = "Admin".equalsIgnoreCase(user.getRole());

        VBox formContainer = new VBox(15);
        formContainer.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");

        Label title = new Label("Schedule Class Session");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        title.setStyle("-fx-text-fill: #1e293b;");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(12);

        Label courseLbl = new Label("Course");
        courseLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        courseComboBox = new ComboBox<>();
        courseComboBox.setPromptText("Select Course");
        courseComboBox.setStyle("-fx-padding: 5px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        courseComboBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(courseComboBox, Priority.ALWAYS);

        Label subjectLbl = new Label("Subject");
        subjectLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        subjectComboBox = new ComboBox<>();
        subjectComboBox.setPromptText("Select Subject");
        subjectComboBox.setStyle("-fx-padding: 5px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        subjectComboBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(subjectComboBox, Priority.ALWAYS);

        Label lecturerLbl = new Label("Lecturer");
        lecturerLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        lecturerComboBox = new ComboBox<>();
        lecturerComboBox.setPromptText("Select Lecturer");
        lecturerComboBox.setStyle("-fx-padding: 5px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        lecturerComboBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(lecturerComboBox, Priority.ALWAYS);

        Label dateLbl = new Label("Date");
        dateLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        datePicker = new DatePicker();
        datePicker.setPromptText("Select Date");
        datePicker.setStyle("-fx-padding: 5px; -fx-background-radius: 4px;");
        datePicker.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(datePicker, Priority.ALWAYS);

        Label timeLbl = new Label("Time Slot");
        timeLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        timeSlotComboBox = new ComboBox<>();
        timeSlotComboBox.setItems(FXCollections.observableArrayList(
            "08:30 AM - 10:30 AM",
            "10:30 AM - 12:30 PM",
            "01:30 PM - 03:30 PM",
            "03:30 PM - 05:30 PM"
        ));
        timeSlotComboBox.setPromptText("Select Time Slot");
        timeSlotComboBox.setStyle("-fx-padding: 5px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        timeSlotComboBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(timeSlotComboBox, Priority.ALWAYS);

        Label hallLbl = new Label("Hall Number");
        hallLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        hallField = new TextField();
        hallField.setPromptText("e.g. Hall 01, Lab A");
        hallField.setStyle("-fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        hallField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(hallField, Priority.ALWAYS);

        formGrid.add(courseLbl, 0, 0);
        formGrid.add(courseComboBox, 1, 0);
        formGrid.add(subjectLbl, 2, 0);
        formGrid.add(subjectComboBox, 3, 0);
        formGrid.add(lecturerLbl, 0, 1);
        formGrid.add(lecturerComboBox, 1, 1);
        formGrid.add(dateLbl, 2, 1);
        formGrid.add(datePicker, 3, 1);
        formGrid.add(timeLbl, 0, 2);
        formGrid.add(timeSlotComboBox, 1, 2);
        formGrid.add(hallLbl, 2, 2);
        formGrid.add(hallField, 3, 2);

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

        if (isAdmin) {
            getChildren().add(formContainer);
        }

        scheduleTable = new TableView<>();
        scheduleTable.setStyle("-fx-background-radius: 4px; -fx-border-radius: 4px;");
        VBox.setVgrow(scheduleTable, Priority.ALWAYS);

        TableColumn<ScheduleDTO, Date> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setPrefWidth(100);

        TableColumn<ScheduleDTO, String> timeCol = new TableColumn<>("Time Slot");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        timeCol.setPrefWidth(180);

        TableColumn<ScheduleDTO, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseCol.setPrefWidth(180);

        TableColumn<ScheduleDTO, String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        subjectCol.setPrefWidth(180);

        TableColumn<ScheduleDTO, String> lecCol = new TableColumn<>("Lecturer");
        lecCol.setCellValueFactory(new PropertyValueFactory<>("lecturerName"));
        lecCol.setPrefWidth(180);

        TableColumn<ScheduleDTO, String> hallCol = new TableColumn<>("Hall");
        hallCol.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        hallCol.setPrefWidth(120);

        scheduleTable.getColumns().addAll(dateCol, timeCol, courseCol, subjectCol, lecCol, hallCol);
        getChildren().add(scheduleTable);

        configureConverters();
        loadCourses();
        loadLecturers();
        loadSchedules();

        courseComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadSubjects(newVal.getId());
            } else {
                subjectComboBox.getItems().clear();
                lecturerComboBox.getItems().clear();
            }
        });

        subjectComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                filterLecturersBySubject(newVal.getId());
            } else {
                lecturerComboBox.getItems().clear();
            }
        });

        if (isAdmin) {
            scheduleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
                if (newSel != null) {
                    selectedSchedule = newSel;
                    datePicker.setValue(selectedSchedule.getDate().toLocalDate());
                    timeSlotComboBox.setValue(selectedSchedule.getTimeSlot());
                    hallField.setText(selectedSchedule.getHallNumber());
                    
                    for (CourseDTO c : courseComboBox.getItems()) {
                        if (c.getId() == selectedSchedule.getCourseId()) {
                            courseComboBox.setValue(c);
                            break;
                        }
                    }

                    for (SubjectDTO s : subjectComboBox.getItems()) {
                        if (s.getId() == selectedSchedule.getSubjectId()) {
                            subjectComboBox.setValue(s);
                            break;
                        }
                    }

                    filterLecturersBySubject(selectedSchedule.getSubjectId());

                    for (LecturerDTO l : lecturerComboBox.getItems()) {
                        if (l.getId() == selectedSchedule.getLecturerId()) {
                            lecturerComboBox.setValue(l);
                            break;
                        }
                    }
                }
            });

            saveBtn.setOnAction(e -> {
                CourseDTO c = courseComboBox.getValue();
                SubjectDTO s = subjectComboBox.getValue();
                LecturerDTO l = lecturerComboBox.getValue();
                LocalDate localDate = datePicker.getValue();
                String timeSlot = timeSlotComboBox.getValue();
                String hall = hallField.getText().trim();

                if (c == null || s == null || l == null || localDate == null || timeSlot == null || hall.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "All scheduling fields including Hall Number are required.");
                    return;
                }

                try {
                    ScheduleDTO dto = new ScheduleDTO(0, c.getId(), s.getId(), l.getId(), Date.valueOf(localDate), timeSlot, hall);
                    if (scheduleController.saveSchedule(dto)) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Class session scheduled successfully.");
                        clearForm();
                        loadSchedules();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to save schedule: " + ex.getMessage());
                }
            });

            updateBtn.setOnAction(e -> {
                if (selectedSchedule == null) {
                    showAlert(Alert.AlertType.WARNING, "Warning", "Select a schedule to update.");
                    return;
                }
                CourseDTO c = courseComboBox.getValue();
                SubjectDTO s = subjectComboBox.getValue();
                LecturerDTO l = lecturerComboBox.getValue();
                LocalDate localDate = datePicker.getValue();
                String timeSlot = timeSlotComboBox.getValue();
                String hall = hallField.getText().trim();

                if (c == null || s == null || l == null || localDate == null || timeSlot == null || hall.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "All scheduling fields including Hall Number are required.");
                    return;
                }

                try {
                    selectedSchedule.setCourseId(c.getId());
                    selectedSchedule.setSubjectId(s.getId());
                    selectedSchedule.setLecturerId(l.getId());
                    selectedSchedule.setDate(Date.valueOf(localDate));
                    selectedSchedule.setTimeSlot(timeSlot);
                    selectedSchedule.setHallNumber(hall);
                    if (scheduleController.updateSchedule(selectedSchedule)) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Schedule updated successfully.");
                        clearForm();
                        loadSchedules();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update schedule: " + ex.getMessage());
                }
            });

            deleteBtn.setOnAction(e -> {
                if (selectedSchedule == null) {
                    showAlert(Alert.AlertType.WARNING, "Warning", "Select a schedule to delete.");
                    return;
                }
                try {
                    if (scheduleController.deleteSchedule(selectedSchedule.getId())) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Schedule deleted successfully.");
                        clearForm();
                        loadSchedules();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete schedule.");
                }
            });

            resetBtn.setOnAction(e -> clearForm());
        }
    }

    private void configureConverters() {
        courseComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(CourseDTO object) {
                return object == null ? "" : object.getName();
            }
            @Override
            public CourseDTO fromString(String string) { return null; }
        });

        subjectComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(SubjectDTO object) {
                return object == null ? "" : object.getName();
            }
            @Override
            public SubjectDTO fromString(String string) { return null; }
        });

        lecturerComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(LecturerDTO object) {
                return object == null ? "" : object.getName();
            }
            @Override
            public LecturerDTO fromString(String string) { return null; }
        });
    }

    private void loadCourses() {
        try {
            courseComboBox.setItems(FXCollections.observableArrayList(courseController.getAllCourses()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLecturers() {
        try {
            lecturerComboBox.setItems(FXCollections.observableArrayList(lecturerController.getAllLecturers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSubjects(int courseId) {
        try {
            subjectComboBox.setItems(FXCollections.observableArrayList(courseController.getSubjectsByCourse(courseId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSchedules() {
        try {
            List<ScheduleDTO> list;
            if ("Lecturer".equalsIgnoreCase(currentUser.getRole())) {
                List<LecturerDTO> lecturers = lecturerController.getAllLecturers();
                int currentLecturerId = 0;
                for (LecturerDTO l : lecturers) {
                    if (l.getUserId() != null && l.getUserId() == currentUser.getId()) {
                        currentLecturerId = l.getId();
                        break;
                    }
                }
                list = scheduleController.getSchedulesByLecturer(currentLecturerId);
            } else {
                list = scheduleController.getAllSchedules();
            }
            scheduleTable.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filterLecturersBySubject(int subjectId) {
        try {
            List<LecturerDTO> all = lecturerController.getAllLecturers();
            List<LecturerDTO> filtered = new java.util.ArrayList<>();
            for (LecturerDTO l : all) {
                if (l.getAssignedSubjectIds() != null && l.getAssignedSubjectIds().contains(subjectId)) {
                    filtered.add(l);
                }
            }
            lecturerComboBox.setItems(FXCollections.observableArrayList(filtered));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        courseComboBox.setValue(null);
        subjectComboBox.setValue(null);
        lecturerComboBox.setValue(null);
        datePicker.setValue(null);
        timeSlotComboBox.setValue(null);
        hallField.clear();
        selectedSchedule = null;
        scheduleTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
