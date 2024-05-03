package com.annyarusova.task3;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

public class Person implements Thing {
    @Getter
    private final String name;

    @Getter @Setter
    private Emotion emotion = Emotion.Calm;

    @Getter
    private HashSet<Thing> seeingThings = new HashSet<>();

    @Getter
    private HashSet<BodyPart> bodyParts = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    public void EnterArea(Area area) {
        seeingThings.clear();
        for (Thing thing : area.getThings()) {
            see(thing);
        }

        area.getThings().add(this);
    }

    public void see(Thing thing) {
        seeingThings.add(thing);
    }
}
