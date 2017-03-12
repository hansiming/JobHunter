package com.cszjo.jobhunter.model;

/**
 * Created by Han on 2017/3/5.
 */
public enum ResponseStatus {

    SUCCESS(1), FAIL(2);

    private final int value;

    private ResponseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
