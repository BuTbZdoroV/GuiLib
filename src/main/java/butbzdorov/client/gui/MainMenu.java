package butbzdorov.client.gui;

import butbzdorov.client.gui.res.ResourceLoader;
import butbzdorov.client.guiLib.Container;
import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.utils.SG;
import org.newdawn.slick.Color;

@Delicate
public class MainMenu extends Container implements IDelicate {

    public MainMenu() {
        DelicateController.delicates.add(this);
    }

    @Override
    public void initGui() {
        super.initGui();
        buttons.clear();

        SG.update();

        buttons.add((Button) new Button(getCenterScreenWidth(), getCenterScreenHeight(), SG.get(200), SG.get(200))
                .drawColorBackground(Color.gray)
                .editText(text -> text.setText("Hello World!").setFontSize(24))
                .onClickButton(button -> {
                    switch (button.clickType){
                        case LEFT_MOUSE_PRESS:
                            System.out.println("Left click");
                            break;
                        case RIGHT_MOUSE_PRESS:
                                System.out.println("Right click");
                                break;
                    }

                }));
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

    }

    private final static Image image = new Image(new ResourceLoader("butbzdorov", "gui/mainmenu/background.png"), 0, 0, 1920, 1080);

    @Override
    public void onRender() {
        image.onRender();
    }
}
