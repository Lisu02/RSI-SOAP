package org.example.wssoapprojekt.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class GenericDAOImpl<T,K> implements GenericDAO<T,K>{

    HashMap<K,T> database = new HashMap<>();

    @Override
    public Optional<T> findById(K id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        database.forEach((k,t) -> {list.add(t);});
        return list;
    }

    @Override
    public void delete(K id) {
        database.remove(id);
    }
}
