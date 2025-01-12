package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.guiLib.functional.EClickType;
import butbzdorov.client.guiLib.functional.FunctionalDelicate;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import butbzdorov.client.guiLib.window.IWindow;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.util.ResourceLocation;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@EqualsAndHashCode(callSuper = true)
@Data
public class Button extends FunctionalDelicate<Button> {

    protected List<Text> texts = new ArrayList<>();
    protected List<Image> images = new ArrayList<>();

    protected List<Consumer<Button>> renderActions = new ArrayList<>();

    public Button addRenderAction(Consumer<Button> action) {
        renderActions.add(action);
        return this;
    }

    public Button() {


    }

    public Button(float posX, float posY, float endX, float endY) {
        this.posX = posX;
        this.posY = posY;
        this.endX = endX;
        this.endY = endY;
    }

    public Button addText(Text text) {
        addChild(text, text.getText());
        return this;
    }

    public Button addText(String stringText, float posX, float posY) {
        Text newText = new Text(stringText, this.posX + posX, this.posY + posY);
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


    @Override
    public Button onHover(Consumer<Button> action) {
        onHoverHandler = action;
        return this;
    }

    @Override
    public void handleHover(float mouseX, float mouseY) {
        if (onHoverHandler != null && isMouseOver(mouseX, mouseY)) {
            onHoverHandler.accept(this);
        }
    }

    @Override
    public Button onClickMouse(Consumer<Button> buttonModifier) {
        return super.onClickMouse(buttonModifier);
    }

    @Override
    public void handleClick(EClickType type) {
        this.clickType = type;
        if (onClickHandler != null) {
            ((Consumer<Button>) onClickHandler).accept((Button) this);
        }
    }



    public Button drawColorBackground(Color color) {
        return addRenderAction(button -> GuiUtils.drawRectS(button.posX, button.posY, button.endX, button.endY, color, 1));
    }

    public Button editText(String textName, Consumer<Text> textModifier) {
        return editText(String.valueOf(textName), textModifier);
    }

    public Button editText(int textIndex, Consumer<Text> textModifier) {
        return editText(String.valueOf(textIndex), textModifier);
    }

    public Text getText(int index){
        return (Text) getChildDelicate(Text.class, String.valueOf(index));
    }

    public Text getText(String index){
        return (Text) getChildDelicate(Text.class, String.valueOf(index));
    }

    public Image getImage(int index){
        return (Image) getChildDelicate(Image.class, String.valueOf(index));
    }

    public Image getImage(String index){
        return (Image) getChildDelicate(Image.class, String.valueOf(index));
    }

    public Button editImage(String imageName, Consumer<Image> imageModifier) {
        editChildComponent(Image.class,imageName, imageModifier);
        return this;
    }


    public Button editImage(int imageIndex, Consumer<Image> imageModifier) {
        editChildComponent(Image.class,String.valueOf(imageIndex), imageModifier);
        return this;
    }

}
