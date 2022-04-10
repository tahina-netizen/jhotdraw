package org.jhotdraw.draw.liner;

import java.util.Collection;
import java.util.Collections;

import org.jhotdraw.draw.connector.Connector;
import org.jhotdraw.draw.figure.ConnectionFigure;
import org.jhotdraw.draw.figure.LineConnectionFigure;
import org.jhotdraw.draw.handle.Handle;
import org.jhotdraw.geom.BezierPath;

public abstract class AbstractLiner implements Liner {
    
    @Override
    public Collection<Handle> createHandles(BezierPath path) {
        return Collections.emptyList();
    }
    
    @Override
    final public void lineout(ConnectionFigure figure) {
        BezierPath path = ((LineConnectionFigure) figure).getBezierPath();
        Connector start = figure.getStartConnector();
        Connector end = figure.getEndConnector();
        if (start == null || end == null || path == null) {
            return;
        }
        // Special treatment if the connection connects the same figure
        if (figure.getStartFigure() == figure.getEndFigure()) {
            lineoutWhenConnectingSameFigure(figure, path, start, end);
        } else {
            lineoutWhenConnectingDifferentFigures(figure, path, start, end);
        }
        path.invalidatePath();
    }

    protected abstract void lineoutWhenConnectingDifferentFigures(ConnectionFigure figure, BezierPath path,
            Connector start, Connector end);

    protected abstract void lineoutWhenConnectingSameFigure(ConnectionFigure figure, BezierPath path, Connector start,
            Connector end);
    
    @Override
    public Liner clone() {
        try {
            return (Liner) super.clone();
        } catch (CloneNotSupportedException ex) {
            InternalError error = new InternalError(ex.getMessage());
            error.initCause(ex);
            throw error;
        }
    }

}