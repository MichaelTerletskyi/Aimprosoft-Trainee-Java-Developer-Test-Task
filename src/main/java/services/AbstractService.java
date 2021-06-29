package services;

import repositories.jdbc.AbstractJDBCRepository;

import java.util.Set;

/**
 * @Create 6/29/2021
 */

public abstract class AbstractService<T> {
    protected abstract AbstractJDBCRepository<T, Long> repository();

    public void create(T element) {
        repository().create(element);
    }

    public T getById(Long id) {
        return repository().getById(id);
    }

    public Set<T> getAll() {
        return repository().getAll();
    }

    public T update(T element) {
        return repository().update(element);
    }

    public void delete(Long id) {
        repository().delete(id);
    }

    public boolean existById(Long id) {
        return repository().existById(id);
    }
}