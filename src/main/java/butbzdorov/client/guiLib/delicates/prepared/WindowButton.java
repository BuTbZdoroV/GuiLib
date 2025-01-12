package butbzdorov.client.guiLib.delicates.prepared;

import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.SG;
import butbzdorov.client.guiLib.window.IWindow;
import net.minecraft.util.ResourceLocation;
import org.newdawn.slick.Color;

@Delicate
public class WindowButton extends Button {

    private int indexText = 0;
    private int indexImage = 0;

    public WindowButton(IWindow window, String text, ResourceLocation image, float posX, float posY, float width, float height) {
        super(posX, posY, width, height);
        this.window = window;
        addText(text, String.valueOf(indexText));
        indexText++;
        addImage(image, String.valueOf(indexImage));
        indexImage++;

        onHoverHandler = button -> {
            GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getEndX(), button.getEndY(), Color.gray, 0.4);
        };
    }
}
