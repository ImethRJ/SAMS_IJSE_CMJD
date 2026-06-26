package com.ijse.sams.view;

import com.ijse.sams.controller.UserController;
import com.ijse.sams.dto.UserDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginView extends Stage {
    private final UserController userController = new UserController();

    public LoginView() {
        setTitle("SAMS Login");
        setResizable(false);

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: linear-gradient(to right, #4f46e5, #6366f1);");

        VBox loginCard = new VBox(20);
        loginCard.setMaxSize(360, 420);
        loginCard.setStyle("-fx-background-color: white; -fx-padding: 40px; -fx-background-radius: 12px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 15, 0, 0, 10);");
        loginCard.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Student Attendance");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        title.setStyle("-fx-text-fill: #1e1b4b;");

        Label subtitle = new Label("Sign in to your account");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        subtitle.setStyle("-fx-text-fill: #6b7280;");

        VBox headerBox = new VBox(5);
        headerBox.getChildren().addAll(title, subtitle);

        VBox userFieldBox = new VBox(5);
        Label userLabel = new Label("Username");
        userLabel.setStyle("-fx-text-fill: #4b5563; -fx-font-weight: bold; -fx-font-size: 13px;");
        TextField userField = new TextField();
        userField.setPromptText("Enter your username");
        userField.setStyle("-fx-padding: 10px; -fx-background-radius: 6px; -fx-border-color: #d1d5db; -fx-border-radius: 6px; -fx-background-color: #f9fafb;");
        userFieldBox.getChildren().addAll(userLabel, userField);

        VBox passFieldBox = new VBox(5);
        Label passLabel = new Label("Password");
        passLabel.setStyle("-fx-text-fill: #4b5563; -fx-font-weight: bold; -fx-font-size: 13px;");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter your password");
        passField.setStyle("-fx-padding: 10px; -fx-background-radius: 6px; -fx-border-color: #d1d5db; -fx-border-radius: 6px; -fx-background-color: #f9fafb;");
        passFieldBox.getChildren().addAll(passLabel, passField);

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #ef4444; -fx-font-size: 13px;");
        errorLabel.setWrapText(true);

        Button loginBtn = new Button("Sign In");
        loginBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.setStyle("-fx-background-color: #4f46e5; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 12px; -fx-background-radius: 6px; -fx-cursor: hand;");
        
        loginBtn.setOnMouseEntered(e -> loginBtn.setStyle("-fx-background-color: #4338ca; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 12px; -fx-background-radius: 6px; -fx-cursor: hand;"));
        loginBtn.setOnMouseExited(e -> loginBtn.setStyle("-fx-background-color: #4f46e5; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 12px; -fx-background-radius: 6px; -fx-cursor: hand;"));

        loginBtn.setOnAction(e -> {
            String username = userField.getText().trim();
            String password = passField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Please fill all fields.");
                return;
            }

            try {
                UserDTO user = userController.login(username, password);
                if (user != null) {
                    close();
                    new MainView(user).show();
                } else {
                    errorLabel.setText("Invalid username or password.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                errorLabel.setText("System error. Database may be offline.");
            }
        });

        loginCard.getChildren().addAll(headerBox, userFieldBox, passFieldBox, errorLabel, loginBtn);
        root.getChildren().add(loginCard);

        Scene scene = new Scene(root, 640, 520);
        setScene(scene);
    }
}
