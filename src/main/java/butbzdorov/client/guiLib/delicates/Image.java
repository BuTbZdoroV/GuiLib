package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.gui.res.ResourceLoader;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.SG;
import lombok.Data;
import net.minecraft.util.ResourceLocation;

@Delicate
@Data
public class Image implements IDelicate {

    private ResourceLoader image;

    private float posX = 0.0f;
    private float posY = 0.0f;
    private float endX = 0.0f;
    private float endY = 0.0f;

    private float alpha = 1.0f;


    public Image(ResourceLoader image, float posX, float posY, float endX, float endY) {
        this.image = image;
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


    @Override
    public void onStartRender(float milliseconds) {

    }

    @Override
    public void onRender() {
        GuiUtils.drawImage(this.image, SG.get(posX), SG.get(posY), SG.get(endX), SG.get(endY), alpha);
    }
}
