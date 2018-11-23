package com.lrd.cool.api.repository;

import com.lrd.cool.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @author lierdong
 */
public interface ProductRepository extends JpaRepository<Product, Long>
        , JpaSpecificationExecutor<Product>, Serializable {


}
