package butbzdorov.client.gui;

import butbzdorov.client.guiLib.AbstractScreenGui;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.delicates.prepared.SimpleButton;
import butbzdorov.client.guiLib.screen.ScreenManager;
import butbzdorov.client.guiLib.utils.ResourceCache;
import butbzdorov.client.guiLib.utils.SG;

import java.util.Collections;
import java.util.List;

public class SettingsMenu extends AbstractScreenGui {

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    @Override
    public void initGui() {
        super.initGui();

        buttons.clear();

        buttons.add(new SimpleButton("Я сигма", ResourceCache.getResource("butbzdorov","gui/mainmenu/button.png")
                ,SG.get(200), SG.get(200), SG.get(200), SG.get(200))
                .onClickMouse(button -> {
                    switch (button.clickType) {
                        case LEFT_MOUSE_CLICK:
                            ScreenManager.switchToScreen("main_menu");
                            break;
                    }
                }));
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public String getScreenId() {
        return "settings_menu";
    }

    @Override
    public List<? extends IDelicate> getDelicates() {
        return buttons;
    }

    Image background = new Image(0,0,mc.displayWidth, mc.displayHeight);
    @Override
    public void renderScreen() {
        background.onRender();
    }
}
