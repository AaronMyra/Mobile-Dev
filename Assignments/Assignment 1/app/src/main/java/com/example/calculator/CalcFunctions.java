package com.example.calculator;

public class CalcFunctions {

    double calculate(String displayText, String operator, double value1, double value2 ){

        switch (operator) {

            case "plus":
                return value1 + value2;
            case "minus":
                return value1 - value2;
            case "multiply":
                return value1 * value2;
            case "divide":
                return value1 / value2;
            default:
                return 0.00;
        }
    }

    boolean isDecimal(double disFloat){

        if ((disFloat - ((int)disFloat)) == 0){
            return false;
        }
        else {
            return true;
        }
    }
}
