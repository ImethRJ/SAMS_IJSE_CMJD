package com.ijse.sams.dao.custom.impl;

import com.ijse.sams.dao.CrudUtil;
import com.ijse.sams.dao.custom.QueryDAO;
import com.ijse.sams.entity.CustomEntity;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> getDetailedAttendanceReport(Integer studentId, Integer courseId, Integer subjectId, Date startDate, Date endDate, Integer lecturerId) throws Exception {
        String sql = "SELECT s.name as student_name, s.reg_number, c.name as course_name, sub.name as subject_name, sch.date, sch.time_slot, COALESCE(a.status, 'Unmarked') as status " +
                     "FROM students s " +
                     "JOIN student_courses sc ON s.id = sc.student_id " +
                     "JOIN courses c ON sc.course_id = c.id " +
                     "JOIN schedules sch ON sch.course_id = c.id " +
                     "JOIN subjects sub ON sch.subject_id = sub.id " +
                     "LEFT JOIN attendance a ON a.schedule_id = sch.id AND a.student_id = s.id " +
                     "WHERE 1=1";

        List<Object> params = new ArrayList<>();
        StringBuilder query = new StringBuilder(sql);

        if (studentId != null && studentId > 0) {
            query.append(" AND s.id = ?");
            params.add(studentId);
        }
        if (courseId != null && courseId > 0) {
            query.append(" AND c.id = ?");
            params.add(courseId);
        }
        if (subjectId != null && subjectId > 0) {
            query.append(" AND sub.id = ?");
            params.add(subjectId);
        }
        if (startDate != null) {
            query.append(" AND sch.date >= ?");
            params.add(startDate);
        }
        if (endDate != null) {
            query.append(" AND sch.date <= ?");
            params.add(endDate);
        }
        if (lecturerId != null && lecturerId > 0) {
            query.append(" AND sch.lecturer_id = ?");
            params.add(lecturerId);
        }

        query.append(" ORDER BY sch.date DESC, sch.time_slot ASC");

        ResultSet rst = CrudUtil.executeQuery(query.toString(), params.toArray());
        List<CustomEntity> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new CustomEntity(
                rst.getString("student_name"),
                rst.getString("reg_number"),
                rst.getString("course_name"),
                rst.getString("subject_name"),
                rst.getDate("date"),
                rst.getString("time_slot"),
                rst.getString("status")
            ));
        }
        return list;
    }

    @Override
    public List<CustomEntity> getAttendanceSummaryReport(Integer courseId, Integer subjectId, Integer lecturerId) throws Exception {
        String sql = "SELECT s.name as student_name, s.reg_number, " +
                     "COUNT(a.id) as total_sessions, " +
                     "SUM(CASE WHEN a.status = 'Present' THEN 1 ELSE 0 END) as present_count, " +
                     "SUM(CASE WHEN a.status = 'Absent' THEN 1 ELSE 0 END) as absent_count, " +
                     "SUM(CASE WHEN a.status = 'Late' THEN 1 ELSE 0 END) as late_count " +
                     "FROM students s " +
                     "JOIN student_courses sc ON s.id = sc.student_id " +
                     "LEFT JOIN attendance a ON s.id = a.student_id " +
                     "LEFT JOIN schedules sch ON a.schedule_id = sch.id " +
                     "WHERE 1=1";

        List<Object> params = new ArrayList<>();
        StringBuilder query = new StringBuilder(sql);

        if (courseId != null && courseId > 0) {
            query.append(" AND sc.course_id = ?");
            params.add(courseId);
        }
        if (subjectId != null && subjectId > 0) {
            query.append(" AND sch.subject_id = ?");
            params.add(subjectId);
        }
        if (lecturerId != null && lecturerId > 0) {
            query.append(" AND sch.lecturer_id = ?");
            params.add(lecturerId);
        }

        query.append(" GROUP BY s.id, s.name, s.reg_number");

        ResultSet rst = CrudUtil.executeQuery(query.toString(), params.toArray());
        List<CustomEntity> list = new ArrayList<>();
        while (rst.next()) {
            int total = rst.getInt("total_sessions");
            int present = rst.getInt("present_count");
            int absent = rst.getInt("absent_count");
            int late = rst.getInt("late_count");
            double pct = total > 0 ? ((double) (present + late) / total) * 100.0 : 100.0;

            list.add(new CustomEntity(
                rst.getString("student_name"),
                rst.getString("reg_number"),
                total,
                present,
                absent,
                late,
                pct
            ));
        }
        return list;
    }
}
