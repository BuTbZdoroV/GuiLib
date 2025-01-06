package butbzdorov.client.guiLib.delicates;

import butbzdorov.client.guiLib.animation.TextAnimation;
import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.functional.FunctionalDelicateController;
import butbzdorov.client.guiLib.utils.CustomFont.CustomFont;
import butbzdorov.client.guiLib.utils.CustomFont.CustomFontRenderer;
import butbzdorov.client.guiLib.functional.IFunctionalDelicate;
import butbzdorov.client.guiLib.utils.SG;
import lombok.*;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.List;

@Delicate
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Text implements IFunctionalDelicate {

    private float posX;
    private float posY;
    private String text = "";
    private float size = 1;
    private Color color = Color.white;
    private float alpha = 1;
    private CustomFont font = CustomFont.TTNormsBold;
    private float fontSize = 20;

    private final List<TextAnimation> animations = new ArrayList<>();

    public Text(String text, CustomFont font) {
        this.text = text;
        this.font = font;
    }

    public Text(String text) {
        this.text = text;
    }

    public Text(String text, float posX, float posY) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        FunctionalDelicateController.functionalDelicates.add(this);
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

    public Text setColor(Color color) {
        this.color = color;
        return this;
    }

    public Text setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public Text setFont(CustomFont font) {
        this.font = font;
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

    @Override
    public void onStartRender(float milliseconds) {
        for (TextAnimation animation : animations) {
            animation.update(this, milliseconds);
        }
    }

    @Override
    public void onRender() {
        CustomFontRenderer.drawStringWithMaxWidth(text, posX, posY, -1, color, font.setSize(SG.get(fontSize*2)));
    }
}
