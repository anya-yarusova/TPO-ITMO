package com.annyarusova.task3;

public record Hand(Person person, BodyOrientation bodyOrientation) implements BodyPart {
    public Action pickTeeth(Teeth teeth) {
        return new Action("Hand picking teeth");
    }
}
