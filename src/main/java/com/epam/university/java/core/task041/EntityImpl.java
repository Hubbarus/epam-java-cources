package com.epam.university.java.core.task041;

import java.util.Objects;

public class EntityImpl implements Entity {
    private String value;
    private int id;

    public void setValue(String value) {
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityImpl)) {
            return false;
        }
        EntityImpl entity = (EntityImpl) o;
        return Objects.equals(getValue(), entity.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getId());
    }
}
