package com.taocares.monitor.service.impl;

import com.taocares.monitor.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseServiceImpl<M extends JpaRepository<T, String>, T> implements IBaseService<T> {
    @Autowired
    protected M baseRepository;

    @Override
    public <S extends T> S saveOne(S val1) {
        return this.baseRepository.save(val1);
    }

    @Override
    public void deleteById(String id) {
        this.baseRepository.deleteById(id);
    }

    @Override
    public <S extends T> void delete(S val1) {
        this.baseRepository.delete(val1);
    }

    @Override
    public List<T> findAll() {
        return this.baseRepository.findAll();
    }

    @Override
    public List<T> findAll(Sort var1) {
        return this.baseRepository.findAll(var1);
    }

    @Override
    public List<T> findAllById(Iterable<String> var1) {
        return this.baseRepository.findAllById(var1);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> var1) {
        return this.baseRepository.saveAll(var1);
    }

    @Override
    public void flush() {
        this.baseRepository.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S var1) {
        return this.baseRepository.saveAndFlush(var1);
    }

    @Override
    public void deleteInBatch(Iterable<T> var1) {
        this.baseRepository.deleteInBatch(var1);
    }

    @Override
    public void deleteAllInBatch() {
        this.baseRepository.deleteAllInBatch();
    }

    @Override
    public T getOne(String var1) {
        return this.baseRepository.getOne(var1);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> var1) {
        return this.baseRepository.findAll(var1);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> var1, Sort var2) {
        return this.baseRepository.findAll(var1, var2);
    }


}