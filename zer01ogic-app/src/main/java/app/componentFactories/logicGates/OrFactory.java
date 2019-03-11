package app.componentFactories.logicGates;

import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.dragdrop.logicGates.OrGateDraggable;

public class OrFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new OrGateDraggable();
    }
}
