package app.componentFactories.memory;

import app.dragdrop.DraggableNode;
import app.dragdrop.memory.TFlipFlopDraggable;
import app.interfaces.IDraggableFactory;

public class TFlipFlopFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new TFlipFlopDraggable();
    }
}
