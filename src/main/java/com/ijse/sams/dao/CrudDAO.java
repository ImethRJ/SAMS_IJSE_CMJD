package com.ijse.sams.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(int id) throws Exception;
    List<T> getAll() throws Exception;
}
