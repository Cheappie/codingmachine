package com.rpn;

sealed interface UnaryExpression {
    double evaluate(double operand);

    record AbsExpression() implements UnaryExpression {
        @Override
        public double evaluate(double operand) {
            return Math.abs(operand);
        }
    }

    record NegationExpression() implements UnaryExpression {
        @Override
        public double evaluate(double operand) {
            return -operand;
        }
    }
}
