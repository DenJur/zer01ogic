package app.componentFactories;


import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.models.logicGates.NotGateDraggable;

public class NotFactory implements IDraggableFactory{
    @Override
    public DraggableNode constructDraggable() {
        return new NotGateDraggable();
    }
}
