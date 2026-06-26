package com.ijse.sams.dao;

import com.ijse.sams.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private static PreparedStatement getPreparedStatement(String sql, Object... values) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        int i = 0;
        for (Object value : values) {
            ++i;
            pstm.setObject(i, value);
        }
        return pstm;
    }

    public static boolean executeUpdate(String sql, Object... values) throws SQLException {
        PreparedStatement pstm = getPreparedStatement(sql, values);
        return pstm.executeUpdate() > 0;
    }

    public static ResultSet executeQuery(String sql, Object... values) throws SQLException {
        PreparedStatement pstm = getPreparedStatement(sql, values);
        return pstm.executeQuery();
    }
}
