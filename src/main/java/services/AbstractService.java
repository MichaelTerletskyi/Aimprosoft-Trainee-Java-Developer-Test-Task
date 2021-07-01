package services;

import repositories.jdbc.AbstractJDBCRepository;

import java.util.Set;

/**
 * @Create 6/29/2021
 */

public abstract class AbstractService<T> {
    protected abstract AbstractJDBCRepository<T, Long> jdbcRepository();

    public void create(T element) {
        jdbcRepository().create(element);
    }

    public T getById(Long id) {
        return jdbcRepository().getById(id);
    }

    public Set<T> getAll() {
        return jdbcRepository().getAll();
    }

    public T update(T element) {
        return jdbcRepository().update(element);
    }

    public void delete(Long id) {
        jdbcRepository().delete(id);
    }

    public boolean existById(Long id) {
        return jdbcRepository().existById(id);
    }
}