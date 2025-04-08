package butbzdorov.client.guiLib.delicates.prepared;

import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.window.IWindow;
import org.newdawn.slick.Color;

import javax.vecmath.Vector2d;


public class ImageColor extends Image {

    Color color = Color.white;
    double alpha = 1;

    public ImageColor(Vector2d position, float width, float height, Color color) {
        super(position, width, height);
        this.color = color;
    }

    public ImageColor(Vector2d position, float width, float height, Color color, double alpha) {
        super(position, width, height);
        this.color = color;
        this.alpha = alpha;
    }

    public ImageColor(IWindow window, Vector2d position, float width, float height,  Color color) {
        super(window, position, width, height);
        this.color = color;
    }

    public ImageColor(IWindow window, Vector2d position, float width, float height, Color color, double alpha) {
        super(window, position, width, height);
        this.color = color;
        this.alpha = alpha;
    }

    @Override
    public void onRender() {
        if (!IsVisible) return;
        GuiUtils.drawRectS(position.x, position.y, width, height, color, alpha);
    }
}
