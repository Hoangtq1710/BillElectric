package services;

import java.util.List;

public interface CRUDInterface<T> {
    void add(T t);
    void addAll(List<T> list);
    void show(List<T> list);
    List<T> findAll();
}
