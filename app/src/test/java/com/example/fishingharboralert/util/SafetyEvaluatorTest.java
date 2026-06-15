package com.example.fishingharboralert.util;

import com.example.fishingharboralert.model.SafetyLevel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SafetyEvaluatorTest {
    @Test
    public void returnsSafeWhenWindAndWaveAreLow() {
        assertEquals(SafetyLevel.SAFE, SafetyEvaluator.evaluate(5.5, 0.6));
    }

    @Test
    public void returnsCautionWhenConditionsAreModerate() {
        assertEquals(SafetyLevel.CAUTION, SafetyEvaluator.evaluate(9.5, 1.4));
    }

    @Test
    public void returnsDangerWhenWindOrWaveExceedsLimit() {
        assertEquals(SafetyLevel.DANGER, SafetyEvaluator.evaluate(13.0, 0.7));
        assertEquals(SafetyLevel.DANGER, SafetyEvaluator.evaluate(6.0, 2.4));
    }
}
