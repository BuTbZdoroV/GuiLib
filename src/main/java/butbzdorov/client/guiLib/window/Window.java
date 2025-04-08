package butbzdorov.client.guiLib.window;

import butbzdorov.client.gui.MainScreen;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Image;
import butbzdorov.client.guiLib.functional.EClickType;
import butbzdorov.client.guiLib.functional.FunctionalDelicate;
import butbzdorov.client.guiLib.functional.ScrollPanel;
import butbzdorov.client.guiLib.utils.Math.Vector2D;
import butbzdorov.client.guiLib.utils.SG;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Window implements IWindow {

    public static final List<IWindow> Windows = new ArrayList<>();

    public final List<IDelicate> delicates = new ArrayList<>();
    protected final Minecraft mc = Minecraft.getMinecraft();

    private Resolution resolution = new Resolution(0, 0, 1920, 1080);
    @Getter
    public int layer = 0;
    protected final MainScreen mainScreen;

    public boolean isVisible = true;

    private Vector2D position;
    private boolean dragging = false;
    private Vector2D dragOffset = new Vector2D(0, 0);

    protected Window(MainScreen mainScreen, Resolution resolution) {
        this.mainScreen = mainScreen;
        this.resolution = resolution;

        Windows.add(this);
    }

    protected Window(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        this.position = new Vector2D(0, 0);
        Windows.add(this);
    }

    @Override
    public String getWindowId() {
        return "default";
    }

    public List<IDelicate> getDelicates() {
        return delicates;
    }

    @Override
    public void initWindow() {
        for (IDelicate delicate : delicates) {
            if (delicate != null) {
                delicate.init();
                if (delicate instanceof FunctionalDelicate) {
                    FunctionalDelicate<?> funcDelicate = (FunctionalDelicate<?>) delicate;
                }
            }
        }
    }

    @Override
    public Resolution getResolution() {
        return new Resolution(SG.get(resolution.getPosX()), SG.get(resolution.getPosY()),
                SG.get(resolution.getEndX()), SG.get(resolution.getEndY()));
    }


    @Override
    public void renderWindow(float mouseX, float mouseY, float frametime) {
        if (!isVisible) return;

        for (IDelicate delicate : delicates) {
            if (delicate != null) {
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
    }



    public <T extends ScrollPanel> T addScrollPanel(T panel) {
        addDelicate(panel);
        return panel;
    }

    private static boolean leftMouseDown = false;
    private static boolean rightMouseDown = false;
    private static boolean middleMouseDown = false;

    public boolean isMouseOverWindow(float mouseX, float mouseY) {
        return mouseX >= this.resolution.getPosX() && mouseX <= this.resolution.getPosX() + this.resolution.getEndX() &&
                mouseY >= this.resolution.getPosY() && mouseY <= this.resolution.getPosY() + this.resolution.getEndY();
    }

    private void clickOnLayer() {
        if (Mouse.isButtonDown(0)) {

        }
    }

    private void handleMouseEvents(FunctionalDelicate functionalDelicate) {

        if (Mouse.isButtonDown(0)) {
            if (!leftMouseDown) {
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

    public <T extends IDelicate> T addDelicate(T delicate) {
        Objects.requireNonNull(delicate, "Delicate cannot be null");

        if (delicate.getId() == null || delicate.getId().isEmpty()) {
            throw new IllegalArgumentException("Delicate must have non-empty ID");
        }

        removeDelicate(delicate);
        delicates.add(delicate);
        return delicate;
    }

    public boolean removeDelicate(IDelicate delicate) {
        return delicates.removeIf(d -> d.equals(delicate));
    }

    protected <T extends IDelicate> T getDelicate(int index) {
        return (T) this.delicates.get(index);
    }

    protected <T extends IDelicate> T getDelicate(IDelicate iDelicate) {
        return (T) this.delicates.stream().filter(delicate -> delicate.equals(iDelicate)).findFirst().orElse(null);
    }

    protected <T extends IDelicate> T getDelicate(String delicateUUID) {
        return (T) this.delicates.stream().filter(delicate -> delicate.getId() == delicateUUID).findFirst().orElse(null);
    }

    protected void clean() {
        delicates.clear();
    }

    protected void drawBackground(ResourceLocation background) {
        Image image = new Image(background, resolution.getPosX(), resolution.getPosY(), resolution.getEndX(), resolution.getEndY());
        delicates.add(image);
    }


}
