package butbzdorov.client.guiLib.functional;

import butbzdorov.client.guiLib.Container;
import butbzdorov.client.guiLib.DelicateController;
import butbzdorov.client.guiLib.IDelicate;
import butbzdorov.client.guiLib.delicates.Button;
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
        }

        functionalDelicates.isEmpty();

        for (Button button : Container.buttons) {
            button.onRender();
            button.handleHover(event.mouseX, event.mouseY);

            if (button.isMouseOver(event.mouseX, event.mouseY)) {
                handleMouseEvents(button, event);
            }
        }
    }

    private void handleMouseEvents(Button button, GuiScreenEvent.DrawScreenEvent.Post event) {

        if (Mouse.isButtonDown(0)) {
            if (!leftMouseDown) {
                leftMouseDown = true;
                button.handleClick(EClickType.LEFT_MOUSE_PRESS);
            }
        } else {
            if (leftMouseDown) {
                leftMouseDown = false;
                button.handleClick(EClickType.LEFT_MOUSE_RELEASE);
            }
        }

        if (Mouse.isButtonDown(1)) {
            if (!rightMouseDown) {
                rightMouseDown = true;
                button.handleClick(EClickType.RIGHT_MOUSE_PRESS);
            }
        } else {
            if (rightMouseDown) {
                rightMouseDown = false;
                button.handleClick(EClickType.RIGHT_MOUSE_RELEASE);
            }
        }

        if (Mouse.isButtonDown(2)) {
            if (!middleMouseDown) {
                middleMouseDown = true;
                button.handleClick(EClickType.MIDDLE_MOUSE_PRESS);
            }
        } else {
            if (middleMouseDown) {
                middleMouseDown = false;
                button.handleClick(EClickType.MIDDLE_MOUSE_RELEASE);
            }
        }
    }

}
