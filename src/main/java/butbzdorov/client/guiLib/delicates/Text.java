package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.animation.TextAnimation;
import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.functional.FunctionalDelicate;
import butbzdorov.client.guiLib.functional.FunctionalDelicateController;
import butbzdorov.client.guiLib.utils.SG;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFont;
import butbzdorov.client.guiLib.utils.newCustomNPC.CustomFontRenderer;
import lombok.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Text extends FunctionalDelicate<Text> {

    private float posX;
    private float posY;

    private float width;
    private float height;

    private String text = "";
    private CustomFont font = CustomFont.TTNormsBold;
    private float fontSize = 20;
    private Color color = Color.white;
    private float alpha = 1;

    private final List<TextAnimation> animations = new ArrayList<>();

    public Text(String text, CustomFont font) {
        this.text = text;
        this.font = font;
        width =  CustomFontRenderer.getStringWidth(font, text);
        height = CustomFontRenderer.getStringHeight(font, text, -1);
    }

    public Text(String text) {
        this.text = text;
        width =  CustomFontRenderer.getStringWidth(font, text);
        height = CustomFontRenderer.getStringHeight(font, text, -1);
    }

    public Text(String text, float posX, float posY) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;

        WidthAndHeightUpdate();

        System.out.println(this.endX);
        this.endX = width;
        this.endY = height;
    }

    @Override
    public boolean isMouseOver(float mouseX, float mouseY) {
        return mouseX >= this.posX && mouseX <= this.posX + this.endX &&
                mouseY >= this.posY && mouseY <= this.posY + this.endY;
    }

    public Text setCentrePosX() {
        this.posX -= getWidth()/2;
        return this;
    }
    public Text setCentrePosY() {
        this.posY -= getHeight()/2;
        return this;
    }

    public Text setText(String text) {
        this.text = text;
        return this;
    }

    public Text setPosX(float posX) {
        this.posX = posX;
        return this;
    }

    public Text setPosY(float posY) {
        this.posY = posY;
        return this;
    }

    public Text setPlacePosX(float posX) {
        this.posX += posX;
        return this;
    }

    public Text setPlacePosY(float posY) {
        this.posY += posY;
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


    public void addAnimation(TextAnimation animation) {
        animations.add(animation);
    }

    public void removeAnimation(TextAnimation animation) {
        animations.remove(animation);
    }

  /*  @Override
    public void onStartRender(float milliseconds) {
        for (TextAnimation animation : animations) {
            animation.update(this, milliseconds);
        }
    }*/

    @Override
    public void onRender() {
        CustomFontRenderer.drawStringWithMaxWidth(text, posX, posY, -1, font.setSize((int) fontSize));
    }

    @Override
    public String getId() {
        return text + "_" + posX + "_" + posY;
    }

}
