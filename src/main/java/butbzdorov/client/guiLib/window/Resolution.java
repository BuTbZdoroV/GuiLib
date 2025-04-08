package butbzdorov.client.guiLib.window;


import butbzdorov.client.guiLib.utils.SG;
import lombok.Data;

@Data
public class Resolution {

    float posX = 0;
    float posY = 0;
    float endX = 0;
    float endY = 0;

    public Resolution(float posX, float posY, float endX, float endY) {
        this.posX = posX;
        this.posY = posY;
        this.endX = endX;
        this.endY = endY;
    }

}
