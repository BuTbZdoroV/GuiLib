package butbzdorov.client.guiLib.delicates.prepared;

import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.utils.GuiUtils;
import net.minecraft.util.ResourceLocation;
import org.newdawn.slick.Color;

import java.util.function.Consumer;

@Delicate
public class SimpleButton extends Button {

    private int indexText = 0;
    private int indexImage = 0;

    public SimpleButton(String text, ResourceLocation image, float posX, float posY, float width, float height) {
        super(posX, posY, width, height);

        addText(text, String.valueOf(indexText));
        indexText++;
        addImage(image, String.valueOf(indexImage));
        indexImage++;

        onHoverHandler = button -> {
            GuiUtils.drawRectS(posX, posY, endX, endY, Color.gray, 0.4);
        };
    }
}
