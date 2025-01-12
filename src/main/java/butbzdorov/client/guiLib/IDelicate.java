package butbzdorov.client.guiLib;

import java.util.UUID;

public abstract class IDelicate {

    protected String id;

    public IDelicate() {
        this.id = UUID.randomUUID().toString();
    }

    void onStartRender(float milliseconds) {}

    public abstract void onRender();

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
}
