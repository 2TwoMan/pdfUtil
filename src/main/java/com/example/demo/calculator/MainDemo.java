package com.example.demo.calculator;


import com.example.demo.calculator.operator.factory.OperatorFactory;
import com.example.demo.calculator.operator.model.Operator;

import java.util.Scanner;

/**
 * @Description: 客户端
 * @Author: CL
 * @Date: 2020/1/15 17:22
 */
public class MainDemo {
    public static void main(String[] args) throws Exception {
       Operator operator;
       operator = OperatorFactory.createOperate("/");
       operator.setFirstNumber(1d);
       operator.setSecondNumber(2d);
       double result = operator.getResult();
       System.out.println(result);
    }
}
