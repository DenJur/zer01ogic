package app.componentFactories.memory;

import app.dragdrop.DraggableNode;
import app.dragdrop.memory.JKFlipFlopDraggable;
import app.interfaces.IDraggableFactory;

public class JKFlipFlopFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new JKFlipFlopDraggable();
    }
}
