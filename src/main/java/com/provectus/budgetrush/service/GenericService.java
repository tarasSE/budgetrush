package com.provectus.budgetrush.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericService <E, R extends JpaRepository<E, Integer>>{

    public E createAndUpdate(E entity){
        return getRepository().saveAndFlush(entity);
    }

    public void delete(int id){
        getRepository().delete(id);
    }

    public E getById(int id){
        return getRepository().findOne(id);
    }

    public List<E> getAll(){
        return getRepository().findAll();
    }

    protected abstract R getRepository();
}
