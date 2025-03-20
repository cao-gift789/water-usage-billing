package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType, Integer> {
    CustomerType findByTypeName(String typeName);
}
