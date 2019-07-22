package com.taocares.monitor.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IBaseService<T> {

    <S extends T> S saveOne(S val1);

    void deleteById(String id);

    <S extends T> void delete(S val1);

    List<T> findAll();

    List<T> findAll(Sort var1);

    List<T> findAllById(Iterable<String> var1);

    <S extends T> List<S> saveAll(Iterable<S> var1);

    void flush();

    <S extends T> S saveAndFlush(S var1);

    void deleteInBatch(Iterable<T> var1);

    void deleteAllInBatch();

    T getOne(String var1);

    <S extends T> List<S> findAll(Example<S> var1);

    <S extends T> List<S> findAll(Example<S> var1, Sort var2);
}
