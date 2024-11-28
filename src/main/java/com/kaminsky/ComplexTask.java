package com.kaminsky;

public class ComplexTask {

    public int execute() {
        int sum = 0;
        for(int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
