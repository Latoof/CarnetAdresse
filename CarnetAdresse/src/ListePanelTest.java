
import java.awt.Component;

import javax.swing.*;
import junit.framework.*;
import junit.extensions.abbot.*;

import abbot.finder.matchers.ClassMatcher;
import abbot.tester.ComponentTester;
import abbot.tester.JListLocation;
import abbot.tester.JListTester;

public class ListePanelTest extends ComponentTestFixture {
	
	/* Pour commencer, on peut essayer de tester si le label d'un element de la liste ...
	 * ... correspond bien au nom et au prenom de la Fiche concernee.
	 * Base toi sur cet exemple (incomplet) si tu veux essayer.
	 */
    public void testViewChangedOnSelectionChange() throws Throwable {
        String[] contents = { "one", "two", "three" };
        //final ListePanel labeledList = new ListePanel(contents);
        Component list = getFinder().find(new ClassMatcher(ListePanel.class)); // Trouve la classe concercee
        JListTester tester = new JListTester();
        tester.actionSelectRow(list, new JListLocation(1)); 
        // ...
    }
	
    public ListePanelTest(String name) { 
    	super(name); 
    }

    public static void main(String[] args) {
        TestHelper.runTests(args, ListePanelTest.class);
    }

}
