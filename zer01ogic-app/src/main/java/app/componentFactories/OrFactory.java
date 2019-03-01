package app.componentFactories;

import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.models.logicGates.AndGateDraggable;
import app.models.logicGates.OrGateDraggable;

public class OrFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new OrGateDraggable();
    }
}
