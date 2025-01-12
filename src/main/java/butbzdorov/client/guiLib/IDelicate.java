package butbzdorov.client.guiLib;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public abstract class IDelicate<T extends IDelicate<T>> {

    protected String id;

    @Getter protected float posX;
    @Getter protected float posY;
    @Getter protected float endX;
    @Getter protected float endY;

    public T setPosX(float posX) {
        this.posX = posX;
        return (T) this;
    }

    public T setPosY(float posY) {
        this.posY = posY;
        return (T) this;
    }

    public T setEndX(float endX) {
        this.endX = endX;
        return (T) this;
    }

    public T setEndY(float endY) {
        this.endY = endY;
        return (T) this;
    }

    public IDelicate() {
        this.id = UUID.randomUUID().toString();
    }

    void onStartRender(float milliseconds) {}

    public abstract void onRender();

}
