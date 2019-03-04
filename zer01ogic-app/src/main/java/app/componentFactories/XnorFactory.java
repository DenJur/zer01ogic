package app.componentFactories;


import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.models.logicGates.XnorGateDraggable;

public class XnorFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new XnorGateDraggable();
    }
}
