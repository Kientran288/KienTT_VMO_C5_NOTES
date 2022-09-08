package com.note.demo.models;

/**
 * Status enum
 * Arthur: kientt
 */
public enum EStatus {
    EDITING(0L),
    SAVED(1L);

    EStatus(Long i) {
        this.value = i;
    }

    private Long value;

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public static EStatus getEnum(Long value) {
        for (EStatus v : values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
