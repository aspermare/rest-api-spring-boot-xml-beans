/*
 * Copyright (c) 2020 KU (TEST statement for qulice style check)
 */
package com.example.services;

import com.example.demo.IntNum;
import com.example.demo.IntNumRepository;

/**
 * Class implementing aggregation operation "Max number".
 * @since 1.0
 */
public class GetMax {

    /**
     * Execution count.
     */
    private long executed;

    /**
     * Constructor.
     */
    public GetMax() {
        this.executed = 0;
    }

    /**
     * Function for getting maximum number (default scope is all records).
     *   @param idsarray If this parameter is specified, then maximum number is
     *                 fetched only for affected records.
     *   @param repository SQLite db repository containing numbers
     *   @return Maximum number
     */
    public Integer execute(final long[] idsarray, final IntNumRepository repository) {
        Integer max = null;

        if (idsarray != null && idsarray.length > 0) {
            int cnt = 1;
            for (final long id : idsarray) {
                final IntNum num = repository.findById(id);
                if (num != null) {
                    if (cnt == 1 || (cnt > 1 && max < num.getNumber())) {
                        max = num.getNumber();
                        ++cnt;
                    }
                }
            }
        } else {
            int cnt = 1;
            for (final IntNum num : repository.findAll()) {
                if (cnt == 1 || (cnt > 1 && max < num.getNumber())) {
                    max = num.getNumber();
                    ++cnt;
                }
            }
        }
        this.executed = this.executed + 1;
        return max;
    }
}
