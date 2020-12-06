/*
 * Copyright (c) 2020 KU (TEST statement for qulice style check)
 */
package com.example.demo;

import com.example.services.GetAvg;
import com.example.services.GetMax;
import com.example.services.GetMin;
import com.example.services.GetSum;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller implementing REST API.
 * @since 1.0
 */
@RestController
public class IntNumController {

    /**
     * Linked interface to db table "int_num" for accessing data.
     */
    @Autowired
    private IntNumRepository repository;

    /**
     * Application context for fetching predefined beans from XML.
     */
    private final ApplicationContext context;

    /**
     * Constructor for this controller.
     * Used for initializing application context with aggregation spring beans.
     */
    public IntNumController() {
        this.context = new ClassPathXmlApplicationContext("spring-beans.xml");
    }

    /**
     * REST API test method - produces test data and returns numbers sum for all records.
     *  @return Sum of numbers.
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Integer generateTestData() {
        // save few customers
        this.repository.save(new IntNum(10));
        this.repository.save(new IntNum(20));
        this.repository.save(new IntNum(30));
        final GetSum getsum = (GetSum) this.context.getBean("get_sum");
        return getsum.execute(null, this.repository);
    }

    /**
     * This REST API method for adding new number entry, returns Id of created record.
     *   @param num Received num entry by using HTTP POST method
     *   @return Id of created record
     */
    @RequestMapping(value = "/addNum", method = RequestMethod.POST, consumes = "application/json")
    public Long addNum(@RequestBody final IntNum num) {
        final IntNum numresult = this.repository.save(num);
        return numresult.getId();
    }

    /**
     * The REST API method for calculating aggregation function (default scope is all records).
     *   @param operation It supports 4 aggregation functions: Sum, Avg, Min, and Max.
     *   @param ids If this parameter is specified, then aggregation is
     *                      performed only for affected records.
     *   @return Id of created record.
     */
    @RequestMapping(value = "/getAggregation", method = RequestMethod.GET)
    public Float getAggrValue(@RequestParam(value = "operation",
                                            required = true) final String operation,
                              @RequestParam(value = "ids",
                                            required = false) final String ids) {
        Float aggresult = null;
        Integer intvalue = null;

        long[] idsarray = null;
        if (ids != null && !ids.isEmpty()) {
            idsarray = IntNumController.parseIds(ids);
        }

        if (operation.equalsIgnoreCase("sum")) {
            final GetSum opergetsum = (GetSum) this.context.getBean("get_sum");
            intvalue = opergetsum.execute(idsarray, this.repository);

        } else if (operation.equalsIgnoreCase("avg")) {
            final GetAvg opergetavg = (GetAvg) this.context.getBean("get_avg");
            aggresult = opergetavg.execute(idsarray, this.repository);

        } else if (operation.equalsIgnoreCase("min")) {
            final GetMin opergetmin = (GetMin) this.context.getBean("get_min");
            intvalue = opergetmin.execute(idsarray, this.repository);

        } else if (operation.equalsIgnoreCase("max")) {
            final GetMax opergetmax = (GetMax) this.context.getBean("get_max");
            intvalue = opergetmax.execute(idsarray, this.repository);

        } else {
            throw new Error("Wrong operation name!");
        }
        if (intvalue != null) {
            aggresult = (float) intvalue;
        }
        return aggresult;
    }

    /**
     * This function parses parameter "ids" by filtering duplicate Ids.
     *   @param ids Should be in format:  "id1,id2,...", e.g: "5,7,3"
     *   @return Array containing unique Ids
     */
    private static long[] parseIds(final String ids) {
        final String[] idsarray = ids.split(",");
        final long[] idsarraylong = new long[idsarray.length];

        //Check if provides Ids are of long type
        for (int cnt = 0; cnt < idsarray.length; cnt++) {
            idsarraylong[cnt] = Long.parseLong(idsarray[cnt]);
        }
        //Remove duplicate Ids entries if any
        final Set<Long> intset = new HashSet<>();
        for (final long elem : idsarraylong) {
            intset.add(elem);
        }
        //Produce result array with unique Ids
        final long[] idsresult = new long[intset.size()];
        int cnt = 0;
        for (final Long elem : intset) {
            idsresult[cnt++] = elem;
        }
        return idsresult;
    }
}
