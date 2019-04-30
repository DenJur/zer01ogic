package app.interfaces;

import app.enums.DrawStyle;

public interface StatefulNode {
    void updateStyle();
    void setNodeStyle(DrawStyle newStyle);
}
