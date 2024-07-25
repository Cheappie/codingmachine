package com.rpn;

sealed interface BinaryExpression {
    double evaluate(double leftOperand, double rightOperand);

    record AddExpression() implements BinaryExpression {
        @Override
        public double evaluate(double leftOperand, double rightOperand) {
            return leftOperand + rightOperand;
        }
    }

    record SubtractExpression() implements BinaryExpression {
        @Override
        public double evaluate(double leftOperand, double rightOperand) {
            return leftOperand - rightOperand;
        }
    }

    record MultiplyExpression() implements BinaryExpression {
        @Override
        public double evaluate(double leftOperand, double rightOperand) {
            return leftOperand * rightOperand;
        }
    }

    record DivideExpression() implements BinaryExpression {
        @Override
        public double evaluate(double leftOperand, double rightOperand) {
            return leftOperand / rightOperand;
        }
    }
}
