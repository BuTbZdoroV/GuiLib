package butbzdorov.client.gui;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.delicates.prepared.SimpleButton;
import butbzdorov.client.guiLib.delicates.prepared.WindowButton;
import butbzdorov.client.guiLib.utils.ResourceCache;
import butbzdorov.client.guiLib.utils.SG;
import butbzdorov.client.guiLib.window.Resolution;
import butbzdorov.client.guiLib.window.Window;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class GuiTest extends Window {

    private final List<Button> buttons = new ArrayList<>();

    public GuiTest(GuiScreen MainScreen) {
        super(MainScreen, new Resolution(20, 20, 500, 500));
    }

    @Override
    public String getWindowId() {
        return "GuiTest";
    }

    private final static Image image = new Image(ResourceCache.getResource("butbzdorov", "gui/mainmenu/background.png"), 0, 0, SG.get(1920), SG.get(1080));

    float number = 2;
    @Override
    public void renderWindow(float mouseX, float mouseY) {
        super.renderWindow(mouseX, mouseY);
           image.setPosX(SG.get(resolution.getPosX())).setPosY(SG.get(resolution.getPosY())).setEndX(SG.get(resolution.getEndX())).setEndY(SG.get(resolution.getEndY()));

           resolution.setPosX(resolution.getPosX() + SG.get(number));
           number++;
    }

    @Override
    public void initWindow() {
        super.initWindow();
        buttons.clear();
        SG.update();

        buttons.add(new WindowButton(this,"Я СОСАЛ", ResourceCache.getResource("butbzdorov", "gui/mainmenu/button.png"),
                SG.get(200), SG.get(200), SG.get(200), SG.get(100)));
    }

    @Override
    public List<? extends IDelicate> getDelicates() {
        delicates.clear();
        delicates.add(image);
        delicates.addAll(buttons);
        return delicates;
    }
}
