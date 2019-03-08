package app.componentFactories.logicGates;


import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.dragdrop.logicGates.NotGateDraggable;

public class NotFactory implements IDraggableFactory{
    @Override
    public DraggableNode constructDraggable() {
        return new NotGateDraggable();
    }
}
