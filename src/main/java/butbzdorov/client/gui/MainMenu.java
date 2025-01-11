package butbzdorov.client.gui;

import butbzdorov.client.gui.res.ResourceLoader;
import butbzdorov.client.guiLib.Container;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.delicates.Text;
import butbzdorov.client.guiLib.delicates.prepared.SimpleButton;
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

    static boolean flag = false;

    @Override
    public void initGui() {
        super.initGui();
        buttons.clear();

       SG.update();

        buttons.add(new Button(SG.get(236), SG.get(445), SG.get(343), SG.get(60))
                .addImage(ResourceCache.getResource("butbzdorov","gui/mainmenu/button.png"), "image0")
                .addImage(ResourceCache.getResource("butbzdorov","gui/mainmenu/playicon.png"), "image1", 0,0,SG.get(15),SG.get(20))
                .addText("Hello WorlD", CustomFont.TTNormsBold, 30)
                .onHover(button -> GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getEndX(), button.getEndY(), Color.gray, 0.4))
                .onClickMouse(button -> {
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


        buttons.add(new Button(SG.get(236), SG.get(550), SG.get(343), SG.get(60))
                .addImage(ResourceCache.getResource("butbzdorov", "gui/mainmenu/button.png"), "image0")
                .addImage(ResourceCache.getResource("butbzdorov", "gui/mainmenu/settingsicon.png"), "image1", 0,0,SG.get(15),SG.get(20))
                .addText("Settings", CustomFont.TTNormsBold, 10)
                .onHover(button -> GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getEndX(), button.getEndY(), Color.gray, 0.4))
                .onClickMouse(button -> {
                    switch (button.clickType) {
                        case LEFT_MOUSE_PRESS:
                            //mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                            break;
                        case RIGHT_MOUSE_PRESS:
                            mc.displayGuiScreen(new GuiMultiplayer(this));
                            break;
                    }
                }));

        buttons.get(1).getText("Settings").onClickMouse(text -> System.out.println(text.getText()));

        buttons.get(1).editImage("image1", image1 -> image1
                .setPosX(buttons.get(1).getText("Settings").getPosX() + SG.get(10))
                .setPosY(buttons.get(1).getText("Settings").getPosY())
                .setPlacePosX(buttons.get(1).getText("Settings").getWidth())
                .setPlacePosY(buttons.get(1).getText("Settings").getHeight()/2- image1.getEndY()/2));

        afterInitGui();

        buttons.add(new SimpleButton("Лунтик гей", ResourceCache.getResource("butbzdorov", "gui/mainmenu/button.png"),
                SG.get(236), SG.get(700), SG.get(343), SG.get(60))
                .onClickMouse(button -> {
                    switch (button.clickType){
                        case LEFT_MOUSE_PRESS:
                            button.getText(0).setText("Hello WorlD");
                            button.addText("HUY", SG.get(30), SG.get(20));
                            break;
                        case RIGHT_MOUSE_PRESS:
                            button.getText(0).setText("Лунтик гей");
                            button.addText("pidr", SG.get(30), SG.get(20));
                            break;
                    }
                }));


        System.out.println("Debug");


    }

    private String dynamicText = "Loading111...";
    Text randomText = new Text(dynamicText, SG.screenCenterX, SG.screenCenterY);
    private void updateDynamicText() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            dynamicText = sdf.format(new Date());
            lastTime = currentTime;
        }
    }

    private final static Image image = new Image(new ResourceLoader("butbzdorov", "gui/mainmenu/background.png"), 0, 0, SG.get(1920), SG.get(1080));

    @Override
    public void onRender() {
        image.setEndX(this.width).setEndY(this.height);
        image.onRender();

        updateDynamicText();
        randomText.setText(dynamicText).setFontSize(60);
        randomText.onRender();
    }
}
