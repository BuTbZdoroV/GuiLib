package butbzdorov.client.guiLib;

import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.functional.FunctionalDelicate;
import butbzdorov.client.guiLib.screen.IScreen;
import butbzdorov.client.guiLib.screen.ScreenController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractScreenGui extends GuiScreen implements IScreen {

    protected final static Minecraft mc = Minecraft.getMinecraft();

    protected float width;
    protected float height;

    protected float mouseX;
    protected float mouseY;

    public final static List<Button> buttons = new ArrayList<>();

    public AbstractScreenGui() {
    }
    protected boolean initialize = false;
    protected void initialize(){
        initialize = true;
    }

    @Override
    public void initGui() {
        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        width = scaledresolution.getScaledWidth();
        height = scaledresolution.getScaledHeight();
        if (!initialize) initialize();
    }



    public void afterInitGui() {
            ScreenController.registerDelicates(this, buttons);
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

        mouseX = p_73863_2_;
        mouseY = p_73863_1_;
    }

    protected float getCenterScreenWidth() {
        return  width / 2;
    }

    protected float getCenterScreenHeight() {
        return  height / 2;
    }

    @Override
    public String getScreenId() {
        return "default";
    }
}
