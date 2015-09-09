package com.provectus.budgetrush.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericService<E, R extends JpaRepository<E, Integer>> {

    public E createAndUpdate(E entity) {
        return getRepository().saveAndFlush(entity);
    }

    public void delete(int id) {
        getRepository().delete(id);
    }

    public E getById(int id) {
        return getRepository().findOne(id);
    }

    public List<E> getAll() {
        return getRepository().findAll();
    }

    protected abstract R getRepository();
}
