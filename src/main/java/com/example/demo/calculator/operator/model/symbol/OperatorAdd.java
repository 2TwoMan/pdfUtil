package com.example.demo.calculator.operator.model.symbol;

import com.example.demo.calculator.operator.model.Operator;

/**
 * @Description: 加法运算继承运算类
 * @Author: CL
 * @Date: 2020/1/15 19:48
 */
public class OperatorAdd extends Operator {
    @Override
    public double getResult() {
        Double firstNumber = super.getFirstNumber();
        Double secondNumber = super.getSecondNumber();
        return firstNumber + secondNumber;
    }
}
