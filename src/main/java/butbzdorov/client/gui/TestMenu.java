package butbzdorov.client.gui;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.delicates.Text;
import butbzdorov.client.guiLib.delicates.prepared.ImageColor;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.ResourceCache;
import butbzdorov.client.guiLib.utils.SG;
import butbzdorov.client.guiLib.window.Resolution;
import butbzdorov.client.guiLib.window.Window;
import org.newdawn.slick.Color;

import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class TestMenu extends Window {


    public TestMenu(MainScreen MainScreen) {
        super(MainScreen, new Resolution(100, 100, 1920 / 2, 1080 / 2));
    }

    @Override
    public String getWindowId() {
        return "GuiTest";
    }

    private static Image image = null;

    float number = 2;

    @Override
    public void renderWindow(float mouseX, float mouseY, float frametime) {
        super.renderWindow(mouseX, mouseY, frametime);
        number += 2 * frametime;
        image = new ImageColor(new Vector2d(getResolution().getPosX(), getResolution().getPosY()), getResolution().getEndX(), getResolution().getEndY(), Color.red, 1);
        //resolutionX = number;
    }

    @Override
    public void initWindow() {
        clean();
        SG.update();

/*        Button button1 = addDelicate(new Button(this, 20, 0, 1920 / 2, 1080 / 2)
                .addImage(ResourceCache.getResource("butbzdorov", "gui/mainmenu/button.png"), "1")
                .addText(new Text("Я сосал", SG.get(60), SG.get(20)).setTextColor(Color.green))
                .onHover(button -> GuiUtils.drawRectS(button.getPosX(), button.getPosY(), button.getWidth(), button.getHeight(), Color.gray, 0.4))
        );

        button1.addText("Я гей", SG.get(10), SG.get(10));
        button1.getText("Я гей").setTextColor(Color.blue);*/
        super.initWindow();
    }


    @Override
    public int getLayer() {
        return -1;
    }

    private float resolutionX = 0;
    private float resolutionY = 0;
    private float resolutionEndX = 0;
    private float resolutionEndY = 0;

}
