package task3;

import com.annyarusova.task3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DomainTests {
    @Test
    void person_can_see_thing() {
        Person person = new Person("Artur");
        Thing thing = new ControlPanel();
        person.see(thing);

        assertTrue(person.getSeeingThings().contains(thing));
    }

    @Test
    void person_can_see_things_in_area() {
        Person person = new Person("Artur");
        Thing thing = new ControlPanel();
        Area area = new Area();
        area.getThings().add(thing);

        person.EnterArea(area);

        assertTrue(person.getSeeingThings().contains(thing));
    }

    @Test
    void leg_can_laid_on_thing() {
        Person person = new Person("Artur");
        Leg leg = new Leg(person, BodyOrientation.Left);
        Thing thing = new ControlPanel();

        Action action = leg.layOnThing(thing);

        Assertions.assertEquals(action.Description(), "Leg lays on ControlPanel");
    }

    @Test
    void jaw_can_droop() {
        Person person = new Person("Artur");
        Head head = new Head(person, BodyOrientation.Single);
        Jaw jaw = new Jaw(person, BodyOrientation.Single, head);

        Action action = jaw.droop();

        Assertions.assertEquals(action.Description(), "Jaw droop");
    }

    @Test
    void hand_can_pick_teeth() {
        Person person = new Person("Artur");
        Head head = new Head(person, BodyOrientation.Single);
        Hand hand = new Hand(person, BodyOrientation.Left);
        Teeth teeth = new Teeth(person, BodyOrientation.Single, head);

        Action action = hand.pickTeeth(teeth);

        Assertions.assertEquals(action.Description(), "Hand picking teeth");
    }

    @Test
    void head_can_perform_action() {
        Person person = new Person("Artur");
        Head head = new Head(person, BodyOrientation.Single);
        Hand hand = new Hand(person, BodyOrientation.Left);
        Teeth teeth = new Teeth(person, BodyOrientation.Single, head);

        Action action = hand.pickTeeth(teeth);
        head.performAction(action);

        Assertions.assertEquals(action, head.getCurrentAction());
    }

    @Test
    void head_can_smile() {
        Person person = new Person("Artur");
        Head head = new Head(person, BodyOrientation.Single);

        head.smile(SmileCharacteristic.Wide);

        Assertions.assertEquals(head.getCurrentAction().Description(), "Smiling Wide");
    }
}
