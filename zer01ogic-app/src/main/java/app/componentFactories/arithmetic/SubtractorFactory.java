package app.componentFactories.arithmetic;

import app.dragdrop.DraggableNode;
import app.dragdrop.arithmetic.SubtractorDraggable;
import app.interfaces.IDraggableFactory;

public class SubtractorFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new SubtractorDraggable();
    }
}
