package com.appspc.apilibros.data.interfaces;

import java.util.List;

public interface IRepository<T,ID> {
    T findById(ID id);
    List<T> findAll();
    int save(T entity);
    int update(T entity);
    int delete(ID id);
}
