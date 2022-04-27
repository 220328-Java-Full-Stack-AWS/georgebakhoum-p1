package com.revature.persistence;
import com.revature.models.User;

import java.util.List;

public interface CRUDInterface<T> {
    public T create(T obj);
    public T read(int id);
    public void update(T model);
    public void delete(int id);
    public List<T> getAll();
}
