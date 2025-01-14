package butbzdorov.client.guiLib.window;

import butbzdorov.client.gui.MainScreen;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.functional.EClickType;
import butbzdorov.client.guiLib.functional.FunctionalDelicate;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;

public abstract class Window implements IWindow {

    public static final List<IWindow> Windows = new ArrayList<>();
    public static IWindow selectedWindow;

    public final List<IDelicate> delicates = new ArrayList<>();
    protected final Minecraft mc = Minecraft.getMinecraft();

    public Resolution resolution = new Resolution(0, 0, 1920, 1080);
    protected final MainScreen mainScreen;

    public boolean isVisible = true;

    protected Window(MainScreen mainScreen, Resolution resolution) {
        this.mainScreen = mainScreen;
        this.resolution = resolution;

        Windows.add(this);
    }

    protected Window(MainScreen mainScreen) {
        this.mainScreen = mainScreen;

        Windows.add(this);
    }

    @Override
    public String getWindowId() {
        return "default";
    }

    @Override
    public List<? extends IDelicate> getDelicates() {
        return delicates;
    }

    @Override
    public void initWindow() {
        List<IDelicate> delicates = (List<IDelicate>) this.getDelicates();
            for (IDelicate delicate : delicates) {
                if (delicate instanceof FunctionalDelicate) {
                    FunctionalDelicate<?> funcDelicate = (FunctionalDelicate<?>) delicate;
                    if (funcDelicate.window != null) {
                        funcDelicate.setPosX(funcDelicate.getPosX() + funcDelicate.window.getResolution().getPosX());
                        funcDelicate.setPosY(funcDelicate.getPosY() + funcDelicate.window.getResolution().getPosY());
                    }
                }
        }
    }

    @Override
    public Resolution getResolution() {
        return resolution;
    }


    @Override
    public void renderWindow(float mouseX, float mouseY) {
            if (!isVisible) return;

            if (selectedWindow != null) {
                System.out.println(selectedWindow.getWindowId());
            }
            List<IDelicate> delicates = (List<IDelicate>) this.getDelicates();
            for (IDelicate delicate : delicates) {
                delicate.onRender();
                if (delicate instanceof FunctionalDelicate) {
                    FunctionalDelicate<?> funcDelicate = (FunctionalDelicate<?>) delicate;
                    funcDelicate.handleHover(mouseX, mouseY);
                    if (funcDelicate.isMouseOver(mouseX, mouseY)) {
                        handleMouseEvents(funcDelicate);
                    }
                }
            }
    }

    private static boolean leftMouseDown = false;
    private static boolean rightMouseDown = false;
    private static boolean middleMouseDown = false;

    public boolean isMouseOverWindow(float mouseX, float mouseY) {
        return mouseX >= this.resolution.getPosX() && mouseX <= this.resolution.getPosX() + this.resolution.getEndX() &&
                mouseY >= this.resolution.getPosY() && mouseY <= this.resolution.getPosY() + this.resolution.getEndY();
    }

    private void handleMouseEvents(FunctionalDelicate functionalDelicate) {

        if (Mouse.isButtonDown(0)) {
            if (!leftMouseDown) {
                selectedWindow = this;
                leftMouseDown = true;
                functionalDelicate.handleClick(EClickType.LEFT_MOUSE_CLICK);
            }
            functionalDelicate.handleClick(EClickType.LEFT_MOUSE_PRESS);
        } else {
            if (leftMouseDown) {
                leftMouseDown = false;
                functionalDelicate.handleClick(EClickType.LEFT_MOUSE_RELEASE);
            }
        }

        if (Mouse.isButtonDown(1)) {
            if (!rightMouseDown) {
                rightMouseDown = true;
                functionalDelicate.handleClick(EClickType.RIGHT_MOUSE_PRESS);
            }
        } else {
            if (rightMouseDown) {
                rightMouseDown = false;
                functionalDelicate.handleClick(EClickType.RIGHT_MOUSE_RELEASE);
            }
        }

        if (Mouse.isButtonDown(2)) {
            if (!middleMouseDown) {
                middleMouseDown = true;
                functionalDelicate.handleClick(EClickType.MIDDLE_MOUSE_PRESS);
            }
        } else {
            if (middleMouseDown) {
                middleMouseDown = false;
                functionalDelicate.handleClick(EClickType.MIDDLE_MOUSE_RELEASE);
            }
        }
    }

    protected void drawBackground(ResourceLocation background) {
        Image image = new Image(background, resolution.getPosX(), resolution.getPosY(), resolution.getEndX(), resolution.getEndY());
        delicates.add(image);

    }

}
