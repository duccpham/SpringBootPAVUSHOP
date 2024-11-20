package com.shop.pavushop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.pavushop.entity.Brand;



@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
