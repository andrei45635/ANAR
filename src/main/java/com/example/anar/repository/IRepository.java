package com.example.anar.repository;

import com.example.anar.domain.Entity;
import java.util.List;

public interface IRepository<ID, E extends Entity<Integer>> {
    List<E> findAll();
    E save(E e);
}
