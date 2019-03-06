package app.componentFactories.logicGates;

import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.dragdrop.logicGates.NandGateDraggable;

public class NandFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new NandGateDraggable();
    }
}
