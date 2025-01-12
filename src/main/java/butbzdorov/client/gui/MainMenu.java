package butbzdorov.client.gui;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.delicates.Text;
import butbzdorov.client.guiLib.delicates.prepared.SimpleButton;
import butbzdorov.client.guiLib.window.Resolution;
import butbzdorov.client.guiLib.window.Window;

import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.ResourceCache;
import butbzdorov.client.guiLib.utils.SG;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import org.newdawn.slick.Color;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    public class MainMenu extends Window {

    private static long lastTime = System.currentTimeMillis();

    public final List<Button> buttons = new ArrayList<>();
    static boolean flag = false;

        public MainMenu(GuiScreen mainScreen) {
            super(mainScreen);
        }

        public void initWindow() {
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
                            mc.displayGuiScreen(new GuiSelectWorld(mainScreen));
                            break;
                        case RIGHT_MOUSE_PRESS:
                            System.out.println("Right click");
                            break;
                    }
                }));


        buttons.get(0).editImage("image1", image1 -> image1
                .setPosX(buttons.get(0).getText("Hello WorlD").getPosX())
                .setPosY(buttons.get(0).getText("Hello WorlD").getPosY())
                .setPlacePosX(buttons.get(0).getText("Hello WorlD").getEndX() + SG.get(10))
                .setPlacePosY(buttons.get(0).getText("Hello WorlD").getEndY()/2- image1.getEndY()/2));


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
                            mc.displayGuiScreen(new GuiMultiplayer(mainScreen));
                            break;
                    }
                }));

        buttons.get(1).getText("Settings").onClickMouse(text -> System.out.println(text.getText()));

        buttons.get(1).editImage("image1", image1 -> image1
                .setPosX(buttons.get(1).getText("Settings").getPosX() + SG.get(10))
                .setPosY(buttons.get(1).getText("Settings").getPosY())
                .setPlacePosX(buttons.get(1).getText("Settings").getEndX())
                .setPlacePosY(buttons.get(1).getText("Settings").getEndY()/2- image1.getEndY()/2));

        buttons.add(new SimpleButton("Лунтик гей", ResourceCache.getResource("butbzdorov", "gui/mainmenu/button.png"),
                SG.get(236), SG.get(700), SG.get(343), SG.get(60))
                .onClickMouse(button -> {
                    switch (button.clickType){
                        case LEFT_MOUSE_PRESS:

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
    Text randomText = new Text(dynamicText, 0, 0);
    private void updateDynamicText() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            dynamicText = sdf.format(new Date());
            lastTime = currentTime;
        }
    }

    private final static Image image = new Image(ResourceCache.getResource("butbzdorov", "gui/mainmenu/background.png"), 0, 0, SG.get(1920), SG.get(1080));

        @Override
        public void renderWindow(float mouseX, float mouseY) {
            image.setEndX(mainScreen.width).setEndY(mainScreen.height);
          //  image.onRender();
            updateDynamicText();
            randomText.setText(dynamicText).setFontSize(60);
            super.renderWindow(mouseX, mouseY);
        }

        @Override
    public String getWindowId() {
        return "main_menu";
    }

    @Override
    public List<? extends IDelicate> getDelicates() {
        delicates.clear();
        delicates.add(image);      // Добавляем изображение
        delicates.addAll(buttons); // Добавляем все кнопки
        delicates.add(randomText); // Добавляем текст
        return delicates;
    }

}
