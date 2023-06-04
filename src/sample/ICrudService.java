package sample;

import java.util.List;

public interface ICrudService<T> {


    void add(String param);

    void update(T t);

    T findById(int id);

    List<T> findAll();

    boolean removeById(int id);

    boolean removeAll();

}
