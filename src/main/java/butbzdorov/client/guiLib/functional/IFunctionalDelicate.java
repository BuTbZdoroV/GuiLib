package butbzdorov.client.guiLib.functional;

import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Button;

import java.util.function.Consumer;

public interface IFunctionalDelicate extends IDelicate {

    default IFunctionalDelicate onClickButton(Consumer<Button> buttonModifier) {
        return this;
    }

    // Левая кнопка мыши
    default IFunctionalDelicate onClickLeftButton(Consumer<Button> buttonModifier) {
        return this;
    }
    default IFunctionalDelicate onReleaseLeftButton(Consumer<Button> buttonModifier) {
        return this;
    }
    default IFunctionalDelicate onHoldLeftButton(Consumer<Button> buttonModifier) {
        return this;
    }

    // Правая кнопка мыши
    default void onClickRightButton() {}
    default void onReleaseRightButton() {}
    default void onHoldRightButton() {}

    // Средняя кнопка мыши (колесо)
    default void onClickMiddleButton() {}
    default void onReleaseMiddleButton() {}
    default void onHoldMiddleButton() {}

    // Прокрутка колесика
    default void onScrollUp() {}
    default void onScrollDown() {}

    // Перемещение мыши
    default void onMouseMove(int x, int y) {}
    default void onMouseEnter() {}
    default void onMouseExit() {}
}
