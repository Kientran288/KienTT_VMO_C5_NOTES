package com.note.demo.models;

/**
 * Status enum
 * Arthur: kientt
 */
public enum EType {
    SIMPLE(0L),
    IMAGE(1L),
    CHECK_BOX(2L);

    private Long value;

    EType(Long i) {
        this.value = i;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public static EType getEnum(String value) {
        for (EType v : values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }


}
