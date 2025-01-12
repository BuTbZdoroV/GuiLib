package butbzdorov.client.guiLib.utils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiScreenEvent;

public final class CustomSG {

    //ScaleGui, SG.get(Число);

    private static final Minecraft mc = Minecraft.getMinecraft();
    public static float FULL_HD = 16 / 9f;
    public static float SXGA = 4 / 3f;

    public static float screenCenterX, screenCenterY;
    public static int screenWidth, screenHeight;
    public static float scaleValue;

    public static float resolutionX, resolutionY = 0;

    public float width = 1920;
    public float HALF_WIDTH = width / 2f;
    public float height = 1080;
    public float HALF_HEIGHT = height / 2f;

    public static void update() {
        refresh(FULL_HD);
    }

    public static void update(float minAspect) {
        refresh(minAspect);
    }

    private static void refresh(float minAspect) {
        screenWidth = mc.displayWidth;
        screenHeight = mc.displayHeight;
        screenCenterX = screenWidth / 2f;
        screenCenterY = screenHeight / 2f;
        float ratio =  screenWidth / (float) screenHeight;
        scaleValue = ratio < minAspect ? screenHeight / (1.0f + (minAspect - ratio)) : screenHeight;
    }

    public CustomSG(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float get(Number value) {
        return (scaleValue / (height / value.floatValue())) / 2;
    }


    @SubscribeEvent
    public void renderGameGui(GuiScreenEvent.DrawScreenEvent.Pre e){
        resolutionX = e.gui.width;
        resolutionY = e.gui.height;
    }

}
