package butbzdorov.client.guiLib.screen;

import butbzdorov.client.guiLib.IDelicate;

import java.util.List;

public interface IScreen {
    String getScreenId(); // Уникальный ID экрана
    List<? extends IDelicate> getDelicates(); // Список деликейтов для экрана
    void renderScreen(); // Уникальный идентификатор экрана
}
