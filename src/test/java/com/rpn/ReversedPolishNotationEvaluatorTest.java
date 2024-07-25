package com.rpn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReversedPolishNotationEvaluatorTest {

    @Test
    void shouldEvaluateAddExpression() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = "3 5 +";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(8, result);
    }

    @Test
    void shouldEvaluateSubtractExpression() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = "3 5 -";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(-2, result);
    }

    @Test
    void shouldEvaluateMultiplyExpression() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = "3 5 *";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(15, result);
    }

    @Test
    void shouldEvaluateDivideExpression() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = "3 6 /";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(0.5, result);
    }

    @Test
    void shouldEvaluateDigitExpression() {
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = "6";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(6, result);
    }

    @Test
    void shouldEvaluateNumberExpression() {
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = "64";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(64, result);
    }

    @Test
    void shouldEvaluateNegationExpression() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = " 5 _";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(-5, result);
    }

    @Test
    void shouldEvaluateAbsExpression() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = " 5 _ a";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(5, result);
    }

    @Test
    void shouldEvaluateMaximalValueExpressionOnSingleNumber() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = "10 m";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(10, result);
    }

    @Test
    void shouldEvaluateMaximalValueExpressionOnMultipleNumbers() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = " 5 7 2 4 m";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(7, result);
    }

    @Test
    void shouldEvaluateRPNExpression() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = " 2 3 + 5 *";

        //when
        var result = sysUnderTest.evaluate(expression);

        //then
        assertEquals(25, result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void shouldThrowRPNExceptionOnBlankExpression(String expression) {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();

        //then
        assertThrows(RPNException.class, () -> sysUnderTest.evaluate(expression));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+", "-", "*", "/", "a", "m", "_",
            "0 +", "0 -", "0 *", "0 /"
    })
    void shouldThrowOnInsufficientArguments(String expression) {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();

        //then
        assertThrows(InsufficientArgumentsException.class, () -> sysUnderTest.evaluate(expression));
    }

    @Test
    void shouldThrowOnUnsupportedOperatorInExpression() {
        //given
        var sysUnderTest = new ReversedPolishNotationEvaluator();
        var expression = "1 2 + #";

        //then
        assertThrows(UnsupportedOperatorException.class, () -> sysUnderTest.evaluate(expression));
    }
}
