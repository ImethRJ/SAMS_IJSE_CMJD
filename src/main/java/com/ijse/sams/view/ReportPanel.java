package com.ijse.sams.view;

import com.ijse.sams.controller.AttendanceController;
import com.ijse.sams.controller.CourseController;
import com.ijse.sams.controller.StudentController;
import com.ijse.sams.controller.LecturerController;
import com.ijse.sams.controller.ScheduleController;
import com.ijse.sams.dto.CourseDTO;
import com.ijse.sams.dto.CustomDTO;
import com.ijse.sams.dto.StudentDTO;
import com.ijse.sams.dto.SubjectDTO;
import com.ijse.sams.dto.LecturerDTO;
import com.ijse.sams.dto.ScheduleDTO;
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

public class ReportPanel extends VBox {
    private final AttendanceController attendanceController = new AttendanceController();
    private final CourseController courseController = new CourseController();
    private final StudentController studentController = new StudentController();
    private final LecturerController lecturerController = new LecturerController();
    private final ScheduleController scheduleController = new ScheduleController();
    private final UserDTO currentUser;

    private ComboBox<String> modeComboBox;
    private ComboBox<StudentDTO> studentComboBox;
    private ComboBox<CourseDTO> courseComboBox;
    private ComboBox<SubjectDTO> subjectComboBox;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;

    private TableView<CustomDTO> reportTable;
    private Label aggregateLabel;
    private VBox aggregateBox;

