package app.componentFactories.io;

import app.dragdrop.DraggableNode;
import app.dragdrop.io.ClockDraggable;
import app.interfaces.IDraggableFactory;

public class ClockFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new ClockDraggable();
    }
}
