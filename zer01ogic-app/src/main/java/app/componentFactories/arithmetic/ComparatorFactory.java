package app.componentFactories.arithmetic;

import app.dragdrop.DraggableNode;
import app.dragdrop.arithmetic.ComparatorDraggable;
import app.interfaces.IDraggableFactory;

public class ComparatorFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new ComparatorDraggable();
    }
}
