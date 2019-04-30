package app.componentFactories.memory;

import app.dragdrop.DraggableNode;
import app.dragdrop.memory.DFlipFlopDraggable;
import app.interfaces.IDraggableFactory;

public class DFlipFlopFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new DFlipFlopDraggable();
    }
}
