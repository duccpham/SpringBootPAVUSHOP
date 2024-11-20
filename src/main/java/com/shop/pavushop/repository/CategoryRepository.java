package com.shop.pavushop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.pavushop.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
