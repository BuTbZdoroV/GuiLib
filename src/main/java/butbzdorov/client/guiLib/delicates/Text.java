package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.guiLib.functional.FunctionalDelicate;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFontRenderer;
import lombok.*;
import org.newdawn.slick.Color;

import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Text extends FunctionalDelicate<Text> {
    private String text = "";
    private CustomFont font = CustomFont.TTNormsBold;
    private float fontSize = 20;
    private Color color = Color.white;
    private float alpha = 1;

    public Text(String text, CustomFont font) {
        super();
        this.text = text;
        this.font = font;
        width =  CustomFontRenderer.getStringWidth(font, text);
        height = CustomFontRenderer.getStringHeight(font, text, -1);
    }

    public Text(String text) {
        super();
        this.text = text;
        width =  CustomFontRenderer.getStringWidth(font, text);
        height = CustomFontRenderer.getStringHeight(font, text, -1);
    }

    public Text(String text, Vector2d position) {
        super();
        this.text = text;
        this.position = position;

        WidthAndHeightUpdate();
    }

    public Text(String text, float posX, float posY) {
        super();
        this.text = text;
        this.position = new Vector2d(posX, posY);

        WidthAndHeightUpdate();
    }

    public Text setCentrePosX() {
        this.position.x -= getWidth()/2;
        return this;
    }
    public Text setCentrePosY() {
        this.position.y -= getHeight()/2;
        return this;
    }

    public Text setText(String text) {
        this.text = text;
        return this;
    }

    public double getPosX(){
        return position.x;
    }

    public double getPosY(){
        return position.y;
    }

    public Text setPosX(float posX) {
        this.position.x = posX;
        return this;
    }

    public Text setPosY(float posY) {
        this.position.y = posY;
        return this;
    }

    public Text setPlacePosX(float posX) {
        this.position.x += posX;
        return this;
    }

    public Text setPlacePosY(float posY) {
        this.position.y += posY;
        return this;
    }

    public Text setColor(Color color) {
        this.color = color;
        return this;
    }

    public Text setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    @Override
    public Text onClickMouse(Consumer<Text> textmoddifier) {
        onClickHandler = textmoddifier;
        return this;
    }

    public void WidthAndHeightUpdate() {
        width = CustomFontRenderer.getStringWidth(this.font.setSize(fontSize), text);
        height = CustomFontRenderer.getStringHeight(this.font.setSize((int) fontSize), text, -1);
    }

    public Text setFont(CustomFont font, float sizeFont) {
        this.fontSize = sizeFont;
        this.font = font;
        WidthAndHeightUpdate();
        return this;
    }

    public Text setFontSize(float fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Text setTextColor(Color color) {
        this.color = color;
        return this;
    }
    
    @Override
    public void onRender() {
        CustomFontRenderer.drawStringWithMaxWidth(text, (float) position.x, (float) position.y, -1, font.setSize((int) fontSize), color);
    }

    @Override
    public String getId() {
        return text + "_" + position.x + "_" + position.y;
    }

}
