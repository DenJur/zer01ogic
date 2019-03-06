package app.componentFactories.logicGates;


import app.dragdrop.DraggableNode;
import app.interfaces.IDraggableFactory;
import app.dragdrop.logicGates.NorGateDraggable;

public class NorFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new NorGateDraggable();
    }
}
