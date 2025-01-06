package butbzdorov.client.guiLib;

public interface IDelicate {

    default void onStartRender(float milliseconds) {}

    void onRender();

}
