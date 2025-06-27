package com.attri.splitwise.dao.service.impl;

import com.attri.splitwise.dao.repo.IBaseRepository;
import com.attri.splitwise.dao.service.IBaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractDaoImpl<E, ID extends Serializable, R extends IBaseRepository<E, ID>>
        implements IBaseDao<E, ID> {

    @Autowired
    protected R repository;

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public R getRepository() {
        return repository;
    }

    @Override
    public E saveAndFlush(E entity) {
        return repository.saveAndFlush(entity);
    }


}
