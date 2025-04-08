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
    // Вертикальный скролл
    private double scrollY = 0;
    private double maxScrollY = 0;
    private double scrollBarWidth = 6;
    private double scrollBarHeight = 6;
    private double scrollBarPosY = 0;
    private final static float SCROLL_OFFSET_Y = 8;

    // Горизонтальный скролл
    private double scrollX = 0;
    private double maxScrollX = 0;
    private double hScrollBarHeight = 6;
    private double hScrollBarWidth = 6;
    private double scrollBarPosX = 0;
    private final static float SCROLL_OFFSET_X = 6;

    // Состояние
    private boolean isScrollingY = false;
    private boolean isScrollingX = false;
    private boolean hovered = false;
    private boolean scrollBarYHovered = false;
    private boolean scrollBarXHovered = false;
    private double lastMouseX, lastMouseY;

    public ScrollPanel(Vector2d position, float width, float height) {
        super(position, width, height);
    }

    public <C extends IDelicate> C addContent(IDelicate delicate, String identifier) {
        C result = super.addChild(delicate, identifier);
        calculateScrollBounds();
        return result;
    }

    private void calculateScrollBounds() {
        double maxY = 0;
        double maxX = 0;

        for (Map<String, IDelicate> components : childDelicates.values()) {
            for (IDelicate child : components.values()) {
                double bottom = child.getPosY() + child.getHeight();
                double right = child.getPosX() + child.getWidth();

                if (bottom > maxY) maxY = bottom;
                if (right > maxX) maxX = right;
            }
        }

        // Вертикальные границы
        maxScrollY = Math.max(0, maxY - this.position.y - this.height);
        scrollBarHeight = this.height * (this.height / Math.max(this.height, maxY - this.position.y));
        scrollBarHeight = Math.max(20, scrollBarHeight);

        // Горизонтальные границы
        maxScrollX = Math.max(0, maxX - this.position.x - this.width);
        hScrollBarWidth = this.width * (this.width / Math.max(this.width, maxX - this.position.x));
        hScrollBarWidth = Math.max(20, hScrollBarWidth);
    }

    @Override
    public void onRender() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(),
                Minecraft.getMinecraft().displayWidth,
                Minecraft.getMinecraft().displayHeight);
        float scaleFactor = sr.getScaleFactor();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(
                (int)(this.position.x * scaleFactor),
                (int)((sr.getScaledHeight() - (this.position.y + this.height)) * scaleFactor),
                (int)(this.width * scaleFactor),
                (int)(this.height * scaleFactor)
        );

        GL11.glPushMatrix();
        GL11.glTranslated(-scrollX, -scrollY, 0); // Теперь сдвигаем и по X и по Y

        super.onRender();

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        renderScrollBars();
    }

    private void renderScrollBars() {
        if (maxScrollY > 0) {
            scrollBarPosY = this.position.y + (scrollY / maxScrollY) * (this.height - scrollBarHeight);

            GuiUtils.drawRectS( // Фон
                    this.position.x + this.width - scrollBarWidth + SCROLL_OFFSET_X,
                    this.position.y,
                    scrollBarWidth,
                    this.height,
                    new Color(50, 50, 50),
                    0.3f
            );

            Color thumbColorY = scrollBarYHovered ? new Color(180, 180, 180) : new Color(140, 140, 140);
            GuiUtils.drawRectS( // Ползунок
                    this.position.x + this.width - scrollBarWidth + SCROLL_OFFSET_X,
                    scrollBarPosY,
                    scrollBarWidth,
                    scrollBarHeight,
                    thumbColorY,
                    0.9f
            );
        }

        // Горизонтальный скроллбар
        if (maxScrollX > 0) {
            scrollBarPosX = this.position.x + (scrollX / maxScrollX) * (this.width - hScrollBarWidth);

            GuiUtils.drawRectS( // Фон
                    this.position.x,
                    this.position.y + this.height - hScrollBarHeight + SCROLL_OFFSET_Y,
                    this.width,
                    hScrollBarHeight,
                    new Color(50, 50, 50),
                    0.8f
            );

            Color thumbColorX = scrollBarXHovered ? new Color(180, 180, 180) : new Color(140, 140, 140);
            GuiUtils.drawRectS( // Ползунок
                    scrollBarPosX,
                    this.position.y + this.height - hScrollBarHeight + SCROLL_OFFSET_Y,
                    hScrollBarWidth,
                    hScrollBarHeight,
                    thumbColorX,
                    0.9f
            );
        }
    }

    @Override
    public void handleHover(float mouseX, float mouseY) {
        hovered = isMouseOver(mouseX, mouseY);
        scrollBarYHovered = isMouseOverVerticalScrollBar(mouseX, mouseY);
        scrollBarXHovered = isMouseOverHorizontalScrollBar(mouseX, mouseY);

        // Обработка колесика мыши (только вертикальный скролл)
        int wheel = Mouse.getDWheel();
        if (hovered && wheel != 0) {
            scrollY -= wheel * 0.4;
            clampScroll();
        }

        // Обработка перетаскивания
        if (Mouse.isButtonDown(0)) {
            if (scrollBarYHovered && !isScrollingY) {
                isScrollingY = true;
                lastMouseY = mouseY;
            }
            else if (scrollBarXHovered && !isScrollingX) {
                isScrollingX = true;
                lastMouseX = mouseX;
            }

            if (isScrollingY) {
                double deltaY = mouseY - lastMouseY;
                scrollY += deltaY * (maxScrollY / (this.height - scrollBarHeight));
                lastMouseY = mouseY;
            }

            if (isScrollingX) {
                double deltaX = mouseX - lastMouseX;
                scrollX += deltaX * (maxScrollX / (this.width - hScrollBarWidth));
                lastMouseX = mouseX;
            }

            clampScroll();
        } else {
            isScrollingY = false;
            isScrollingX = false;
        }

        // Передача событий дочерним элементам
        float relativeX = mouseX + (float)scrollX;
        float relativeY = mouseY + (float)scrollY;

        GL11.glPushMatrix();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(),
                Minecraft.getMinecraft().displayWidth,
                Minecraft.getMinecraft().displayHeight);
        float scaleFactor = sr.getScaleFactor();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(
                (int)(this.position.x * scaleFactor),
                (int)((sr.getScaledHeight() - (this.position.y + this.height)) * scaleFactor),
                (int)(this.width * scaleFactor),
                (int)(this.height * scaleFactor)
        );

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
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    private boolean isMouseOverVerticalScrollBar(float mouseX, float mouseY) {
        return maxScrollY > 0 &&
                mouseX >= this.position.x + this.width - scrollBarWidth + SCROLL_OFFSET_X &&
                mouseX <= this.position.x + this.width + SCROLL_OFFSET_X &&
                mouseY >= scrollBarPosY &&
                mouseY <= scrollBarPosY + scrollBarHeight;
    }

    private boolean isMouseOverHorizontalScrollBar(float mouseX, float mouseY) {
        return maxScrollX > 0 &&
                mouseX >= scrollBarPosX &&
                mouseX <= scrollBarPosX + hScrollBarWidth &&
                mouseY >= this.position.y + this.height - hScrollBarHeight + SCROLL_OFFSET_Y &&
                mouseY <= this.position.y + this.height + SCROLL_OFFSET_Y;
    }

    private void clampScroll() {
        scrollY = Math.max(0, Math.min(maxScrollY, scrollY));
        scrollX = Math.max(0, Math.min(maxScrollX, scrollX));
    }

    @Override
    public void handleClick(EClickType type) {
        super.handleClick(type);

        float relativeX = MainScreen.mouseX + (float)scrollX;
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

    @Override
    public boolean isMouseOver(float mouseX, float mouseY) {
        return mouseX >= this.position.x &&
                mouseX <= this.position.x + this.width &&
                mouseY >= this.position.y &&
                mouseY <= this.position.y + this.height;
    }
}