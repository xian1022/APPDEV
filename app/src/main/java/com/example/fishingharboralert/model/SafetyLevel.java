package com.example.fishingharboralert.model;

public enum SafetyLevel {
    SAFE("安全", "風浪穩定，適合一般岸邊活動"),
    CAUTION("注意", "風浪偏高，建議留意天候變化"),
    DANGER("危險", "風浪達警戒，避免靠近海邊或出航");

    private final String label;
    private final String description;

    SafetyLevel(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
}
