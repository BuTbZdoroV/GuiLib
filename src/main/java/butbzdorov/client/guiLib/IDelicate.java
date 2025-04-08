package butbzdorov.client.guiLib;

import butbzdorov.client.guiLib.window.IWindow;
import butbzdorov.client.guiLib.window.Resolution;
import lombok.Data;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import javax.vecmath.Vector2d;
import java.util.Objects;
import java.util.UUID;

@Data
public abstract class IDelicate<T extends IDelicate<T>> {

    protected final String id;

    protected Vector2d position = new Vector2d();
    @Getter protected float width;
    @Getter protected float height;

    @Getter protected int zLevel = 0;

    public IWindow window; //TODO Когда нибуд

    public IDelicate(IWindow window, Vector2d position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.window = window;
        this.id = UUID.randomUUID().toString();
        setScaledBounds(window.getResolution());
    }

    public IDelicate(Vector2d position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.id = UUID.randomUUID().toString();
    }

    public IDelicate() {
        this.id = UUID.randomUUID().toString();
    }

    public double getPosX(){
        return position.x;
    }

    public double getPosY(){
        return position.y;
    }

    public T setPosX(double posX) {
        this.position.x = posX;
        return (T) this;
    }

    public T setPosY(double posY) {
        this.position.y = posY;
        return (T) this;
    }

    public boolean isMouseOver(float mouseX, float mouseY) {
        return mouseX >= this.position.x && mouseX <= this.position.x + this.width &&
                mouseY >= this.position.y && mouseY <= this.position.y + this.height;
    }

    public T setWidth(float width) {
        this.width = width;
        return (T) this;
    }

    public T setHeight(float height) {
        this.height = height;
        return (T) this;
    }

    public T setZLevel(int zLevel) {
        this.zLevel += zLevel;
        return (T) this;
    }

    public void setScaledBounds(Resolution resolution, float x, float y, float width, float height) {
        float scaleX = resolution.getEndX() / 1920;
        float scaleY = resolution.getEndY() / 1080;

        this.position.x = resolution.getPosX() + (x * scaleX);
        this.position.y = resolution.getPosY() + (y * scaleY);
        this.width = width * scaleX;
        this.height = height * scaleY;

    }

    public void setScaledBounds(Resolution resolution) {
        float scaleX = resolution.getEndX() / 1920;
        float scaleY = resolution.getEndY() / 1080;

        this.position.x = resolution.getPosX() + (this.position.x * scaleX);
        this.position.y = resolution.getPosY() + (this.position.y * scaleY);
        this.width = this.width * scaleX;
        this.height = this.height * scaleY;

    }



    void onStartRender(float milliseconds) {}

    public T init(){
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        Resolution resolution;
        if (window != null) {
            resolution = window.getResolution();
        } else {
            resolution = new Resolution(0, 0, 1920, 1080);
        }


            this.setScaledBounds(resolution);

        return (T) this;
    }

    public abstract void onRender();


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IDelicate<?> delicate = (IDelicate<?>) o;
        return Objects.equals(id, delicate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
