package app.componentFactories;


import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.models.logicGates.XorGateDraggable;

public class XorFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new XorGateDraggable();
    }
}
