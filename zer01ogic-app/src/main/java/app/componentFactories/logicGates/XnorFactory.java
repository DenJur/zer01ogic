package app.componentFactories.logicGates;


import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.dragdrop.logicGates.XnorGateDraggable;

public class XnorFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new XnorGateDraggable();
    }
}
