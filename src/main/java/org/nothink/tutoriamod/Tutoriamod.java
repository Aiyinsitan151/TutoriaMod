package org.nothink.tutoriamod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Tutoriamod.MODID)
public class Tutoriamod {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "tutoriamod";

    public Tutoriamod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    }
}

