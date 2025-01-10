package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.functional.EClickType;
import butbzdorov.client.guiLib.functional.FunctionalDelicate;
import butbzdorov.client.guiLib.functional.FunctionalDelicateController;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.util.ResourceLocation;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@EqualsAndHashCode(callSuper = true)
@Delicate
@Data
public class Button extends FunctionalDelicate {

    private String stringText;
    private float posX;
    private float posY;
    private float endX;
    private float endY;

    private List<Text> texts = new ArrayList<>();
    private List<Image> images = new ArrayList<>();

    public EClickType clickType;

    private Consumer<Button> onClickHandler;
    private Consumer<Button> onHoverHandler;

    private List<Consumer<Button>> renderActions = new ArrayList<>();

    public Button addRenderAction(Consumer<Button> action) {
        renderActions.add(action);
        return this;
    }

    public Button(float posX, float posY, float endX, float endY) {
        this.stringText = "";
        this.posX = posX;
        this.posY = posY;
        this.endX = endX;
        this.endY = endY;
    }

    public Button addText(Text text) {
        text.setCentrePosX().setCentrePosY();
        addChild(text, text.getText());
        return this;
    }

    public Button addText(String stringText, float posX, float posY) {
        Text newText = new Text(stringText, posX + endX / 2, posY + endY / 2);
        newText.setCentrePosX().setCentrePosY();
        addChild(newText, stringText);
        return this;
    }


    public Button addText(String text, String identifier) {
        Text newText = new Text(text, posX + endX / 2, posY + endY / 2);
        newText.setCentrePosX().setCentrePosY();  // Установка центра
        addChild(newText, identifier);  // Добавляем текст с уникальным идентификатором
        return this;
    }

    public Button addText(String stringText, CustomFont font, float fontSize) {
        Text newText = new Text(stringText, posX + endX / 2, posY + endY / 2);
        newText.setFont(font, fontSize).setCentrePosX().setCentrePosY();
        addChild(newText, stringText);
        return this;
    }


    public Button addImage(Image image) {
        addChild(image, image.getImageName());
        return this;
    }

    public Button addImage(ResourceLocation image, String identifier, float posX, float posY, float endX, float endY) {
        Image newImage = new Image(image ,this.posX + posX, this.posY + posY, endX, endY);
        addChild(newImage, identifier);
        return this;
    }

    public Button addImage(ResourceLocation image, String identifier) {
        Image newImage = new Image(image, posX, posY, endX, endY);
        addChild(newImage, identifier);
        return this;
    }

    public Button addImage(ResourceLocation image, float posX, float posY, float endX, float endY) {
        Image newImage = new Image(image ,this.posX + posX, this.posY + posY, endX, endY);
        addChild(newImage, newImage.getImageName());
        return this;
    }


    public Button onHover(Consumer<Button> action) {
        onHoverHandler = action;
        return this;
    }

    public void handleHover(float mouseX, float mouseY){
        if (onHoverHandler != null) {
            if (isMouseOver(mouseX, mouseY)) {
                onHoverHandler.accept(this);
            }
        }
    }


    @Override
    public Button onClickButton(Consumer<Button> handler) {
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


    public boolean isMouseOver(float mouseX, float mouseY) {
        return mouseX >= this.posX && mouseX <= this.posX + this.endX &&
                mouseY >= this.posY && mouseY <= this.posY + this.endY;
    }


    public Button editText(String textName, Consumer<Text> textModifier) {
        return editText(String.valueOf(textName), textModifier);
    }


    public Button editText(int textIndex, Consumer<Text> textModifier) {
        return editText(String.valueOf(textIndex), textModifier);
    }

    public Text getText(int index){
        return getChildDelicate(Text.class, String.valueOf(index));
    }


    public Text getText(String index){
        return getChildDelicate(Text.class, String.valueOf(index));
    }

    public Image getImage(int index){
        return getChildDelicate(Image.class, String.valueOf(index));
    }

    public Image getImage(String index){
        return getChildDelicate(Image.class, String.valueOf(index));
    }

    public Button editImage(String imageName, Consumer<Image> imageModifier) {
        return (Button) editChildComponent(Image.class,imageName, imageModifier);
    }


    public Button editImage(int imageIndex, Consumer<Image> imageModifier) {
        return (Button) editChildComponent(Image.class,String.valueOf(imageIndex), imageModifier);
    }

}
