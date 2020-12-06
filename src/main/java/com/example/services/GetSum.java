/*
 * Copyright (c) 2020 KU (TEST statement for qulice style check)
 */
package com.example.services;

import com.example.demo.IntNum;
import com.example.demo.IntNumRepository;

/**
 * Class implementing aggregation operation "Sum of numbers".
 * @since 1.0
 */
public class GetSum {

    /**
     * Execution count.
     */
    private long executed;

    /**
     * Constructor.
     */
    public GetSum() {
        this.executed = 0;
    }

    /**
     * Function for calculating sum of numbers (default scope is all records).
     *   @param idsarray If parameter is specified, then it calculates
     *                 numbers sum only for affected records.
     *   @param repository SQLite db repository containing numbers
     *   @return Sum of numbers
     */
    public Integer execute(final long[] idsarray, final IntNumRepository repository) {
        Integer sum = null;

        if (idsarray != null && idsarray.length > 0) {
            for (final long id : idsarray) {
                final IntNum num = repository.findById(id);
                if (num != null) {
                    if (sum == null) {
                        sum = 0;
                    }
                    sum += num.getNumber();
                }
            }
        } else {
            for (final IntNum num : repository.findAll()) {
                if (sum == null) {
                    sum = 0;
                }
                sum += num.getNumber();
            }
        }
        this.executed = this.executed + 1;
        return sum;
    }
}
