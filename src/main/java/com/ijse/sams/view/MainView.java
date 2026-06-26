package com.ijse.sams.view;

import com.ijse.sams.dto.UserDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainView extends Stage {
    private final UserDTO currentUser;
    private final BorderPane rightContainer;
    private final Label viewTitleLabel;
    private VBox sidebar;

    public MainView(UserDTO user) {
        this.currentUser = user;
        setTitle("Student Attendance Management System (SAMS)");
        setWidth(1100);
        setHeight(750);
        setMinWidth(950);
        setMinHeight(650);

        HBox root = new HBox();
        root.setStyle("-fx-background-color: #f8fafc;");

        sidebar = new VBox(15);
        sidebar.setPrefWidth(240);
        sidebar.setStyle("-fx-background-color: #0f172a; -fx-padding: 25px 15px;");
        sidebar.setAlignment(Pos.TOP_LEFT);

        Label appName = new Label("SAMS PRO");
        appName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        appName.setStyle("-fx-text-fill: #6366f1; -fx-padding: 0 0 10 10;");

        Label welcomeText = new Label("Welcome, " + user.getUsername());
        welcomeText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        welcomeText.setStyle("-fx-text-fill: #ffffff; -fx-padding: 0 0 0 10;");

        Label roleText = new Label(user.getRole() + " Portal");
        roleText.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12));
        roleText.setStyle("-fx-text-fill: #64748b; -fx-padding: 0 0 20 10;");

        VBox userProfile = new VBox(2);
        userProfile.getChildren().addAll(welcomeText, roleText);

        sidebar.getChildren().addAll(appName, userProfile);

        rightContainer = new BorderPane();
        HBox.setHgrow(rightContainer, Priority.ALWAYS);

        HBox headerBar = new HBox();
        headerBar.setStyle("-fx-background-color: #ffffff; -fx-padding: 15px 30px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 1);");
        headerBar.setAlignment(Pos.CENTER_LEFT);
        
        viewTitleLabel = new Label("Dashboard");
        viewTitleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        viewTitleLabel.setStyle("-fx-text-fill: #1e293b;");
        headerBar.getChildren().add(viewTitleLabel);
        
        rightContainer.setTop(headerBar);

        setupNavigation();

        root.getChildren().addAll(sidebar, rightContainer);
        Scene scene = new Scene(root);
        setScene(scene);
        
        boolean isAdmin = "Admin".equalsIgnoreCase(currentUser.getRole());
        if (isAdmin) {
            showView("Dashboard", new DashboardPanel());
        } else {
            showView("Class Schedules", new SchedulePanel(currentUser));
        }
    }

    private void setupNavigation() {
        boolean isAdmin = "Admin".equalsIgnoreCase(currentUser.getRole());

        if (isAdmin) {
            Button dbBtn = createNavButton("Dashboard");
            dbBtn.setOnAction(e -> showView("Dashboard", new DashboardPanel()));
            sidebar.getChildren().add(dbBtn);

            Button courseBtn = createNavButton("Courses");
            courseBtn.setOnAction(e -> showView("Course & Subject Management", new CoursePanel()));
            sidebar.getChildren().add(courseBtn);

            Button studentBtn = createNavButton("Students");
            studentBtn.setOnAction(e -> showView("Student Management", new StudentPanel()));
            sidebar.getChildren().add(studentBtn);

            Button lecturerBtn = createNavButton("Lecturers");
            lecturerBtn.setOnAction(e -> showView("Lecturer Management", new LecturerPanel()));
            sidebar.getChildren().add(lecturerBtn);
        }

        Button scheduleBtn = createNavButton("Schedules");
        scheduleBtn.setOnAction(e -> showView("Class Schedules", new SchedulePanel(currentUser)));
        sidebar.getChildren().add(scheduleBtn);

        if (!isAdmin) {
            Button attendanceBtn = createNavButton("Attendance Marking");
            attendanceBtn.setOnAction(e -> showView("Attendance Marking", new AttendancePanel(currentUser)));
            sidebar.getChildren().add(attendanceBtn);
        }

        Button reportBtn = createNavButton("Reports");
        reportBtn.setOnAction(e -> showView("Attendance Reports", new ReportPanel(currentUser)));
        sidebar.getChildren().add(reportBtn);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().add(spacer);

        Button logoutBtn = new Button("Sign Out");
        logoutBtn.setMaxWidth(Double.MAX_VALUE);
        logoutBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #f1f5f9; -fx-alignment: center-left; -fx-padding: 10px 15px; -fx-background-radius: 6px; -fx-font-weight: bold; -fx-cursor: hand;");
        logoutBtn.setOnAction(e -> {
            close();
            new LoginView().show();
        });
        sidebar.getChildren().add(logoutBtn);
    }

    private Button createNavButton(String text) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #94a3b8; -fx-alignment: center-left; -fx-padding: 10px 15px; -fx-background-radius: 6px; -fx-font-weight: 500; -fx-cursor: hand;");
        return btn;
    }

    private void showView(String title, Pane panel) {
        viewTitleLabel.setText(title);
        rightContainer.setCenter(panel);

        for (javafx.scene.Node node : sidebar.getChildren()) {
            if (node instanceof Button) {
                Button btn = (Button) node;
                if ("Sign Out".equals(btn.getText())) continue;
                
                if (btn.getText().equals(title) || 
                    (title.contains("Course") && btn.getText().equals("Courses")) ||
                    (title.contains("Attendance") && btn.getText().equals("Attendance Marking")) ||
                    (title.contains("Schedules") && btn.getText().equals("Schedules")) ||
                    (title.contains("Reports") && btn.getText().equals("Reports"))) {
                    btn.setStyle("-fx-background-color: #4f46e5; -fx-text-fill: #ffffff; -fx-alignment: center-left; -fx-padding: 10px 15px; -fx-background-radius: 6px; -fx-font-weight: bold; -fx-cursor: hand;");
                } else {
                    btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #94a3b8; -fx-alignment: center-left; -fx-padding: 10px 15px; -fx-background-radius: 6px; -fx-font-weight: 500; -fx-cursor: hand;");
                }
            }
        }
    }
}
