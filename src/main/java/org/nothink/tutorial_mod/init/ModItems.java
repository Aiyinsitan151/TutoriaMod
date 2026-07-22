package org.nothink.tutorial_mod.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.nothink.tutorial_mod.Tutorial_mod;

public class ModItems {
    //创建物品注册器
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial_mod.MOD_ID);

    //进行物品的注册
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties()));

    //用于在主类登记注册器
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
