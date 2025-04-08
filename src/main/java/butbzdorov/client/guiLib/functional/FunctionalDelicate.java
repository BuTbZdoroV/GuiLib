package butbzdorov.client.guiLib.functional;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.window.IWindow;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Vector2d;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class FunctionalDelicate<T extends FunctionalDelicate<T>> extends IDelicate {

    protected final Map<Class<? extends IDelicate>, Map<String, IDelicate>> childDelicates = new HashMap<>();
    public boolean isActive = true;
    public EClickType clickType;

    protected Consumer<T> onClickHandler;
    protected Consumer<T> onHoverHandler;

    public FunctionalDelicate(IWindow window, Vector2d position, float endX, float endY) {
        super(window, position, endX, endY);
    }

    public FunctionalDelicate(Vector2d position, float endX, float endY) {
        super(position, endX, endY);
    }

    public FunctionalDelicate() {
        super();
    }

    // Добавление дочернего компонента
    public <C extends IDelicate> C addChild(IDelicate delicate, String identifier) {

        adjustChildPositions(delicate, this.position.x, this.position.y);

        childDelicates
                .computeIfAbsent(delicate.getClass(), k -> new HashMap<>())
                .put(identifier, delicate);

        return (C) this;
    }

    public <C extends IDelicate> C getChildDelicate(Class<C> componentClass, String identifier) {
        Map<String, IDelicate> components = childDelicates.get(componentClass);
        return components != null ? componentClass.cast(components.get(identifier)) : null;
    }

    public <C extends IDelicate> C editChildComponent(Class<C> componentClass, String childKey, Consumer<C> componentModifier) {
        if (childDelicates.isEmpty() || childKey == null || componentModifier == null) {
            return (C) this;
        }

        IDelicate child = getChildDelicate(componentClass, childKey);

        if (child != null) {
            try {
                componentModifier.accept((C) child);
            } catch (ClassCastException e) {
                System.out.println("Ошибка приведения типа для ключа: " + childKey);
            }
        } else {
            System.out.println("Дочерний компонент с ключом " + childKey + " не найден.");
        }

        return (C) this;
    }

    public void onRender() {
        GL11.glPushMatrix();
        GL11.glTranslatef(0,0,zLevel);
        for (Map<String, IDelicate> components : childDelicates.values()) {
            for (IDelicate child : components.values()) {
                if (child instanceof FunctionalDelicate && !((FunctionalDelicate<?>) child).isActive) {
                    continue;
                }
                child.onRender();
            }
        }
        GL11.glPopMatrix();
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

    public T onHover(Consumer<T> action) {
        onHoverHandler = action;
        return (T) this;
    }

    // Обработка наведения
    public void handleHover(float mouseX, float mouseY) {
        if (onHoverHandler != null && isMouseOver(mouseX, mouseY)) {
            onHoverHandler.accept((T) this);
        }
    }

    // Установка обработчика клика
    public T onClickMouse(Consumer<T> buttonModifier) {
        this.onClickHandler = buttonModifier;
        return (T) this;
    }

    // Обработка клика
    public void handleClick(EClickType type) {
        this.clickType = type;
        if (onClickHandler != null) {
            (onClickHandler).accept((T) this);
        }
    }
}
