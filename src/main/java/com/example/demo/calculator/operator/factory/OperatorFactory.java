package com.example.demo.calculator.operator.factory;

import com.example.demo.calculator.operator.model.Operator;
import com.example.demo.calculator.operator.model.symbol.OperatorAdd;
import com.example.demo.calculator.operator.model.symbol.OperatorDiv;
import com.example.demo.calculator.operator.model.symbol.OperatorMul;
import com.example.demo.calculator.operator.model.symbol.OperatorSub;

/**
 * @Description: 运算工厂类
 * @Author: CL
 * @Date: 2020/1/15 20:01
 */
public class OperatorFactory {

    /**
     * 创建运算符
     * @param operate
     * @return
     */
    public static Operator createOperate(String operate){
        Operator operator = null;
        switch (operate){
            case "+":
                operator = new OperatorAdd();
                break;
            case "-":
                operator = new OperatorSub();
                break;
            case "*":
                operator = new OperatorMul();
                break;
            case "/":
                operator = new OperatorDiv();
                break;
        }
        return operator;
    }
}
