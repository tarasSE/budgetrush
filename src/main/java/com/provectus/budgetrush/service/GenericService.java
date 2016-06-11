package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.BaseEntity;
import com.provectus.budgetrush.exceptions.CustomException;
import com.provectus.budgetrush.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional(readOnly = true)
public abstract class GenericService<E extends BaseEntity, R extends JpaRepository<E, Integer>> {

    @Transactional
    public E create(E entity) {
        entity.setId(0);
        try {
            return getRepository().saveAndFlush(entity);
        } catch (Exception exception) {
            throw new CustomException("Can`t create resource " + entity + "." + exception);
        }

    }

    @Transactional
    public E update(E entity, int id) {
        entity.setId(id);
        try {
            return getRepository().saveAndFlush(entity);
        } catch (Exception exception) {
            throw new CustomException("Can`t create resource. " + exception);
        }
    }

    @Transactional
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
