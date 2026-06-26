package com.ijse.sams.view;

import com.ijse.sams.controller.CourseController;
import com.ijse.sams.controller.LecturerController;
import com.ijse.sams.controller.ScheduleController;
import com.ijse.sams.controller.StudentController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DashboardPanel extends VBox {
    private final CourseController courseController = new CourseController();
    private final StudentController studentController = new StudentController();
    private final LecturerController lecturerController = new LecturerController();
    private final ScheduleController scheduleController = new ScheduleController();

    public DashboardPanel() {
        setSpacing(25);
        setPadding(new Insets(30));
        setStyle("-fx-background-color: #f8fafc;");

        Label welcome = new Label("Institutional Overview");
        welcome.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        welcome.setStyle("-fx-text-fill: #1e293b;");
        getChildren().add(welcome);

        FlowPane cardsPane = new FlowPane();
        cardsPane.setHgap(20);
        cardsPane.setVgap(20);

        int coursesCount = 0;
        int studentsCount = 0;
        int lecturersCount = 0;
        int schedulesCount = 0;

        try {
            coursesCount = courseController.getAllCourses().size();
            studentsCount = studentController.getAllStudents().size();
            lecturersCount = lecturerController.getAllLecturers().size();
            schedulesCount = scheduleController.getAllSchedules().size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cardsPane.getChildren().add(createStatCard("Total Courses", String.valueOf(coursesCount), "#4f46e5"));
        cardsPane.getChildren().add(createStatCard("Registered Students", String.valueOf(studentsCount), "#06b6d4"));
        cardsPane.getChildren().add(createStatCard("Active Lecturers", String.valueOf(lecturersCount), "#10b981"));
        cardsPane.getChildren().add(createStatCard("Scheduled Sessions", String.valueOf(schedulesCount), "#f59e0b"));

        getChildren().add(cardsPane);
    }

    private VBox createStatCard(String titleText, String countText, String accentColor) {
        VBox card = new VBox(10);
        card.setPrefSize(200, 120);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 10, 0, 0, 4); -fx-border-color: " + accentColor + "; -fx-border-width: 0 0 4 0; -fx-border-radius: 8px;");
        card.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label(titleText);
        title.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
        title.setStyle("-fx-text-fill: #64748b;");

        Label count = new Label(countText);
        count.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        count.setStyle("-fx-text-fill: #1e293b;");

        card.getChildren().addAll(title, count);
        return card;
    }
}