    public ReportPanel(UserDTO user) {
        this.currentUser = user;
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f8fafc;");
        VBox.setVgrow(this, Priority.ALWAYS);

        VBox filtersContainer = new VBox(15);
        filtersContainer.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");

        Label filterTitle = new Label("Report Configuration");
        filterTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        filterTitle.setStyle("-fx-text-fill: #1e293b;");

        GridPane filtersGrid = new GridPane();
        filtersGrid.setHgap(15);
        filtersGrid.setVgap(12);

        Label modeLbl = new Label("Report Type");
        modeLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        modeComboBox = new ComboBox<>(FXCollections.observableArrayList("Detailed Logs", "Attendance Percentages"));
        modeComboBox.setValue("Detailed Logs");
        modeComboBox.setStyle("-fx-padding: 5px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        modeComboBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(modeComboBox, Priority.ALWAYS);

        Label studentLbl = new Label("Student");
        studentLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        studentComboBox = new ComboBox<>();
        studentComboBox.setStyle("-fx-padding: 5px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        studentComboBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(studentComboBox, Priority.ALWAYS);

        Label courseLbl = new Label("Course");
        courseLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        courseComboBox = new ComboBox<>();
        courseComboBox.setStyle("-fx-padding: 5px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        courseComboBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(courseComboBox, Priority.ALWAYS);

        Label subjectLbl = new Label("Subject");
        subjectLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        subjectComboBox = new ComboBox<>();
        subjectComboBox.setStyle("-fx-padding: 5px; -fx-background-radius: 4px; -fx-border-color: #cbd5e1; -fx-border-radius: 4px;");
        subjectComboBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(subjectComboBox, Priority.ALWAYS);

        Label startLbl = new Label("Start Date");
        startLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        startDatePicker = new DatePicker();
        startDatePicker.setStyle("-fx-padding: 5px; -fx-background-radius: 4px;");
        startDatePicker.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(startDatePicker, Priority.ALWAYS);

        Label endLbl = new Label("End Date");
        endLbl.setStyle("-fx-text-fill: #475569; -fx-font-weight: bold;");
        endDatePicker = new DatePicker();
        endDatePicker.setStyle("-fx-padding: 5px; -fx-background-radius: 4px;");
        endDatePicker.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(endDatePicker, Priority.ALWAYS);

        filtersGrid.add(modeLbl, 0, 0);
        filtersGrid.add(modeComboBox, 1, 0);
        filtersGrid.add(studentLbl, 2, 0);
        filtersGrid.add(studentComboBox, 3, 0);
        filtersGrid.add(courseLbl, 0, 1);
        filtersGrid.add(courseComboBox, 1, 1);
        filtersGrid.add(subjectLbl, 2, 1);
        filtersGrid.add(subjectComboBox, 3, 1);
        filtersGrid.add(startLbl, 0, 2);
        filtersGrid.add(startDatePicker, 1, 2);
        filtersGrid.add(endLbl, 2, 2);
        filtersGrid.add(endDatePicker, 3, 2);

        HBox filterActions = new HBox(10);
        filterActions.setAlignment(Pos.CENTER_LEFT);
        Button generateBtn = new Button("Generate Report");
        generateBtn.setStyle("-fx-background-color: #4f46e5; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        Button resetBtn = new Button("Reset Filters");
        resetBtn.setStyle("-fx-background-color: #64748b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-background-radius: 4px; -fx-cursor: hand;");
        filterActions.getChildren().addAll(generateBtn, resetBtn);

        filtersContainer.getChildren().addAll(filterTitle, filtersGrid, filterActions);
        getChildren().add(filtersContainer);

        HBox contentBox = new HBox(20);
        VBox.setVgrow(contentBox, Priority.ALWAYS);

        reportTable = new TableView<>();
        reportTable.setStyle("-fx-background-radius: 4px; -fx-border-radius: 4px;");
        HBox.setHgrow(reportTable, Priority.ALWAYS);
        contentBox.getChildren().add(reportTable);

        aggregateBox = new VBox(10);
        aggregateBox.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.02), 10, 0, 0, 2);");
        aggregateBox.setPrefWidth(240);
        aggregateBox.setAlignment(Pos.CENTER);
        Label aggregateTitle = new Label("Average Attendance");
        aggregateTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        aggregateTitle.setStyle("-fx-text-fill: #64748b;");
        aggregateLabel = new Label("100.0%");
        aggregateLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 36));
        aggregateLabel.setStyle("-fx-text-fill: #4f46e5;");
        aggregateBox.getChildren().addAll(aggregateTitle, aggregateLabel);
        contentBox.getChildren().add(aggregateBox);

        getChildren().add(contentBox);

        configureConverters();
        loadStudents();
        loadCourses();

        courseComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.getId() > 0) {
                loadSubjects(newVal.getId());
            } else {
                subjectComboBox.getItems().clear();
                SubjectDTO allSubjects = new SubjectDTO(0, "All Subjects", 0);
                subjectComboBox.setItems(FXCollections.observableArrayList(allSubjects));
                subjectComboBox.setValue(allSubjects);
            }
        });

        modeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if ("Attendance Percentages".equals(newVal)) {
                studentComboBox.setValue(studentComboBox.getItems().get(0));
                studentComboBox.setDisable(true);
                startDatePicker.setValue(null);
                startDatePicker.setDisable(true);
                endDatePicker.setValue(null);
                endDatePicker.setDisable(true);
            } else {
                studentComboBox.setDisable(false);
                startDatePicker.setDisable(false);
                endDatePicker.setDisable(false);
            }
        });

        setupDetailedColumns();

        generateBtn.setOnAction(e -> generateReport());
        resetBtn.setOnAction(e -> resetFilters());
    }

    private void configureConverters() {
        studentComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(StudentDTO object) {
                return object == null ? "" : object.getName() + " (" + object.getRegNumber() + ")";
            }
            @Override
            public StudentDTO fromString(String string) { return null; }
        });

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
    }

    private Integer getLecturerId() {
        if (currentUser == null || !"Lecturer".equalsIgnoreCase(currentUser.getRole())) {
            return null;
        }
        try {
            List<LecturerDTO> lecturers = lecturerController.getAllLecturers();
            for (LecturerDTO l : lecturers) {
                if (l.getUserId() != null && l.getUserId() == currentUser.getId()) {
                    return l.getId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadStudents() {
        try {
            List<StudentDTO> students = studentController.getAllStudents();
            List<StudentDTO> filteredStudents = new java.util.ArrayList<>();

            if (currentUser != null && "Lecturer".equalsIgnoreCase(currentUser.getRole())) {
                Integer currentLecturerId = getLecturerId();
                if (currentLecturerId != null) {
                    List<ScheduleDTO> schedules = scheduleController.getSchedulesByLecturer(currentLecturerId);
                    java.util.Set<Integer> lecturerCourseIds = new java.util.HashSet<>();
                    for (ScheduleDTO s : schedules) {
                        lecturerCourseIds.add(s.getCourseId());
                    }
                    for (StudentDTO s : students) {
                        boolean teaches = false;
                        if (s.getCourseIds() != null) {
                            for (int cid : s.getCourseIds()) {
                                if (lecturerCourseIds.contains(cid)) {
                                    teaches = true;
                                    break;
                                }
                            }
                        }
                        if (teaches) {
                            filteredStudents.add(s);
                        }
                    }
                }
            } else {
                filteredStudents.addAll(students);
            }

            StudentDTO allStudents = new StudentDTO(0, "All Students", "ALL", "", "", 0);
            studentComboBox.getItems().clear();
            studentComboBox.getItems().add(allStudents);
            studentComboBox.getItems().addAll(filteredStudents);
            studentComboBox.setValue(allStudents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCourses() {
        try {
            List<CourseDTO> courses = courseController.getAllCourses();
            List<CourseDTO> filteredCourses = new java.util.ArrayList<>();

            if (currentUser != null && "Lecturer".equalsIgnoreCase(currentUser.getRole())) {
                Integer currentLecturerId = getLecturerId();
                if (currentLecturerId != null) {
                    List<ScheduleDTO> schedules = scheduleController.getSchedulesByLecturer(currentLecturerId);
                    java.util.Set<Integer> lecturerCourseIds = new java.util.HashSet<>();
                    for (ScheduleDTO s : schedules) {
                        lecturerCourseIds.add(s.getCourseId());
                    }
                    for (CourseDTO c : courses) {
                        if (lecturerCourseIds.contains(c.getId())) {
                            filteredCourses.add(c);
                        }
                    }
                }
            } else {
                filteredCourses.addAll(courses);
            }

            CourseDTO allCourses = new CourseDTO(0, "All Courses", "");
            courseComboBox.getItems().clear();
            courseComboBox.getItems().add(allCourses);
            courseComboBox.getItems().addAll(filteredCourses);
            courseComboBox.setValue(allCourses);

            SubjectDTO allSubjects = new SubjectDTO(0, "All Subjects", 0);
            subjectComboBox.getItems().clear();
            subjectComboBox.getItems().add(allSubjects);
            subjectComboBox.setValue(allSubjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSubjects(int courseId) {
        try {
            List<SubjectDTO> subjects = courseController.getSubjectsByCourse(courseId);
            List<SubjectDTO> filteredSubjects = new java.util.ArrayList<>();

            if (currentUser != null && "Lecturer".equalsIgnoreCase(currentUser.getRole())) {
                Integer currentLecturerId = getLecturerId();
                if (currentLecturerId != null) {
                    List<ScheduleDTO> schedules = scheduleController.getSchedulesByLecturer(currentLecturerId);
                    java.util.Set<Integer> lecturerSubjectIds = new java.util.HashSet<>();
                    for (ScheduleDTO s : schedules) {
                        if (s.getCourseId() == courseId) {
                            lecturerSubjectIds.add(s.getSubjectId());
                        }
                    }
                    for (SubjectDTO s : subjects) {
                        if (lecturerSubjectIds.contains(s.getId())) {
                            filteredSubjects.add(s);
                        }
                    }
                }
            } else {
                filteredSubjects.addAll(subjects);
            }

            SubjectDTO allSubjects = new SubjectDTO(0, "All Subjects", 0);
            subjectComboBox.getItems().clear();
            subjectComboBox.getItems().add(allSubjects);
            subjectComboBox.getItems().addAll(filteredSubjects);
            subjectComboBox.setValue(allSubjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupDetailedColumns() {
        reportTable.getColumns().clear();

        TableColumn<CustomDTO, String> nameCol = new TableColumn<>("Student Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        nameCol.setPrefWidth(150);

        TableColumn<CustomDTO, String> regCol = new TableColumn<>("Reg Number");
        regCol.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        regCol.setPrefWidth(100);

        TableColumn<CustomDTO, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseCol.setPrefWidth(130);

        TableColumn<CustomDTO, String> subCol = new TableColumn<>("Subject");
        subCol.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        subCol.setPrefWidth(130);

        TableColumn<CustomDTO, Date> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setPrefWidth(90);

        TableColumn<CustomDTO, String> timeCol = new TableColumn<>("Time Slot");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        timeCol.setPrefWidth(140);

        TableColumn<CustomDTO, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(80);

        reportTable.getColumns().addAll(nameCol, regCol, courseCol, subCol, dateCol, timeCol, statusCol);
    }

    private void setupSummaryColumns() {
        reportTable.getColumns().clear();

        TableColumn<CustomDTO, String> nameCol = new TableColumn<>("Student Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        nameCol.setPrefWidth(180);

        TableColumn<CustomDTO, String> regCol = new TableColumn<>("Reg Number");
        regCol.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        regCol.setPrefWidth(120);

        TableColumn<CustomDTO, Integer> sessionsCol = new TableColumn<>("Total Classes");
        sessionsCol.setCellValueFactory(new PropertyValueFactory<>("totalSessions"));
        sessionsCol.setPrefWidth(100);

        TableColumn<CustomDTO, Integer> presentCol = new TableColumn<>("Present");
        presentCol.setCellValueFactory(new PropertyValueFactory<>("presentCount"));
        presentCol.setPrefWidth(90);

        TableColumn<CustomDTO, Integer> absentCol = new TableColumn<>("Absent");
        absentCol.setCellValueFactory(new PropertyValueFactory<>("absentCount"));
        absentCol.setPrefWidth(90);

        TableColumn<CustomDTO, Integer> lateCol = new TableColumn<>("Late");
        lateCol.setCellValueFactory(new PropertyValueFactory<>("lateCount"));
        lateCol.setPrefWidth(90);

        TableColumn<CustomDTO, Double> pctCol = new TableColumn<>("Attendance %");
        pctCol.setCellValueFactory(new PropertyValueFactory<>("attendancePercentage"));
        pctCol.setPrefWidth(130);

        reportTable.getColumns().addAll(nameCol, regCol, sessionsCol, presentCol, absentCol, lateCol, pctCol);
    }

    private void generateReport() {
        String mode = modeComboBox.getValue();
        CourseDTO c = courseComboBox.getValue();
        SubjectDTO s = subjectComboBox.getValue();

        Integer courseId = (c != null && c.getId() > 0) ? c.getId() : null;
        Integer subjectId = (s != null && s.getId() > 0) ? s.getId() : null;
        Integer lecturerId = getLecturerId();

        try {
            if ("Detailed Logs".equals(mode)) {
                setupDetailedColumns();
                StudentDTO stud = studentComboBox.getValue();
                Integer studentId = (stud != null && stud.getId() > 0) ? stud.getId() : null;

                LocalDate start = startDatePicker.getValue();
                LocalDate end = endDatePicker.getValue();
                Date startDate = start != null ? Date.valueOf(start) : null;
                Date endDate = end != null ? Date.valueOf(end) : null;

                List<CustomDTO> report = attendanceController.getDetailedAttendanceReport(studentId, courseId, subjectId, startDate, endDate, lecturerId);
                reportTable.setItems(FXCollections.observableArrayList(report));

                int present = 0;
                int late = 0;
                int total = report.size();
                for (CustomDTO r : report) {
                    if ("Present".equalsIgnoreCase(r.getStatus())) present++;
                    else if ("Late".equalsIgnoreCase(r.getStatus())) late++;
                }
                double avg = total > 0 ? ((double) (present + late) / total) * 100.0 : 100.0;
                aggregateLabel.setText(String.format("%.1f%%", avg));

            } else {
                setupSummaryColumns();
                List<CustomDTO> report = attendanceController.getAttendanceSummaryReport(courseId, subjectId, lecturerId);
                reportTable.setItems(FXCollections.observableArrayList(report));

                double totalPct = 0;
                int count = report.size();
                for (CustomDTO r : report) {
                    totalPct += r.getAttendancePercentage();
                }
                double avg = count > 0 ? totalPct / count : 100.0;
                aggregateLabel.setText(String.format("%.1f%%", avg));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void resetFilters() {
        modeComboBox.setValue("Detailed Logs");
        if (studentComboBox.getItems().size() > 0) {
            studentComboBox.setValue(studentComboBox.getItems().get(0));
        }
        if (courseComboBox.getItems().size() > 0) {
            courseComboBox.setValue(courseComboBox.getItems().get(0));
        }
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        reportTable.getItems().clear();
        aggregateLabel.setText("100.0%");
    }
}
