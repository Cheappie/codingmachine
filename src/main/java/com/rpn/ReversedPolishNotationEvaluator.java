package com.rpn;

import com.rpn.BinaryExpression.AddExpression;
import com.rpn.BinaryExpression.DivideExpression;
import com.rpn.BinaryExpression.MultiplyExpression;
import com.rpn.BinaryExpression.SubtractExpression;
import com.rpn.ExpressionEvaluator.BinaryExpressionEvaluator;
import com.rpn.ExpressionEvaluator.NAryExpressionEvaluator;
import com.rpn.ExpressionEvaluator.UnaryExpressionEvaluator;
import com.rpn.NaryExpression.MaxValueExpression;
import com.rpn.UnaryExpression.AbsExpression;
import com.rpn.UnaryExpression.NegationExpression;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class ReversedPolishNotationEvaluator {

    private final Deque<Double> stack = new LinkedList<>();

    public double evaluate(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new RPNException("The expression is invalid, it is either null or blank");
        }

        var tokens = Arrays.stream(expression.split("\\s+"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        for (var token : tokens) {
            if (isInteger(token)) {
                stack.push(parseNumber(token));
            } else {
                evaluateExpression(token);
            }
        }

        return stack.pop();
    }

    private boolean isInteger(String token) {
        return token.chars().allMatch(Character::isDigit);
    }

    private Double parseNumber(String token) {
        return Double.parseDouble(token);
    }

    private void evaluateExpression(String token) {
        var expressionEvaluator = selectExpressionEvaluator(token);
        var result = expressionEvaluator.evaluate(stack);
        stack.push(result);
    }

    private ExpressionEvaluator selectExpressionEvaluator(String token) {
        return switch (token) {
            case "a" -> new UnaryExpressionEvaluator(new AbsExpression());
            case "_" -> new UnaryExpressionEvaluator(new NegationExpression());
            case "+" -> new BinaryExpressionEvaluator(new AddExpression());
            case "-" -> new BinaryExpressionEvaluator(new SubtractExpression());
            case "*" -> new BinaryExpressionEvaluator(new MultiplyExpression());
            case "/" -> new BinaryExpressionEvaluator(new DivideExpression());
            case "m" -> new NAryExpressionEvaluator(new MaxValueExpression());
            default -> throw new UnsupportedOperatorException("Unsupported operator found in expression: " + token);
        };
    }
}
