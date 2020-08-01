package com.tulies.api.repository;

import com.tulies.api.entity.Generator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GeneratorRepository extends JpaRepository<Generator,Integer>, JpaSpecificationExecutor {
    Generator findByKey(String key);
}
