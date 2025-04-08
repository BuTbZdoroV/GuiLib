package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.window.IWindow;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.util.ResourceLocation;

import javax.vecmath.Vector2d;


@EqualsAndHashCode(callSuper = true)
@Data
public class Image extends IDelicate<Image> {

    private ResourceLocation image;

    private String ImageName = "NULL";

    private float alpha = 1.0f;

    public boolean IsVisible = true;


    public Image(ResourceLocation image, Vector2d position, float width, float height) {
        super(position, width, height);
        this.image = image;
        this.ImageName = getLastWord();
        this.alpha = 1.0f;
    }

    public Image(Vector2d position, float width, float height) {
        super(position, width, height);
        this.image = null; //Todo Заменить на EMPTY
    }

    public Image(IWindow window, ResourceLocation image, Vector2d position, float width, float height) {
        super(window , position, width, height);
        this.image = image;
        this.ImageName = getLastWord();
        this.alpha = 1.0f;
    }


    public Image(ResourceLocation image, float posX, float posY, float width, float height) {
        super(new Vector2d(posX, posY), width, height);
        this.image = image;
        this.ImageName = getLastWord();
        this.alpha = 1.0f;
    }

    public Image(float posX, float posY, float width, float height) {
        super(new Vector2d(posX, posY), width, height);
        this.image = null; //Todo Заменить на EMPTY
    }

    public Image(IWindow window, ResourceLocation image, float posX, float posY, float width, float height) {
        super(window , new Vector2d(posX, posY), width, height);
        this.image = image;
        this.ImageName = getLastWord();
        this.alpha = 1.0f;
    }

    public Image(IWindow window, float posX, float posY, float width, float height) {
        super(window, new Vector2d(posX, posY), width, height);
        this.image = null; //Todo Заменить на EMPTY
    }

    public Image(IWindow window, Vector2d position, float width, float height) {
        super(window, position, width, height);
        this.image = null; //Todo Заменить на EMPTY
    }

    public Image setWidth(float width) {
        this.width = width;
        return this;
    }
    public Image setHeight(float height) {
        this.height = height;
        return this;
    }

    public Image setPlacePosX(float x) {
        this.position.x += x;
        return this;
    }

    public Image setPlacePosY(float y) {
        this.position.y += y;
        return this;
    }

    public Image placeEndX(float x) {
        this.width += x;
        return this;
    }

    public Image placeEndY(float y) {
        this.height += y;
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
        GuiUtils.drawImage(this.image, position.x, position.y, width, height, alpha);
    }

    @Override
    public String getId() {
        return ImageName;
    }


}
