package dao_layer;

import entities.Entity;


public interface MainDaoLayer<T extends Entity, PK> {
    PK create(T entity) throws DaoExceptionLayer;
    T read(PK id) throws DaoExceptionLayer;
    void update(T entity) throws DaoExceptionLayer;
    void delete(PK id, String tableName) throws DaoExceptionLayer;
}
