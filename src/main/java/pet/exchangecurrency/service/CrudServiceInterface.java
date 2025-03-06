package pet.exchangecurrency.service;

import java.util.Collection;

public interface CrudServiceInterface <T> {

    public T create(T dto);

    public T getById(long id);

    public Collection<T> getAll();

    public T update(T dto);

    public void deleteById(long id);

}
