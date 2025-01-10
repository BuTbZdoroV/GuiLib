package butbzdorov.client.guiLib.functional;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class FunctionalDelicate implements IDelicate {

    protected final Map<Class<? extends IDelicate>, Map<String, IDelicate>> childDelicates = new HashMap<>();
    public boolean isActive = true;

    public FunctionalDelicate addChild(IDelicate delicate, String identifier) {
        Class<? extends IDelicate> componentClass = delicate.getClass();
        childDelicates
                .computeIfAbsent(componentClass, k -> new HashMap<>())
                .put(identifier, delicate);
        return this;
    }

    public <T extends IDelicate> T getChildDelicate(Class<T> componentClass, String identifier) {
        Map<String, IDelicate> components = childDelicates.get(componentClass);
        if (components != null) {
            return componentClass.cast(components.get(identifier));
        }
        return null;
    }

    public <T extends IDelicate> FunctionalDelicate editChildComponent(Class<T> componentClass,String childKey, Consumer<T> componentModifier) {
        if (childDelicates.isEmpty() || childKey == null || componentModifier == null) {
            return this;
        }

        IDelicate child = getChildDelicate(componentClass, childKey);

        if (child != null) {
            try {
                componentModifier.accept((T) child);
            } catch (ClassCastException e) {
                System.out.println("Ошибка приведения типа для ключа: " + childKey);
            }
        } else {
            System.out.println("Дочерний компонент с ключом " + childKey + " не найден.");
        }

        return this;
    }


    public void onRender() {
        for (Map<String, IDelicate> components : childDelicates.values()) {
            for (IDelicate child : components.values()) {
                if (child instanceof FunctionalDelicate && !((FunctionalDelicate) child).isActive) {
                    continue;
                }

                child.onRender();
            }
        }
    }

    public FunctionalDelicate onClickButton(Consumer<Button> buttonModifier) {
        return this;
    }
    public FunctionalDelicate onClickLeftButton(Consumer<Button> buttonModifier) {
        return this;
    }
    public FunctionalDelicate onReleaseLeftButton(Consumer<Button> buttonModifier) {
        return this;
    }
    public FunctionalDelicate onHoldLeftButton(Consumer<Button> buttonModifier) {
        return this;
    }
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
