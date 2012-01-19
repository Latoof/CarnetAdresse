package abbot.tester.extensions;
import java.awt.*;

import abbot.*;
import abbot.tester.*;
import org.jgraph.JGraph;
import org.jgraph.graph.*;

/** Provide user actions on an instance of {@link JGraph}.  
    The substructure for {@link JGraph} is a cell (vertex or edge).
    This class provides an example of a {@link ComponentTester} extension.

    @see JGraphLocation
 */

// TODO: marquee selection
// TODO: multi-selection (can this be generalized?)
//    maybe multi-select(begin, end)
//          multi-select(array) (discontiguous)

public class JGraphTester extends JComponentTester {

    /** Select a single cell. */
    public void actionSelectCell(Component c, ComponentLocation loc) {
        actionClick(c, loc);
    }

    public ComponentLocation parseLocation(String encoded) {
        return new JGraphLocation().parse(encoded);
    }

    /** Returns a {@link JGraphLocation} corresponding to the given
        {@link Point} location.  If there is no object at that location,
        the raw {@link Point} is used.  If there is an object, and its
        {@link CellView}'s {@link Object#toString()} looks meaningful, that is
        used, otherwise the object's index is used.
    */
    public ComponentLocation getLocation(Component c, Point p) {
        //JGraph graph = (JGraph)c;
        // FIXME
        return new JGraphLocation(p);
    }
}
