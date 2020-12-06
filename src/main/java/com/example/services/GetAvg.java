/*
 * Copyright (c) 2020 KU (TEST statement for qulice style check)
 */
package com.example.services;

import com.example.demo.IntNum;
import com.example.demo.IntNumRepository;

/**
 * Class implementing aggregation operation "Average of numbers".
 * @since 1.0
 */
public class GetAvg {

    /**
     * Execution count.
     */
    private long executed;

    /**
     * Constructor.
     */
    public GetAvg() {
        this.executed = 0;
    }

    /**
     * Function for calculating arithmetical mean of number series (default scope is all records).
     *   @param idsarray If this parameter is specified, then it calculates
     *                   the average only for affected records.
     *   @param repository SQLite db repository containing numbers
     *   @return Arithmetical mean of numbers
     */
    public Float execute(final long[] idsarray, final IntNumRepository repository) {
        Float avg = null;
        Float sum = null;
        int cnt = 0;

        if (idsarray != null && idsarray.length > 0) {
            for (final long id : idsarray) {
                final IntNum num = repository.findById(id);
                if (num != null) {
                    if (sum == null) {
                        sum = 0.0f;
                    }
                    sum += num.getNumber();
                    ++cnt;
                }
            }
        } else {
            for (final IntNum num : repository.findAll()) {
                if (sum == null) {
                    sum = 0.0f;
                }
                sum += num.getNumber();
                ++cnt;
            }
        }
        if (cnt > 0) {
            avg = sum / cnt;
        }
        this.executed = this.executed + 1;
        return avg;
    }
}
