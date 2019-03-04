package app.componentFactories;


import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.models.logicGates.NorGateDraggable;

public class NorFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new NorGateDraggable();
    }
}
