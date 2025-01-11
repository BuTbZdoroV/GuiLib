package butbzdorov.client.guiLib.functional;

import butbzdorov.client.guiLib.Container;
import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.delicates.Text;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.lwjgl.input.Mouse;

import java.util.HashSet;
import java.util.Set;

public class FunctionalDelicateController {

    public static final Set<FunctionalDelicate> functionalDelicates = new HashSet<>();

    private static boolean leftMouseDown = false;
    private static boolean rightMouseDown = false;
    private static boolean middleMouseDown = false;

    @SubscribeEvent
    public void onGuiRenderPost(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (!(event.gui instanceof IDelicate)) {
            return;
        }
        for (IDelicate delicate : DelicateController.delicates) {
            delicate.onRender();
            if (delicate instanceof FunctionalDelicate) {
                FunctionalDelicate funcDelicate = (FunctionalDelicate) delicate;
                funcDelicate.handleHover(event.mouseX, event.mouseY);

                if (funcDelicate.isMouseOver(event.mouseX, event.mouseY)) {
                    if (funcDelicate instanceof Text){
                        Text text = (Text) funcDelicate;
                        System.out.println(text.getText());
                    }
                    handleMouseEvents(funcDelicate, event);
                }
            }
        }
    }

    private void handleMouseEvents(FunctionalDelicate functionalDelicate, GuiScreenEvent.DrawScreenEvent.Post event) {

        if (Mouse.isButtonDown(0)) {
            if (!leftMouseDown) {
                leftMouseDown = true;
                functionalDelicate.handleClick(EClickType.LEFT_MOUSE_PRESS);
            }
        } else {
            if (leftMouseDown) {
                leftMouseDown = false;
                functionalDelicate.handleClick(EClickType.LEFT_MOUSE_RELEASE);
            }
        }

        if (Mouse.isButtonDown(1)) {
            if (!rightMouseDown) {
                rightMouseDown = true;
                functionalDelicate.handleClick(EClickType.RIGHT_MOUSE_PRESS);
            }
        } else {
            if (rightMouseDown) {
                rightMouseDown = false;
                functionalDelicate.handleClick(EClickType.RIGHT_MOUSE_RELEASE);
            }
        }

        if (Mouse.isButtonDown(2)) {
            if (!middleMouseDown) {
                middleMouseDown = true;
                functionalDelicate.handleClick(EClickType.MIDDLE_MOUSE_PRESS);
            }
        } else {
            if (middleMouseDown) {
                middleMouseDown = false;
                functionalDelicate.handleClick(EClickType.MIDDLE_MOUSE_RELEASE);
            }
        }
    }

}
