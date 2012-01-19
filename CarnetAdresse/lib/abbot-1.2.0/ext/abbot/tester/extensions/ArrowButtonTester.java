package abbot.tester.extensions;

import java.awt.*;
import java.awt.event.InputEvent;
import example.ArrowButton;

/** Provide user actions and identifiers for the {@link ArrowButton}
    component. */ 

public class ArrowButtonTester extends abbot.tester.ComponentTester {

    /** Set up the editor menu documentation properties. */
    static {
        System.setProperty("actionRoll.menu", "Roll");
        System.setProperty("actionRoll.desc", "Hold down the arrow button");
        System.setProperty("ArrowButtonTester.actionRoll.args",
                           "actionRoll(Component comp, int ms)");
    }

    // NOTE: while deprecated, there is not currently in place a replacement
    // for noting one or more unique attributes of custom components, so for
    // now, this will do, but you are restricted to a single tag.
    public String deriveTag(Component comp) {
        return ((ArrowButton)comp).getDirection();
    }

    /** Press and hold the button for the given length of time (in ms). */
    public void actionRoll(Component comp, int ms) {
        Dimension size = comp.getSize();
        mousePress(comp, (size.width + 1) / 2, (size.height + 1) / 2,
                   InputEvent.BUTTON1_MASK);
        delay(ms);
        mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
