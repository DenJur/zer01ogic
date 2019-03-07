package app.componentFactories;

import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.models.logicGates.NandGateDraggable;

public class NandFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new NandGateDraggable();
    }
}
