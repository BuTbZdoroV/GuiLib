package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.gui.res.ResourceLoader;
import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.functional.EClickType;
import butbzdorov.client.guiLib.functional.FunctionalDelicateController;
import butbzdorov.client.guiLib.functional.IFunctionalDelicate;
import butbzdorov.client.guiLib.utils.GuiUtils;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFontObject;
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

    @Override
    public void onRender() {
        for (Consumer<Button> action : renderActions) {
            action.accept(this);
        }

        images.forEach(Image::onRender);
        for (Text text : texts) {
            text.onRender();
        }
       // texts.forEach(Text::onRender);
    }

    public Button(float posX, float posY, float endX, float endY) {
        this.stringText = "";
        this.posX = posX;
        this.posY = posY;
        this.endX = endX;
        this.endY = endY;

        FunctionalDelicateController.functionalDelicates.add(this);
    }

    public Button addText(Text text) {
        texts.add(text);
        return this;
    }

    public Button addText(String stringText, float posX, float posY) {
        texts.add(new Text(stringText,this.posX + posX,this.posY + posY));
        return this;
    }

    public Button addText(String stringText) {
        texts.add(new Text(stringText, this.posX + this.endX/2,this.posY + this.endY/2).setCentrePosX().setCentrePosY());
        return this;
    }

    public Button addText(String stringText, CustomFont font, float fontSize) {
        texts.add(new Text(stringText, this.posX + this.endX/2,this.posY + this.endY/2).setFont(font, fontSize).setCentrePosX().setCentrePosY());
        return this;
    }

    public Button addImage(Image image) {
        images.add(image);
        return this;
    }

    public Button addImage(ResourceLoader image) {
        images.add(new Image(image, this.posX, this.posY, this.endX, this.endY));
        return this;
    }

    public Button addImage(ResourceLoader image, float posX, float posY, float endX, float endY) {
        images.add(new Image(image ,this.posX + posX, this.posY + posY, endX, endY));
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
        Text text = texts.stream()
                .filter(t -> t.getText().equals(textName))
                .findFirst()
                .orElse(null);

        if (text != null) {
            textModifier.accept(text);
        } else {
            System.out.println("Text not found: " + textName);
        }
        return this;
    }


    public Button editText(int textIndex, Consumer<Text> textModifier) {
        textModifier.accept(this.texts.get(textIndex));
        return this;
    }

    public Text getText(int index){
        return texts.get(index);
    }

    public Text getText(String textName) {
        return texts.stream()
                .filter(text -> text.getText().equals(textName))
                .findFirst()
                .orElse(null);
    }

    public Image getImage(int index){
        return images.get(index);
    }

    public Image getImage(String textName) {
        return images.stream()
                .filter(image -> image.getImageName().equals(textName))
                .findFirst()
                .orElse(null);
    }

    public Button editImage(String imageName, Consumer<Image> imageModifier) {
        Image image = images.stream()
                .filter(i -> i.getImageName().equals(imageName))
                .findFirst()
                .orElse(null);

        if (image != null) {
            imageModifier.accept(image);
        } else {
            System.out.println("Image not found: " + imageName);
        }
        return this;
    }


    public Button editImage(int imageIndex, Consumer<Image> imageModifier) {
        imageModifier.accept(this.images.get(imageIndex));
        return this;
    }

}
