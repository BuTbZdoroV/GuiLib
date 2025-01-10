package butbzdorov.client.guiLib.functional;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Button;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public abstract class FunctionalDelicate implements IDelicate {

    protected final Set<IDelicate> childDelicates = new HashSet<>();
    public boolean isActive = true;

    public FunctionalDelicate onClickButton(Consumer<Button> buttonModifier) {
        return this;
    }

    // Левая кнопка мыши
    public FunctionalDelicate onClickLeftButton(Consumer<Button> buttonModifier) {
        return this;
    }

    public FunctionalDelicate onReleaseLeftButton(Consumer<Button> buttonModifier) {
        return this;
    }

    public FunctionalDelicate onHoldLeftButton(Consumer<Button> buttonModifier) {
        return this;
    }

    // Правая кнопка мыши
    public void onClickRightButton() {}

    public void onReleaseRightButton() {}

    public void onHoldRightButton() {}

    // Средняя кнопка мыши (колесо)
    public void onClickMiddleButton() {}

    public void onReleaseMiddleButton() {}

    public void onHoldMiddleButton() {}

    // Прокрутка колесика
    public void onScrollUp() {}

    public void onScrollDown() {}

    // Перемещение мыши
    public void onMouseMove(int x, int y) {}

    public void onMouseEnter() {}

    public void onMouseExit() {}
}
