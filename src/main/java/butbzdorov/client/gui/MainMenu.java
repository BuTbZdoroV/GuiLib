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
import butbzdorov.client.guiLib.utils.SG;
import butbzdorov.client.guiLib.utils.jglfontCore.JGLFont;
import butbzdorov.client.guiLib.utils.jglfontCore.JGLFontFactory;
import butbzdorov.client.guiLib.utils.lwjgl.LwjglDisplayListFontRenderer;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFontObject;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFontRenderer;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import javax.jws.Oneway;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.lwjgl.opengl.GL11.*;

@Delicate
public class MainMenu extends Container implements IDelicate {

    private static final Log log = LogFactory.getLog(MainMenu.class);
    private static double scale = 1;
    private static double dir = 1;
    private static long lastTime = System.currentTimeMillis();

    JGLFontFactory factory = new JGLFontFactory(new LwjglDisplayListFontRenderer());
    JGLFont jglFont = null;


    public MainMenu(){
        DelicateController.delicates.add(this);
        //  jglFont = factory.loadFont("assets/butbzdorov/fonts/Dimkin-Bold.ttf#size=16;glyphSide=256");
    }



    @Override
    public void initGui() {
        super.initGui();
        buttons.clear();


       SG.update();

        buttons.add(new Button(SG.get(236), SG.get(445), SG.get(343), SG.get(60))
                .addImage(new ResourceLoader("butbzdorov", "gui/mainmenu/button.png"))
                .addImage(new ResourceLoader("butbzdorov", "gui/mainmenu/playicon.png"), 0,0,SG.get(15),SG.get(20))
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


        buttons.get(0).editImage(1, image1 -> image1
                .setPosX(buttons.get(0).getText(0).getPosX())
                .setPosY(buttons.get(0).getText(0).getPosY())
                .setPlacePosX(buttons.get(0).getText(0).getWidth() + SG.get(10))
                .setPlacePosY(buttons.get(0).getText(0).getHeight()/2- image1.getEndY()/2));

        buttons.add(new Button(SG.get(236), SG.get(550), SG.get(343), SG.get(60))
                .addImage(new ResourceLoader("butbzdorov", "gui/mainmenu/button.png"))
                .addImage(new ResourceLoader("butbzdorov", "gui/mainmenu/settingsicon.png"), 0,0,SG.get(15),SG.get(20))
                .addText("Settings", CustomFont.TTNormsBold, 10)
                .onHover(button -> GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getEndX(), button.getEndY(), Color.gray, 0.4))
                .onClickButton(button -> {
                    switch (button.clickType) {
                        case LEFT_MOUSE_PRESS:
                            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                            break;
                        case RIGHT_MOUSE_PRESS:
                            mc.displayGuiScreen(new GuiMultiplayer(this));
                            break;
                    }
                }));

        buttons.get(1).editImage(1, image1 -> image1
                .setPosX(buttons.get(1).getText(0).getPosX() + SG.get(10))
                .setPosY(buttons.get(1).getText(0).getPosY())
                .setPlacePosX(buttons.get(1).getText(0).getWidth())
                .setPlacePosY(buttons.get(1).getText(0).getHeight()/2- image1.getEndY()/2));



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
