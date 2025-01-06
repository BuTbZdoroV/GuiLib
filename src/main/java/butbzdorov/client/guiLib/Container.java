package butbzdorov.client.guiLib;

import butbzdorov.client.guiLib.delicates.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class Container extends GuiScreen {

    protected final static Minecraft mc = Minecraft.getMinecraft();

    protected float width;
    protected float height;

    protected float mouseX;
    protected float mouseY;

    public final static List<Button> buttons = new ArrayList<>();

    public Container() {
    }

    @Override
    public void initGui() {
        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        width = scaledresolution.getScaledWidth();
        height = scaledresolution.getScaledHeight();
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

/*        mouseX = p_73863_1_ * width / mc.displayWidth;
        mouseY = p_73863_2_ * height / mc.displayHeight;

        // Масштабирование экрана
        GL11.glPushMatrix();
        GL11.glScalef(width / mc.displayWidth, height / mc.displayHeight, 1.0f);

        for (Button button : buttons) {
            button.onRender();
        }
        GL11.glPopMatrix();*/
    }

    protected float getCenterScreenWidth() {
        return  width / 2;
    }

    protected float getCenterScreenHeight() {
        return  height / 2;
    }

}
