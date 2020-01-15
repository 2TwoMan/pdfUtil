package com.example.demo.calculator.operator.model.symbol;

import com.example.demo.calculator.operator.model.Operator;

/**
 * @Description: 减法运算继承运算符类
 * @Author: CL
 * @Date: 2020/1/15 19:55
 */
public class OperatorSub extends Operator {
    @Override
    public double getResult() {
        Double firstNumber = super.getFirstNumber();
        Double secondNumber = super.getSecondNumber();
        return firstNumber - secondNumber;
    }
}
