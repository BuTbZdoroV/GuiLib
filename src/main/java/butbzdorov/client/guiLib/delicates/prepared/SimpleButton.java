package butbzdorov.client.guiLib.delicates.prepared;

import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.utils.GuiUtils;
import net.minecraft.util.ResourceLocation;
import org.newdawn.slick.Color;

import javax.vecmath.Vector2d;

@Delicate
public class SimpleButton extends Button {

    private int indexText = 0;
    private int indexImage = 0;

    public SimpleButton(String text, ResourceLocation image, Vector2d position, float width, float height) {
        super(position, width, height);

        addCenteredText(text, String.valueOf(indexText));
        indexText++;
        addImage(image, String.valueOf(indexImage));
        indexImage++;

        onHoverHandler = button -> {
            GuiUtils.drawRectS(position.x, position.y, this.width, this.height, Color.gray, 0.4);
        };
    }

    public SimpleButton(String text, ResourceLocation image, float posX, float posY, float width, float height) {
        super(posX, posY, width, height);

        addCenteredText(text, String.valueOf(indexText));
        indexText++;
        addImage(image, String.valueOf(indexImage));
        indexImage++;

        onHoverHandler = button -> {
            GuiUtils.drawRectS(position.x, position.y, this.width, this.height, Color.gray, 0.4);
        };
    }
}
