package org.nothink.tutorial_mod.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.nothink.tutorial_mod.Tutorial_mod;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> EXAMPLE_ITEM;

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial_mod.MOD_ID);
        EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties()));

    }

    //用于在主类登记注册器
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    //注册默认属性的物品
    public static RegistryObject<Item> registerItem(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }



}
