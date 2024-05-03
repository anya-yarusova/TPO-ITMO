package com.annyarusova.task3;

import lombok.Getter;

@Getter
public class Head implements BodyPart {
    private final Person person;
    private final BodyOrientation bodyOrientation;

    private Action currentAction;

    public Head(Person person, BodyOrientation bodyOrientation) {
        this.person = person;
        this.bodyOrientation = bodyOrientation;
    }

    public void performAction(Action action) {
        currentAction = action;
    }

    public void smile(SmileCharacteristic characteristic) {
        currentAction = new Action("Smiling " + characteristic);
    }

    @Override
    public Person person() {
        return person;
    }

    @Override
    public BodyOrientation bodyOrientation() {
        return bodyOrientation;
    }
}
