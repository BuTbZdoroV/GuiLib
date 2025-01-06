package butbzdorov.server;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;


public class ServerEvents {


    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {

    }

    @SubscribeEvent
    public void onWorldTickEvent(TickEvent.WorldTickEvent event) {

    }


    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {

    }

    @SubscribeEvent
    public void onLivingHurtEvent(LivingHurtEvent event) {

    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
    }



}