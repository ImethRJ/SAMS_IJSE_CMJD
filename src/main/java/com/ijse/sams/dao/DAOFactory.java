package com.ijse.sams.dao;

import com.ijse.sams.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory df;

    private DAOFactory() {
    }

    public static enum DAOTypes {
        USER, COURSE, SUBJECT, STUDENT, LECTURER, SCHEDULE, ATTENDANCE, QUERY
    }

    public static DAOFactory getInstance() {
        return (df == null) ? df = new DAOFactory() : df;
    }

    public SuperDAO getDAO(DAOTypes type) {
        switch (type) {
            case USER:
                return new UserDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case SUBJECT:
                return new SubjectDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case LECTURER:
                return new LecturerDAOImpl();
            case SCHEDULE:
                return new ScheduleDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
