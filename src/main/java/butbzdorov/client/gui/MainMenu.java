package butbzdorov.client.gui;

import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.delicates.Text;
import butbzdorov.client.guiLib.delicates.prepared.ImageColor;
import butbzdorov.client.guiLib.functional.EClickType;
import butbzdorov.client.guiLib.functional.ScrollPanel;
import butbzdorov.client.guiLib.window.Window;

import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.ResourceCache;
import butbzdorov.client.guiLib.utils.SG;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import javax.vecmath.Vector2d;
import java.text.SimpleDateFormat;
import java.util.Date;

import static butbzdorov.client.guiLib.functional.EClickType.LEFT_MOUSE_PRESS;
import static butbzdorov.client.guiLib.functional.EClickType.RIGHT_MOUSE_PRESS;

public class MainMenu extends Window {

    private static long lastTime = System.currentTimeMillis();

    static boolean flag = false;

    public MainMenu(MainScreen mainScreen) {
        super(mainScreen);
    }

    public void initWindow() {
        clean();

        ScrollPanel scrollPanel = new ScrollPanel(new Vector2d(SG.get(400), SG.get(50)), SG.get(600), SG.get(900));

        for (int i = 0; i < 20; i++) { // 20 строк
            for (int j = 0; j < 7; j++) { // 3 колонки
                scrollPanel.addContent(new Button(SG.get(j * 130), SG.get(i * 60), SG.get(120), SG.get(50))
                                .addText(("Item " + ((i * 3) + j + 1)), "0")
                                .addImage(Color.blue)
                                .onHover(button -> {
                                    GL11.glPushMatrix();
                                    GuiUtils.drawRectS(
                                            button.getPosX() - scrollPanel.getScrollX(),
                                            button.getPosY() - scrollPanel.getScrollY(),
                                            button.getWidth(),
                                            button.getHeight(),
                                            Color.darkGray,
                                            0.7f);
                                    GL11.glPopMatrix();
                                })
                                .onClickMouse(button -> {
                                    if (button.clickType == EClickType.LEFT_MOUSE_CLICK) {
                                        System.out.println("Clicked: " + button.getText("0").getText());
                                    }
                                }),
                        "ScrollContent_" + i + "_" + j);
            }
        }

        addScrollPanel(scrollPanel);

        SG.update();

        Button button1 = addDelicate(new Button(SG.get(236), SG.get(100), SG.get(343), SG.get(60))
                .addImage(ResourceCache.getResource("butbzdorov", "gui/mainmenu/button.png"), "image0")
                .addImage(ResourceCache.getResource("butbzdorov", "gui/mainmenu/playicon.png"), "image1", new Vector2d(0, 0), SG.get(15), SG.get(20))
                .addText("Hello WorlD", CustomFont.TTNormsBold, 30)
                .onHover(button -> GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getWidth(), button.getHeight(), Color.gray, 0.4))
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


        button1.editImage("image1", image1 -> image1
                .setPosX(button1.getText("Hello WorlD").getPosX())
                .setPosY(button1.getText("Hello WorlD").getPosY())
                .setPlacePosX(button1.getText("Hello WorlD").getWidth() + SG.get(10))
                .setPlacePosY(button1.getText("Hello WorlD").getHeight() / 2 - image1.getHeight() / 2));


        Button button2 = addDelicate(new Button(SG.get(236), SG.get(100), SG.get(343), SG.get(60))
                .addImage(ResourceCache.getResource("butbzdorov", "gui/mainmenu/button.png"), "image0")
                .addImage(ResourceCache.getResource("butbzdorov", "gui/mainmenu/settingsicon.png"), "image1", new Vector2d(), SG.get(15), SG.get(20))
                .addText("Settings", CustomFont.TTNormsBold, 10)
                .onHover(button -> {
                    GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getWidth(), button.getHeight(), Color.gray, 0.4);
                })
                .onClickMouse(button -> {
                    switch (button.clickType) {
                        case LEFT_MOUSE_RELEASE:
                            GuiUtils.drawLine(button.getPosX() + button.getWidth(), button.getPosY(), mainScreen.mouseX, mainScreen.mouseY, 5, 100, 0xfffffff, 1);
                            break;
                        case RIGHT_MOUSE_RELEASE:
                            mc.displayGuiScreen(new GuiMultiplayer(mainScreen));
                            break;
                    }
                }));

        button2.getText("Settings").onClickMouse(text -> System.out.println(text.getText()));

        button2.editImage("image1", image1 -> image1
                .setPosX(button2.getText("Settings").getPosX() + SG.get(10))
                .setPosY(button2.getText("Settings").getPosY())
                .setPlacePosX(button2.getText("Settings").getWidth())
                .setPlacePosY(button2.getText("Settings").getHeight() / 2 - image1.getHeight() / 2));


        image1.setWidth(SG.get(100)).setHeight(SG.get(100));

        addDelicate(image);
        addDelicate(image1);

        super.initWindow();

    }


    private String dynamicText = "Loading111...";
    Text randomText = addDelicate(new Text(dynamicText));

    private void updateDynamicText() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            dynamicText = sdf.format(new Date());
            lastTime = currentTime;
        }
    }

    private final Image image = new Image(ResourceCache.getResource("butbzdorov", "gui/mainmenu/background.png"), new Vector2d(0, 0), 1920, 1080);

    private final ImageColor image1 = new ImageColor(new Vector2d(), SG.get(100), SG.get(100), Color.blue);
    private boolean dragging = false;
    private float offsetX, offsetY;

    @Override
    public void renderWindow(float mouseX, float mouseY, float frametime) {
        image.setWidth(SG.get(1920)).setHeight(SG.get(1080));

        //  image.onRender();
        updateDynamicText();
        randomText.setText(dynamicText).setFontSize(60);


        if (Mouse.isButtonDown(0)) {
            if (!dragging && getDelicate(image1).isMouseOver(mouseX, mouseY)) {
                dragging = true;
                offsetX = (float) (mouseX - getDelicate(image1).getPosX());
                offsetY = (float) (mouseY - getDelicate(image1).getPosY());
            }
            if (dragging) {
                float newX = mouseX - offsetX;
                float newY = mouseY - offsetY;

                // Ограничение границ
                newX = Math.max(0, Math.min(newX, mainScreen.width - getDelicate(image1).getWidth()));
                newY = Math.max(0, Math.min(newY, mainScreen.height - getDelicate(image1).getHeight()));

                getDelicate(image1).setPosX(newX);
                getDelicate(image1).setPosY(newY);
            }
        } else {
            dragging = false;
        }

        super.renderWindow(mouseX, mouseY, frametime);
    }

    @Override
    public String getWindowId() {
        return "main_menu";
    }

    @Override
    public int getLayer() {
        return 1;
    }

}
