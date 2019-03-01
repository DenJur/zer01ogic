package app.componentFactories;

import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.models.logicGates.AndGateDraggable;

public class AndFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new AndGateDraggable();
    }
}
