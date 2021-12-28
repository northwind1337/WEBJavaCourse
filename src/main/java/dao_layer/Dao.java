package dao_layer;

import domain.Entity;


public interface Dao<T extends Entity, PK> {
    PK create(T entity) throws DaoException;
    T read(PK id) throws DaoException;
    void update(T entity) throws DaoException;
    void delete(PK id, String tableName) throws DaoException;
}
