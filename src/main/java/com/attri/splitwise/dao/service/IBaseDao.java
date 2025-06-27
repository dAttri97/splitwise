package com.attri.splitwise.dao.service;

import com.attri.splitwise.dao.repo.IBaseRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IBaseDao<E, ID extends Serializable> {
    E save(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    void deleteById(ID id);

    boolean existsById(ID id);

    long count();

    IBaseRepository<E, ID> getRepository();

    E saveAndFlush(E entity);
}
