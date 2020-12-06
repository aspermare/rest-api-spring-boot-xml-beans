/*
 * Copyright (c) 2020 KU (TEST statement for qulice style check)
 */
package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IntNum - class associated with SQLite db table "int_num".
 * Storing integer numbers having two fields: "id" and "number".
 * @since 1.0
 */
@Entity
@Table(name = "int_num")
public class IntNum {

    /**
     * Field "id" - key field with autoincrement.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field "number" - data field containing integer number.
     */
    private Integer number = 0;

    /**
     * Empty constructor required by persistence framework.
     */
    public IntNum() {
        // Empty constructor.
    }

    /**
     * Constructor for adding new number entry.
     *   @param number Adding new number
     */
    public IntNum(final Integer number) {
        this.number = number;
    }

    /**
     * Getter method for field "id".
     *   @return Value of field "id"
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter method for field "id".
    *   @param newid New value for setting field "id"
     */
    public void setId(final Long newid) {
        this.id = newid;
    }

    /**
     * Getter method for field "number".
     *   @return Value of field "number"
     */
    public Integer getNumber() {
        return this.number;
    }

    /**
     * Setter method for field "number".
     *   @param newnumber New value for setting field "number"
     */
    public void setNumber(final Integer newnumber) {
        this.number = newnumber;
    }
}
