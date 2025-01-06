package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.functional.EClickType;
import butbzdorov.client.guiLib.functional.FunctionalDelicateController;
import butbzdorov.client.guiLib.functional.IFunctionalDelicate;
import butbzdorov.client.guiLib.utils.GuiUtils;
import lombok.Data;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Delicate
@Data
public class Button implements IFunctionalDelicate {

    private String stringText;
    private float posX;
    private float posY;
    private float endX;
    private float endY;

    private Text text;
    private Image image;

    public EClickType clickType;

    private Consumer<Button> onClickHandler;

    private List<Consumer<Button>> renderActions = new ArrayList<>();

    public Button addRenderAction(Consumer<Button> action) {
        renderActions.add(action);
        return this;
    }

    @Override
    public void onRender() {
        for (Consumer<Button> action : renderActions) {
            action.accept(this);
        }

        text.onRender();
        image.onRender();
    }

    public Button(float posX, float posY, float endX, float endY) {
        this.stringText = "";
        this.posX = posX;
        this.posY = posY;
        this.endX = endX;
        this.endY = endY;

        text = new Text(stringText, posX, posY);
        image = new Image(posX, posY, endX, endY);

        FunctionalDelicateController.functionalDelicates.add(this);
    }

    @Override
    public IFunctionalDelicate onClickButton(Consumer<Button> handler) {
        this.onClickHandler = handler;
        return this;
    }

    public void handleClick(EClickType type) {
        this.clickType = type;
        if (onClickHandler != null) {
            onClickHandler.accept(this);
        }
    }


    public Button drawColorBackground(Color color) {
        return addRenderAction(button -> GuiUtils.drawRectS(button.posX, button.posY, button.endX, button.endY, color, 1));
    }


    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= this.posX && mouseX <= this.posX + this.endX &&
                mouseY >= this.posY && mouseY <= this.posY + this.endY;
    }


    public Button editText(Consumer<Text> textModifier) {
        textModifier.accept(this.text);
        return this;
    }

    public Button editImage(Consumer<Image> imageModifier) {
        imageModifier.accept(this.image);
        return this;
    }

}
