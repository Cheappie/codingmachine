package com.rpn;

import java.util.Deque;

sealed interface ExpressionEvaluator {
    double evaluate(Deque<Double> stack);

    record UnaryExpressionEvaluator(UnaryExpression expression) implements ExpressionEvaluator {
        @Override
        public double evaluate(Deque<Double> stack) {
            if (stack.isEmpty()) {
                throw new InsufficientArgumentsException("Couldn't evaluate unary expression due to stack being empty");
            }

            return expression.evaluate(stack.pop());
        }
    }

    record BinaryExpressionEvaluator(BinaryExpression expression) implements ExpressionEvaluator {
        @Override
        public double evaluate(Deque<Double> stack) {
            if (stack.size() < 2) {
                throw new InsufficientArgumentsException("Couldn't evaluate binary expression due to insufficient arguments");
            }

            var rightOperand = stack.pop();
            var leftOperand = stack.pop();
            return expression.evaluate(leftOperand, rightOperand);
        }
    }

    record NAryExpressionEvaluator(NaryExpression expression) implements ExpressionEvaluator {
        @Override
        public double evaluate(Deque<Double> stack) {
            if (stack.isEmpty()) {
                throw new InsufficientArgumentsException("Couldn't evaluate nary expression due to stack being empty");
            }

            var result = expression.evaluate(stack);
            while (!stack.isEmpty()) {
                stack.pop();
            }

            return result;
        }
    }
}
