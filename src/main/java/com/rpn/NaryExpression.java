package com.rpn;

import java.util.Collection;

sealed interface NaryExpression {
    double evaluate(Collection<Double> values);

    record MaxValueExpression() implements NaryExpression {
        @Override
        public double evaluate(Collection<Double> values) {
            return values.stream()
                    .mapToDouble(Double::doubleValue)
                    .max()
                    .orElseThrow();
        }
    }
}
