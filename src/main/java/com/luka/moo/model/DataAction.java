package com.luka.moo.model;

import java.util.List;

public interface DataAction<E extends DbObject> {

    void save(E object);

    List<E> getAll();

    E getOne(String id);

//    List<E> getAllWhere(List<Filter> filters);


}
