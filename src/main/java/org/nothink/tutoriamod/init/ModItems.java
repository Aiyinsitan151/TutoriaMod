package org.nothink.tutoriamod.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.nothink.tutoriamod.Tutoriamod;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutoriamod.MOD_ID);




    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
