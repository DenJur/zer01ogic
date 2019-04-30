package app.componentFactories.memory;

import app.dragdrop.DraggableNode;
import app.dragdrop.memory.AdderDraggable;
import app.dragdrop.memory.DFlipFlopDraggable;
import app.interfaces.IDraggableFactory;

public class AdderFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new AdderDraggable();
    }
}
