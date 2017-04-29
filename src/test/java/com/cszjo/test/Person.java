package com.cszjo.test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Han on 2017/4/2.
 */
public class Person {

    private final Date birthDate;
    private static final Calendar gmtCal;
    private static final Date BOOM_START;
    private static final Date BOOM_END;

    static {

        //when first use Person class, this field would instance,just only 1 time
        gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        BOOM_START = gmtCal.getTime();
        BOOM_END = gmtCal.getTime();
    }

    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBabyBoomer() {

        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        gmtCal.set(1956, Calendar.JANUARY, 1, 0, 0, 0);
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
    }

    public static void main(String[] args) {
        //use Long, would autoboxing, DON`T DO THIS
        Long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }
}
