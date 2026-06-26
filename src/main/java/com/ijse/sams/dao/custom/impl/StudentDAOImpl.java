package com.ijse.sams.dao.custom.impl;

import com.ijse.sams.dao.CrudUtil;
import com.ijse.sams.dao.custom.StudentDAO;
import com.ijse.sams.db.DBConnection;
import com.ijse.sams.entity.StudentEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(StudentEntity entity) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement pstm = conn.prepareStatement(
                "INSERT INTO students (name, reg_number, email, contact) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            pstm.setString(1, entity.getName());
            pstm.setString(2, entity.getRegNumber());
            pstm.setString(3, entity.getEmail());
            pstm.setString(4, entity.getContact());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 0) {
                conn.rollback();
                return false;
            }

            int studentId = 0;
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    studentId = generatedKeys.getInt(1);
                }
            }

            if (studentId == 0) {
                conn.rollback();
                return false;
            }

            for (int courseId : entity.getCourseIds()) {
                PreparedStatement pstmCourse = conn.prepareStatement(
                    "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)"
                );
                pstmCourse.setInt(1, studentId);
                pstmCourse.setInt(2, courseId);
                pstmCourse.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean update(StudentEntity entity) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement pstm = conn.prepareStatement(
                "UPDATE students SET name = ?, reg_number = ?, email = ?, contact = ? WHERE id = ?"
            );
            pstm.setString(1, entity.getName());
            pstm.setString(2, entity.getRegNumber());
            pstm.setString(3, entity.getEmail());
            pstm.setString(4, entity.getContact());
            pstm.setInt(5, entity.getId());

            pstm.executeUpdate();

            PreparedStatement pstmDelete = conn.prepareStatement(
                "DELETE FROM student_courses WHERE student_id = ?"
            );
            pstmDelete.setInt(1, entity.getId());
            pstmDelete.executeUpdate();

            for (int courseId : entity.getCourseIds()) {
                PreparedStatement pstmCourse = conn.prepareStatement(
                    "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)"
                );
                pstmCourse.setInt(1, entity.getId());
                pstmCourse.setInt(2, courseId);
                pstmCourse.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM students WHERE id = ?", id);
    }

    @Override
    public List<StudentEntity> getAll() throws Exception {
        List<StudentEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM students");
        while (rst.next()) {
            StudentEntity student = new StudentEntity();
            student.setId(rst.getInt("id"));
            student.setName(rst.getString("name"));
            student.setRegNumber(rst.getString("reg_number"));
            student.setEmail(rst.getString("email"));
            student.setContact(rst.getString("contact"));

            List<Integer> courseIds = new ArrayList<>();
            ResultSet rstCourses = CrudUtil.executeQuery("SELECT course_id FROM student_courses WHERE student_id = ?", student.getId());
            while (rstCourses.next()) {
                courseIds.add(rstCourses.getInt("course_id"));
            }
            student.setCourseIds(courseIds);
            list.add(student);
        }
        return list;
    }

    @Override
    public List<StudentEntity> getStudentsByCourse(int courseId) throws Exception {
        List<StudentEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(
            "SELECT s.* FROM students s INNER JOIN student_courses sc ON s.id = sc.student_id WHERE sc.course_id = ?",
            courseId
        );
        while (rst.next()) {
            StudentEntity student = new StudentEntity();
            student.setId(rst.getInt("id"));
            student.setName(rst.getString("name"));
            student.setRegNumber(rst.getString("reg_number"));
            student.setEmail(rst.getString("email"));
            student.setContact(rst.getString("contact"));

            List<Integer> courseIds = new ArrayList<>();
            ResultSet rstCourses = CrudUtil.executeQuery("SELECT course_id FROM student_courses WHERE student_id = ?", student.getId());
            while (rstCourses.next()) {
                courseIds.add(rstCourses.getInt("course_id"));
            }
            student.setCourseIds(courseIds);
            list.add(student);
        }
        return list;
    }
}
