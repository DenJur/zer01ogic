package app.componentFactories.logicGates;


import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.dragdrop.logicGates.XorGateDraggable;

public class XorFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new XorGateDraggable();
    }
}
