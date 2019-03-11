package app.componentFactories.logicGates;

import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.dragdrop.logicGates.AndGateDraggable;

public class AndFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new AndGateDraggable();
    }
}
