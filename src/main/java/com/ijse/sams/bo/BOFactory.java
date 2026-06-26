package com.ijse.sams.bo;

import com.ijse.sams.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory bf;

    private BOFactory() {
    }

    public static enum BOTypes {
        USER, COURSE, STUDENT, LECTURER, SCHEDULE, ATTENDANCE
    }

    public static BOFactory getInstance() {
        return (bf == null) ? bf = new BOFactory() : bf;
    }

    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case USER:
                return new UserBOImpl();
            case COURSE:
                return new CourseBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case LECTURER:
                return new LecturerBOImpl();
            case SCHEDULE:
                return new ScheduleBOImpl();
            case ATTENDANCE:
                return new AttendanceBOImpl();
            default:
                return null;
        }
    }
}
