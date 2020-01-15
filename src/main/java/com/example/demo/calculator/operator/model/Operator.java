package com.example.demo.calculator.operator.model;

/**
 * @Description: 运算类
 * @Author: CL
 * @Date: 2020/1/15 17:19
 */
public class Operator {

    /**
     * 第一个数
     */
    private Double firstNumber;
    /**
     * 第二个数
     */
    private Double secondNumber;

    public Double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public double getResult() throws Exception {
        double result = 0;
        return result;
    }
}
