/*
 * Copyright (c) 2020 KU (TEST statement for qulice style check)
 */
package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to SQLite db table "int_num" storing numbers.
 * @since 1.0
 */
@Repository
public interface IntNumRepository extends CrudRepository<IntNum, Long> {

    /**
     * Specifying findById method search of records in table "int_num".
     *   @param id Record search performed based on key field "id".
     *   @return Matching record
     */
    IntNum findById(long id);
}
