package com.example.fishingharboralert.util;

import com.example.fishingharboralert.model.SafetyLevel;

public final class SafetyEvaluator {
    private SafetyEvaluator() {
    }

    public static SafetyLevel evaluate(double windSpeed, double waveHeight) {
        if (windSpeed > 12.0 || waveHeight > 2.0) {
            return SafetyLevel.DANGER;
        }
        if (windSpeed >= 8.0 || waveHeight >= 1.0) {
            return SafetyLevel.CAUTION;
        }
        return SafetyLevel.SAFE;
    }
}
