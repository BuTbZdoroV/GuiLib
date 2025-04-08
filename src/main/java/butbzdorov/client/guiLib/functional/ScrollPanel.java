package butbzdorov.client.guiLib.functional;

import butbzdorov.client.gui.MainScreen;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.utils.GuiUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import javax.vecmath.Vector2d;
import java.util.Map;

import static butbzdorov.client.gui.MainScreen.mouseX;
import static butbzdorov.client.gui.MainScreen.mouseY;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScrollPanel extends FunctionalDelicate<ScrollPanel> {
    private double scrollY = 0;
    private double maxScroll = 0;
    private boolean isScrolling = false;
    private double scrollBarWidth = 6;
    private double scrollBarHeight = 0;
    private double scrollBarPosY = 0;
    private boolean hovered = false;
    private boolean scrollBarHovered = false;
    private double lastMouseY;

    public ScrollPanel(Vector2d position, float width, float height) {
        super(position, width, height);
    }

    public <C extends IDelicate> C addContent(IDelicate delicate, String identifier) {
        C result = super.addChild(delicate, identifier);

        adjustChildPositions(delicate, this.position.x, this.position.y);

        calculateScrollBounds();
        return result;
    }

    private void adjustChildPositions(IDelicate parent, double offsetX, double offsetY) {
        parent.setPosX(parent.getPosX() + offsetX);
        parent.setPosY(parent.getPosY() + offsetY);

        if (parent instanceof FunctionalDelicate) {
            FunctionalDelicate<?> fd = (FunctionalDelicate<?>) parent;
            for (Map<String, IDelicate> components : fd.childDelicates.values()) {
                for (IDelicate child : components.values()) {
                    adjustChildPositions(child, offsetX, offsetY);
                }
            }
        }
    }

    private void calculateScrollBounds() {
        double maxY = 0; // Максимальная нижняя граница среди всех элементов

        for (Map<String, IDelicate> components : childDelicates.values()) {
            for (IDelicate child : components.values()) {
                double bottom = child.getPosY() + child.getHeight();
                if (bottom > maxY) {
                    maxY = bottom;
                }
            }
        }

        // Вычитаем высоту самой панели, чтобы получить максимальный скролл
        maxScroll = Math.max(0, maxY - this.position.y - this.height);
        scrollBarHeight = this.height * (this.height / Math.max(this.height, maxY - this.position.y));
        scrollBarHeight = Math.max(20, scrollBarHeight);
    }

    @Override
    public void onRender() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        float scaleFactor = sr.getScaleFactor();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(
                (int)(this.position.x * scaleFactor),
                (int)((sr.getScaledHeight() - (this.position.y + this.height)) * scaleFactor),
                (int)(this.width * scaleFactor),
                (int)(this.height * scaleFactor)
        );

        GL11.glPushMatrix();
        // Только вертикальный сдвиг, так как позиции уже корректные
        GL11.glTranslated(0, -scrollY, 0);

        super.onRender();

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        renderScrollBar();
    }

    private void renderScrollBar() {
        if (maxScroll <= 0) return;

        scrollBarPosY = this.position.y + (scrollY / maxScroll) * (this.height - scrollBarHeight);

        // Фон скроллбара
        GuiUtils.drawRectS(
                this.position.x + this.width - scrollBarWidth,
                this.position.y,
                scrollBarWidth,
                this.height,
                new Color(50, 50, 50),
                0.3f
        );

        // Ползунок
        Color thumbColor = scrollBarHovered ? new Color(180, 180, 180) : new Color(140, 140, 140);
        GuiUtils.drawRectS(
                this.position.x + this.width - scrollBarWidth,
                scrollBarPosY,
                scrollBarWidth,
                scrollBarHeight,
                thumbColor,
                0.9f
        );
    }

    @Override
    public void handleHover(float mouseX, float mouseY) {
        hovered = isMouseOver(mouseX, mouseY);
        scrollBarHovered = isMouseOverScrollBar(mouseX, mouseY);

        int wheel = Mouse.getDWheel();
        if (hovered && wheel != 0) {
            scrollY -= wheel * 0.4;
            clampScroll();
        }

        if (Mouse.isButtonDown(0)) {
            if (scrollBarHovered && !isScrolling) {
                isScrolling = true;
                lastMouseY = mouseY;
            } else if (isScrolling) {
                double deltaY = mouseY - lastMouseY;
                scrollY += deltaY * (maxScroll / (this.height - scrollBarHeight));
                lastMouseY = mouseY;
                clampScroll();
            }
        } else {
            isScrolling = false;
        }

        // Координаты относительно панели (уже учитывают position.x/y)
        float relativeX = mouseX;
        float relativeY = mouseY + (float)scrollY;

        for (Map<String, IDelicate> components : childDelicates.values()) {
            for (IDelicate child : components.values()) {
                if (child instanceof FunctionalDelicate) {
                    FunctionalDelicate<?> delicate = (FunctionalDelicate<?>) child;
                    boolean isOver = delicate.isMouseOver(relativeX, relativeY) && hovered;
                    if (isOver) {
                        delicate.handleHover(relativeX, relativeY);
                    }
                }
            }
        }
    }

    @Override
    public void handleClick(EClickType type) {
        super.handleClick(type);

        float relativeX = MainScreen.mouseX;
        float relativeY = MainScreen.mouseY + (float)scrollY;

        for (Map<String, IDelicate> components : childDelicates.values()) {
            for (IDelicate child : components.values()) {
                if (child instanceof FunctionalDelicate) {
                    FunctionalDelicate<?> delicate = (FunctionalDelicate<?>) child;
                    if (delicate.isMouseOver(relativeX, relativeY)) {
                        delicate.handleClick(type);
                    }
                }
            }
        }
    }

    private boolean isMouseOverScrollBar(float mouseX, float mouseY) {
        return mouseX >= this.position.x + this.width - scrollBarWidth &&
                mouseX <= this.position.x + this.width &&
                mouseY >= scrollBarPosY &&
                mouseY <= scrollBarPosY + scrollBarHeight;
    }

    private void clampScroll() {
        scrollY = Math.max(0, Math.min(maxScroll, scrollY));
    }

    @Override
    public boolean isMouseOver(float mouseX, float mouseY) {
        return mouseX >= this.position.x &&
                mouseX <= this.position.x + this.width &&
                mouseY >= this.position.y &&
                mouseY <= this.position.y + this.height;
    }
}