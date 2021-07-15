package services;

import repositories.jdbc.AbstractJDBCRepository;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Create 6/29/2021
 */

public abstract class AbstractService<T> {
    protected abstract AbstractJDBCRepository<T> jdbcRepository();

    public T create(T element) {
        return jdbcRepository().create(element);
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

    /**
     * This method parse getQueryString() to id's departments and employees
     * @View more info {@link HttpServletRequest}
     */

    protected List<Long> extractIds(String queryString) {
        String[] split = queryString.replaceAll("[^0-9]", " ").split(" ");
        List<Long> arr = new ArrayList<>();
        for (String s : split) {
            if (!s.isEmpty()) {
                arr.add(Long.parseLong(s));
            }
        }
        return arr;
    }
}