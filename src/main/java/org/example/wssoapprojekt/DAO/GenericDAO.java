package org.example.wssoapprojekt.DAO;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T,K> {

    public Optional<T> findById(K id);
    public List<T> findAll();
    public void delete(K id);


}
