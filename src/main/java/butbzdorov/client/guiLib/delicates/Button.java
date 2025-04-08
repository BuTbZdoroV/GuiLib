package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.functional.EClickType;
import butbzdorov.client.guiLib.functional.FunctionalDelicate;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import butbzdorov.client.guiLib.window.IWindow;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.util.ResourceLocation;
import org.newdawn.slick.Color;

import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public Button(Vector2d position, float width, float height) {
        super(position, width, height);
    }

    public Button(IWindow window, Vector2d position, float width, float height) {
        super(window, position, width, height);
    }

    public Button(float posX, float posY, float width, float height) {
        super(new Vector2d(posX, posY), width, height);
    }

    public Button(IWindow window, float posX, float posY, float width, float height) {
        super(window, new Vector2d(posX, posY), width, height);
    }

    @Override
    public Button init() {
        return (Button) super.init();
    }

    public Button addText(Text text) {
        Text newText = new Text(text.getText(), new Vector2d(this.position.x + text.getPosX(), this.position.y + text.getPosY()))
                .setTextColor(text.getColor())
                .setFont(text.getFont(), text.getFontSize());
        addChild(newText, newText.getText());
        return this;
    }

    public Button addText(String stringText, Vector2d position) {
        Text newText = new Text(stringText, position);
        addChild(newText, stringText);
        return this;
    }

    public Button addText(String stringText, float posX, float posY) {
        Text newText = new Text(stringText, new Vector2d(posX, posY));
        addChild(newText, stringText);
        return this;
    }


    public Button addCenteredText(String text, String identifier) {
        Text newText = new Text(text, new Vector2d(position.x + width / 2, position.y));
        newText.setCentrePosX().setCentrePosY();  // Установка центра
        addChild(newText, identifier);  // Добавляем текст с уникальным идентификатором
        return this;
    }

    public Button addCenteredText(String text) {
        Text newText = new Text(text, new Vector2d(position.x + width / 2, position.y + height / 2));
        newText.setCentrePosX().setCentrePosY();
        addChild(newText, newText.getText());
        return this;
    }

    public Button addText(String text, String identifier) {
        Text newText = new Text(text, new Vector2d(position.x + width / 2, position.y + height / 2));
        newText.setCentrePosX().setCentrePosY();  // Установка центра
        addChild(newText, identifier);  // Добавляем текст с уникальным идентификатором
        return this;
    }

    public Button addText(String stringText, CustomFont font, float fontSize) {
        Text newText = new Text(stringText, new Vector2d(position.x + width / 2, position.y + height / 2));
        newText.setFont(font, fontSize).setCentrePosX().setCentrePosY();
        addChild(newText, stringText);
        return this;
    }


    public Button addImage(Image image) {
        addChild(image, image.getImageName());
        return this;
    }

    public Button addImage(ResourceLocation image, String identifier, Vector2d position, float width, float height) {
        Image newImage = new Image(image ,new Vector2d(this.position.x + position.x, this.position.y + position.y) ,  width, height);
        addChild(newImage, identifier);
        return this;
    }

    public Button addImage(ResourceLocation image, String identifier) {
        Image newImage = new Image(image, new Vector2d(this.position.x, this.position.y), width, height);
        addChild(newImage, identifier);
        return this;
    }

    public Button addImage(ResourceLocation image, Vector2d position, float endX, float endY) {
        Image newImage = new Image(image ,new Vector2d(this.position.x + position.x, this.position.y + position.y), endX, endY);
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
            (onClickHandler).accept(this);
        }
    }



    public Button drawColorBackground(Color color) {
        return addRenderAction(button -> GuiUtils.drawRectS(button.position.x, button.position.y, button.width, button.height, color, 1));
    }

    public Button editText(String textName, Consumer<Text> textModifier) {
        return editText(String.valueOf(textName), textModifier);
    }

    public Button editText(int textIndex, Consumer<Text> textModifier) {
        return editText(String.valueOf(textIndex), textModifier);
    }


    public Optional<Text> getText(int index){
        return Optional.ofNullable(getChildDelicate(Text.class, String.valueOf(index)));
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
        editChildComponent(Image.class,imageName, imageModifier);
        return this;
    }

    public Button editImage(int imageIndex, Consumer<Image> imageModifier) {
        editChildComponent(Image.class,String.valueOf(imageIndex), imageModifier);
        return this;
    }

}
