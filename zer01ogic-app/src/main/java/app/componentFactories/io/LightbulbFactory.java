package app.componentFactories.io;

import app.dragdrop.DraggableNode;
import app.dragdrop.io.LightbulbDraggable;
import app.interfaces.IDraggableFactory;

public class LightbulbFactory implements IDraggableFactory {
    @Override
    public DraggableNode constructDraggable() {
        return new LightbulbDraggable();
    }
}
