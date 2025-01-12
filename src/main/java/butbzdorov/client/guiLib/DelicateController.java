package butbzdorov.client.guiLib;

import butbzdorov.client.guiLib.annotation.Delicate;
import butbzdorov.client.guiLib.delicates.Button;
import butbzdorov.client.guiLib.functional.EClickType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DelicateController {

    public static final Set<IDelicate> delicates = new HashSet<>();

    public static void registerComponent(IDelicate delicate) {
        System.out.println(delicate.getClass().getName());
        delicates.add(delicate);
    }
}
