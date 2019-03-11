package app.componentFactories.io;

import app.dragdrop.DraggableNode;
import app.dragdrop.io.SwitchDraggable;
import app.interfaces.IDraggableFactory;

public class SwitchFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new SwitchDraggable();
    }
}
