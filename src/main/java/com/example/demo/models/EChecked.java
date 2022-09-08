package com.example.demo.models;

/**
 * Checked enum
 * Arthur: kientt
 */
public enum EChecked {
    CHECKED(0L),
    UN_CHECKED(1L);
    private Long value;

    EChecked(Long i) {
        this.value = i;
    }

    public Long getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public static EChecked getEnum(String value) {
        for (EChecked v : values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }

}
