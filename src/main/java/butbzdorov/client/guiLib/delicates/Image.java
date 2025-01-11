package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.gui.res.ResourceLoader;
import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.utils.GuiUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.util.ResourceLocation;


@EqualsAndHashCode(callSuper = true)
@Data
public class Image extends IDelicate {

    private ResourceLocation image;

    private String ImageName = "NULL";

    private float posX = 0.0f;
    private float posY = 0.0f;
    private float endX = 0.0f;
    private float endY = 0.0f;

    private float alpha = 1.0f;

    public boolean IsVisible = true;


    public Image(ResourceLocation image, float posX, float posY, float endX, float endY) {
        this.image = image;
        this.ImageName = getLastWord();
        this.posX = posX;
        this.posY = posY;
        this.endX = endX;
        this.endY = endY;
        this.alpha = 1.0f;
    }

    public Image(float posX, float posY, float endX, float endY) {
        this.image = null; //Todo Заменить на EMPTY
        this.posX = posX;
        this.posY = posY;
        this.endX = endX;
        this.endY = endY;
    }

    public Image setPosX(float x) {
        this.posX = x;
        return this;
    }

    public Image setPosY(float y) {
        this.posY = y;
        return this;
    }

    public Image setEndX(float endX) {
        this.endX = endX;
        return this;
    }
    public Image setEndY(float endY) {
        this.endY = endY;
        return this;
    }

    public Image setPlacePosX(float x) {
        this.posX += x;
        return this;
    }

    public Image setPlacePosY(float y) {
        this.posY += y;
        return this;
    }

    public Image placeEndX(float x) {
        this.endX += x;
        return this;
    }

    public Image placeEndY(float y) {
        this.endY += y;
        return this;
    }

    public String getLastWord() {
        int lastSlash = Math.max(image.getResourcePath().lastIndexOf('/'), image.getResourcePath().lastIndexOf('\\'));
        int lastDot = image.getResourcePath().lastIndexOf('.');
        return image.getResourcePath().substring(lastSlash + 1, lastDot > lastSlash ? lastDot : image.getResourcePath().length());
    }


    @Override
    public void onRender() {
        if (!IsVisible) return;
        GuiUtils.drawImage(this.image, posX, posY, endX, endY, alpha);
    }

    @Override
    public String getId() {
        return ImageName;
    }


}
