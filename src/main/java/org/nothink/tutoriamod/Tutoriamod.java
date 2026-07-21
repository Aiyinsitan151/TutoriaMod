package org.nothink.tutoriamod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.nothink.tutoriamod.init.ModItems;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Tutoriamod.MOD_ID)
public class Tutoriamod {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "tutoriamod";

    public Tutoriamod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(modEventBus);

    }
}

