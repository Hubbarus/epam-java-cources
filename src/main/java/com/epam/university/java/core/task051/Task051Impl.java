package com.epam.university.java.core.task051;

public class Task051Impl implements Task051 {

    @Override
    public String replace(String source) {
        verify(source);

        source = source.replaceAll("[Tt]he (?![eyuioa])", "a ");
        source = source.replaceAll("[Tt]he (?![^eyuioa])", "an ");

        return source;
    }

    private void verify(String source) {
        if (source == null || source.equals(" ")
                || source.toUpperCase().equals(source)
                || source.equalsIgnoreCase("the")) {
            throw new IllegalArgumentException();
        }
    }
}
