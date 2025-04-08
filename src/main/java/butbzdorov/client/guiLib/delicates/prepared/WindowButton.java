package butbzdorov.client.guiLib.delicates.prepared;

import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.window.IWindow;
import net.minecraft.util.ResourceLocation;
import org.newdawn.slick.Color;

import javax.vecmath.Vector2d;

@Delicate

public class WindowButton extends Button {

    private int indexText = 0;
    private int indexImage = 0;

    public WindowButton(IWindow window, String text, ResourceLocation image, Vector2d position, float width, float height) {
        super(window, position, width, height);

      /*  float scaleX = window.getResolution().getEndX() / 1920;
        float scaleY = window.getResolution().getEndY() / 1080;

        // Применяем масштабирование
        this.posX = window.getResolution().getPosX() + (posX * scaleX);
        this.posY = window.getResolution().getPosY() + (posY * scaleY);
        this.endX = width * scaleX;
        this.endY = height * scaleY;
*/
        onHoverHandler = button -> {
            GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getWidth(), button.getHeight(), Color.gray, 0.4);
        };
    }

    @Override
    public Button addCenteredText(String text, String identifier) {
        indexText++;
        return super.addCenteredText(text, identifier);
    }

}
