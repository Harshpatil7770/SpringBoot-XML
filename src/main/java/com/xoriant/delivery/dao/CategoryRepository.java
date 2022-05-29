package com.xoriant.delivery.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xoriant.delivery.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
