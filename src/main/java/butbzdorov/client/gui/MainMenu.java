package butbzdorov.client.gui;

import butbzdorov.client.gui.res.ResourceLoader;
import butbzdorov.client.guiLib.Container;
import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.delicates.Text;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.ResourceCache;
import butbzdorov.client.guiLib.utils.SG;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import org.newdawn.slick.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

@Delicate
public class MainMenu extends Container implements IDelicate {

    private static long lastTime = System.currentTimeMillis();

    public MainMenu(){
        DelicateController.delicates.add(this);
    }



    @Override
    public void initGui() {
        super.initGui();
        buttons.clear();


       SG.update();

        buttons.add(new Button(SG.get(236), SG.get(445), SG.get(343), SG.get(60))
                .addImage(ResourceCache.getResource("butbzdorov:gui/mainmenu/button.png"), "image0")
                .addImage(ResourceCache.getResource("butbzdorov:gui/mainmenu/playicon.png"), "image1", 0,0,SG.get(15),SG.get(20))
                .addText("Hello WorlD", CustomFont.TTNormsBold, 30)
                .onHover(button -> GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getEndX(), button.getEndY(), Color.gray, 0.4))
                .onClickButton(button -> {
                    switch (button.clickType) {
                        case LEFT_MOUSE_PRESS:
                            mc.displayGuiScreen(new GuiSelectWorld(this));
                            break;
                        case RIGHT_MOUSE_PRESS:
                            System.out.println("Right click");
                            break;
                    }
                }));


        buttons.get(0).editImage("image1", image1 -> image1
                .setPosX(buttons.get(0).getText("Hello WorlD").getPosX())
                .setPosY(buttons.get(0).getText("Hello WorlD").getPosY())
                .setPlacePosX(buttons.get(0).getText("Hello WorlD").getWidth() + SG.get(10))
                .setPlacePosY(buttons.get(0).getText("Hello WorlD").getHeight()/2- image1.getEndY()/2));

        buttons.get(0).getText("Hello WorlD").isActive = true;

        buttons.add(new Button(SG.get(236), SG.get(550), SG.get(343), SG.get(60))
                .addImage(new ResourceLoader("butbzdorov", "gui/mainmenu/button.png"), "image0")
                .addImage(new ResourceLoader("butbzdorov", "gui/mainmenu/settingsicon.png"), "image1", 0,0,SG.get(15),SG.get(20))
                .addText("Settings", CustomFont.TTNormsBold, 10)
                .onHover(button -> GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getEndX(), button.getEndY(), Color.gray, 0.4))
                .onClickButton(button -> {
                    switch (button.clickType) {
                        case LEFT_MOUSE_PRESS:
                            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                            break;
                        case RIGHT_MOUSE_PRESS:
                            mc.displayGuiScreen(new GuiMultiplayer(this));
                            break;
                    }
                }));

        buttons.get(1).editImage("image1", image1 -> image1
                .setPosX(buttons.get(1).getText("Settings").getPosX() + SG.get(10))
                .setPosY(buttons.get(1).getText("Settings").getPosY())
                .setPlacePosX(buttons.get(1).getText("Settings").getWidth())
                .setPlacePosY(buttons.get(1).getText("Settings").getHeight()/2- image1.getEndY()/2));

        afterInitGui();

        System.out.println("Debug");


    }

    private String dynamicText = "Loading111...";
    Text randomText = new Text(dynamicText, SG.screenCenterX, SG.screenCenterY);
    private void updateDynamicText() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            dynamicText = "Current time: " + sdf.format(new Date());
            lastTime = currentTime;
        }
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

/*
        GuiUtils.drawRectS(0,0, mc.displayWidth, mc.displayHeight, Color.black, 1);
        GL11.glPushMatrix();

        // Устанавливаем проекцию для 2D
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, this.width, this.height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        // Включаем прозрачность и текстуры
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        // Рисуем текст
        jglFont.renderText((int) SG.get(100), (int) SG.get(100), "Hello World!", 1, 1, 1.f, 1.f, 1.f, 1.f);

        GL11.glPopMatrix();

        int error = GL11.glGetError();
        if (error != GL11.GL_NO_ERROR) {
            System.err.println("OpenGL Error: " + error);
        }*/
    }




    private final static Image image = new Image(new ResourceLoader("butbzdorov", "gui/mainmenu/background.png"), 0, 0, SG.get(1920), SG.get(1080));

    @Override
    public void onRender() {
        image.setEndX(this.width).setEndY(this.height);
        image.onRender();

        updateDynamicText();
        randomText.setText(dynamicText);
        randomText.onRender();
    }
}
