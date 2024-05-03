package com.annyarusova.task3;

public record Leg(Person person, BodyOrientation bodyOrientation) implements BodyPart {
    public Action layOnThing(Thing thing) {
        return new Action("Leg lays on " + thing.toString());
    }
}
