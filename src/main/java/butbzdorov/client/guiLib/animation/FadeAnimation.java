package butbzdorov.client.guiLib.animation;

import butbzdorov.client.guiLib.delicates.Text;
import org.newdawn.slick.Color;

public class FadeAnimation implements TextAnimation {

    private final float targetAlpha;
    private final float duration;
    private float elapsedTime = 0;

    public FadeAnimation(float targetAlpha, float duration) {
        this.targetAlpha = targetAlpha;
        this.duration = duration;
    }

    @Override
    public void update(Text text, float milliseconds) {
        elapsedTime += milliseconds;
        float progress = Math.min(elapsedTime / duration, 1);

        float newAlpha = text.getAlpha() * (1 - progress) + targetAlpha * progress;
        text.setColor(new Color(text.getColor().r, text.getColor().g, text.getColor().b, newAlpha));

        if (progress >= 1) {
            text.getAnimations().remove(this);
        }
    }
}
