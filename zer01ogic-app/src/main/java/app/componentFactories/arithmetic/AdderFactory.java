package app.componentFactories.arithmetic;

import app.dragdrop.DraggableNode;
import app.dragdrop.arithmetic.AdderDraggable;
import app.interfaces.IDraggableFactory;

public class AdderFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new AdderDraggable();
    }
}
