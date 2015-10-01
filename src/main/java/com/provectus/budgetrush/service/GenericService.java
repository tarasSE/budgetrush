package com.provectus.budgetrush.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provectus.budgetrush.exceptions.CustomException;
import com.provectus.budgetrush.exceptions.ResourceNotFoundException;

public abstract class GenericService<E, R extends JpaRepository<E, Integer>> {

    public E createOrUpdate(E entity) {
        try {
            return getRepository().saveAndFlush(entity);
        } catch (Exception exception) {
            throw new CustomException("Can`t save resource. " + exception);
        }

    }

    public boolean delete(int id) {
        try {
            getRepository().delete(id);
        } catch (Exception exception) {
            throw new CustomException("Can`t delete resource. " + exception);
        }
        return getRepository().exists(id);
    }

    public E getById(int id) {
        E entity = getRepository().findOne(id);
        if (entity == null) {
            throw new ResourceNotFoundException();
        }
        return entity;
    }

    public List<E> getAll() {
        return getRepository().findAll();
    }

    protected abstract R getRepository();
}
