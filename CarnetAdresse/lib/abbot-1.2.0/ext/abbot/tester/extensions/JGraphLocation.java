package abbot.tester.extensions;
import java.awt.*;
import java.awt.geom.*;

import org.jgraph.JGraph;

import abbot.Log;
import abbot.tester.*;
import abbot.i18n.Strings;
import abbot.util.ExtendedComparator;

/** Provides encapsulation of the location of vertices and edges in a
    {@link JGraph} (a coordinate or item index). 
    This class provides an example of a {@link ComponentLocation} extension.
    Note that all bounds information must be converted to screen coordinates
    with {@link JGraph#toScreen(Point2D)} or
    {@link JGraph#toScreen(Rectangle2D)} before use in actions.
 */
// TODO: encode cell "values" (view.toString?)
public class JGraphLocation extends ComponentLocation {
    private int index = -1;

    public JGraphLocation() { }

    public JGraphLocation(int index) {
        if (index < 0) {
            String msg = Strings.get("tester.JGraph.invalid_index",
                                     new Object[] { new Integer(index) });
            throw new LocationUnavailableException(msg);
        }
        this.index = index;
    }

    public JGraphLocation(Point where) {
        super(where);
    }

    protected String badFormat(String encoded) {
        return Strings.get("location.graph.bad_format",
                           new Object[] { encoded });
    }

    private void checkIndex(JGraph graph, int index) {
        if (index < 0 || index >= graph.getRoots().length) {
            String msg = Strings.get("tester.JGraph.invalid_index", 
                                     new Object[] { new Integer(index) });
            throw new LocationUnavailableException(msg);
        }
    }

    /** Return the bounds of the cell in screen coordinates. */
    protected Rectangle getCellBounds(JGraph graph, Object o) {
        Rectangle2D rect = graph.getCellBounds(o);
        if (rect != null) {
            return toScreen(graph, (Rectangle2D)rect.clone());
        }
        String msg = Strings.get("tester.JGraph.cell_not_found",
                                 new Object[] { o });
        throw new LocationUnavailableException(msg);
    }

    /** Convert the given object index into a screen coordinate. */
    protected Point indexToPoint(JGraph graph, int index) {
        checkIndex(graph, index);
        Rectangle rect = getCellBounds(graph, graph.getRoots()[index]);

        // FIXME this is the center of the bounding box; should use the center
        // of the CellView/Renderer instead
        return new Point(rect.x + rect.width/2, 
                         rect.y + rect.height/2);
    }

    /** Return a concrete point for the abstract location. */
    public Point getPoint(Component c) {
        if (index != -1)
            return indexToPoint((JGraph)c, index);
        return super.getPoint(c);
    }

    /** Return the bounds of this location.  If the location refers to a cell,
        returns the cell's bounding box.
    */
    public Rectangle getBounds(Component c) {
        JGraph graph = (JGraph)c;
        if (index != -1) {
            checkIndex(graph, index);
            return getCellBounds(graph, graph.getRoots()[index]);
        }
        return super.getBounds(c);
    }

    public boolean equals(Object o) {
        if (o instanceof JGraphLocation) {
            JGraphLocation loc = (JGraphLocation)o;
            if (index != -1)
                return index == loc.index;
        }
        return super.equals(o);
    }

    public String toString() {
        if (index != -1)
            return encodeIndex(index);
        return super.toString();
    }

    public ComponentLocation parse(String encoded) {
        encoded = encoded.trim();
        if (isIndex(encoded)) {
            index = parseIndex(encoded);
            return this;
        }
        return super.parse(encoded);
    }
    
    protected Point toScreen(JGraph graph, int x, int y) {
        Point2D p = graph.toScreen(new Point(x, y));
        return new Point((int)p.getX(), (int)p.getY());
    }
    
    protected Rectangle toScreen(JGraph graph, Rectangle2D rect) {
        Rectangle2D r = graph.toScreen(rect);
        return new Rectangle((int)r.getX(), (int)r.getY(), 
                             (int)r.getWidth(), (int)r.getHeight());
    }
}
