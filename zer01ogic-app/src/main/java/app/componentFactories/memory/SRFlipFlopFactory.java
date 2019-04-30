package app.componentFactories.memory;

import app.dragdrop.DraggableNode;
import app.dragdrop.memory.SRFlipFlopDraggable;
import app.interfaces.IDraggableFactory;

public class SRFlipFlopFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new SRFlipFlopDraggable();
    }
}
