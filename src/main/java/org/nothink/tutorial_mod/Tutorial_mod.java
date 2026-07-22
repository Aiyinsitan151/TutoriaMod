package org.nothink.tutorial_mod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.nothink.tutorial_mod.init.ModCreativeModeTabs;
import org.nothink.tutorial_mod.init.ModItems;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Tutorial_mod.MOD_ID)
public class Tutorial_mod {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "tutorial_mod";

    public Tutorial_mod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //region ModEventBus
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        //end region

    }
}

