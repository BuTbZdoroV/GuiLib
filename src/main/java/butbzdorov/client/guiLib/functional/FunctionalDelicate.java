package butbzdorov.client.guiLib.functional;

import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.utils.SG;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class FunctionalDelicate<T extends FunctionalDelicate<T>> extends IDelicate {

    protected final Map<Class<? extends IDelicate>, Map<String, IDelicate>> childDelicates = new HashMap<>();
    public boolean isActive = true;
    public EClickType clickType;

    @Getter protected float posX;
    @Getter protected float posY;
    @Getter protected float endX;
    @Getter protected float endY;

    protected Consumer<T> onClickHandler;
    protected Consumer<T> onHoverHandler;

    // Добавление дочернего компонента
    public <C extends IDelicate> C addChild(IDelicate delicate, String identifier) {
        childDelicates
                .computeIfAbsent(delicate.getClass(), k -> new HashMap<>())
                .put(identifier, delicate);
        return (C) this;
    }

    // Получение дочернего компонента по типу и идентификатору
    public <C extends IDelicate> C getChildDelicate(Class<C> componentClass, String identifier) {
        Map<String, IDelicate> components = childDelicates.get(componentClass);
        return components != null ? componentClass.cast(components.get(identifier)) : null;
    }

    public <C extends IDelicate> C editChildComponent(Class<C> componentClass,String childKey, Consumer<C> componentModifier) {
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

    // Рендеринг всех дочерних компонентов
    public void onRender() {
        for (Map<String, IDelicate> components : childDelicates.values()) {
            for (IDelicate child : components.values()) {
                if (child instanceof FunctionalDelicate && !((FunctionalDelicate<?>) child).isActive) {
                    continue;
                }
                child.onRender();
            }
        }
    }

    public boolean isMouseOver(float mouseX, float mouseY) {
        return mouseX >= this.posX && mouseX <= this.posX + this.endX &&
                mouseY >= this.posY && mouseY <= this.posY + this.endY;
    }

    // Установка обработчика для наведения
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
            ((Consumer<T>) onClickHandler).accept((T) this);
        }
    }

    // Методы для обработки кнопок
    public void onClickRightButton() {}
    public void onReleaseRightButton() {}
    public void onHoldRightButton() {}
    public void onClickMiddleButton() {}
    public void onReleaseMiddleButton() {}
    public void onHoldMiddleButton() {}
    public void onScrollUp() {}
    public void onScrollDown() {}
    public void onMouseMove(int x, int y) {}
    public void onMouseEnter() {}
    public void onMouseExit() {}
}
