package com.annyarusova.task3;

public record Jaw(Person person, BodyOrientation bodyOrientation, Head head) implements BodyPart {
    public Action droop() {
        return new Action("Jaw droop");
    }
}
