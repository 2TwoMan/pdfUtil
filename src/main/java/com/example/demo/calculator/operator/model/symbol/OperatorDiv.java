package com.example.demo.calculator.operator.model.symbol;

import com.example.demo.calculator.operator.model.Operator;

/**
 * @Description: 乘法运算继承运算符类
 * @Author: CL
 * @Date: 2020/1/15 19:55
 */
public class OperatorDiv extends Operator {
    @Override
    public double getResult() throws Exception {
        Double firstNumber = super.getFirstNumber();
        Double secondNumber = super.getSecondNumber();

        if (secondNumber == 0){
            throw new Exception("除数不能为0。");
        }
        return firstNumber / secondNumber;
    }
}
