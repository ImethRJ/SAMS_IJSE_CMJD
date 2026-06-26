package com.ijse.sams;

import com.ijse.sams.db.DBConnection;
import com.ijse.sams.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        initializeDatabase();
        new LoginView().show();
    }

    private void initializeDatabase() {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stm = conn.createStatement();

            stm.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50) NOT NULL UNIQUE," +
                    "password VARCHAR(100) NOT NULL," +
                    "role VARCHAR(20) NOT NULL" +
                    ")");

            stm.execute("CREATE TABLE IF NOT EXISTS courses (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "description VARCHAR(255)" +
                    ")");

            stm.execute("CREATE TABLE IF NOT EXISTS subjects (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "course_id INT NOT NULL," +
                    "FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE" +
                    ")");

            stm.execute("CREATE TABLE IF NOT EXISTS students (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "reg_number VARCHAR(50) NOT NULL UNIQUE," +
                    "email VARCHAR(100)," +
                    "contact VARCHAR(20)" +
                    ")");

            stm.execute("CREATE TABLE IF NOT EXISTS student_courses (" +
                    "student_id INT NOT NULL," +
                    "course_id INT NOT NULL," +
                    "PRIMARY KEY (student_id, course_id)," +
                    "FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE" +
                    ")");

            stm.execute("CREATE TABLE IF NOT EXISTS lecturers (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100)," +
                    "contact VARCHAR(20)" +
                    ")");

            stm.execute("CREATE TABLE IF NOT EXISTS lecturer_subjects (" +
                    "lecturer_id INT NOT NULL," +
                    "subject_id INT NOT NULL," +
                    "PRIMARY KEY (lecturer_id, subject_id)," +
                    "FOREIGN KEY (lecturer_id) REFERENCES lecturers(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE" +
                    ")");

            stm.execute("CREATE TABLE IF NOT EXISTS schedules (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "course_id INT NOT NULL," +
                    "subject_id INT NOT NULL," +
                    "lecturer_id INT NOT NULL," +
                    "date DATE NOT NULL," +
                    "time_slot VARCHAR(50) NOT NULL," +
                    "hall_number VARCHAR(50) NOT NULL," +
                    "FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (lecturer_id) REFERENCES lecturers(id) ON DELETE CASCADE" +
                    ")");

            stm.execute("CREATE TABLE IF NOT EXISTS attendance (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "schedule_id INT NOT NULL," +
                    "student_id INT NOT NULL," +
                    "status VARCHAR(20) NOT NULL," +
                    "FOREIGN KEY (schedule_id) REFERENCES schedules(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE," +
                    "UNIQUE KEY unique_attendance (schedule_id, student_id)" +
                    ")");

            boolean hasCourseId = false;
            try (ResultSet rs = conn.getMetaData().getColumns(null, null, "students", "course_id")) {
                if (rs.next()) {
                    hasCourseId = true;
                }
            }
            if (hasCourseId) {

                stm.execute(
                        "INSERT IGNORE INTO student_courses (student_id, course_id) SELECT id, course_id FROM students");

                String fkName = null;
                String query = "SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " +
                        "WHERE TABLE_SCHEMA = 'sams' AND TABLE_NAME = 'students' AND COLUMN_NAME = 'course_id' " +
                        "AND REFERENCED_TABLE_NAME = 'courses'";
                try (ResultSet rs = stm.executeQuery(query)) {
                    if (rs.next()) {
                        fkName = rs.getString(1);
                    }
                }
                if (fkName != null) {
                    stm.execute("ALTER TABLE students DROP FOREIGN KEY " + fkName);
                }
                stm.execute("ALTER TABLE students DROP COLUMN course_id");
            }

            boolean hasHallNumber = false;
            try (ResultSet rs = conn.getMetaData().getColumns(null, null, "schedules", "hall_number")) {
                if (rs.next()) {
                    hasHallNumber = true;
                }
            }
            if (!hasHallNumber) {
                stm.execute("ALTER TABLE schedules ADD COLUMN hall_number VARCHAR(50) NOT NULL DEFAULT 'Main Hall'");
            }

            ResultSet rst = stm.executeQuery("SELECT COUNT(*) FROM users");
            if (rst.next() && rst.getInt(1) == 0) {
                stm.execute("INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'Admin')");
                stm.execute(
                        "INSERT INTO users (username, password, role) VALUES ('lecturer', 'lecturer123', 'Lecturer')");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
