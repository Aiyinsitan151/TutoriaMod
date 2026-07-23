package org.nothink.tutorial_mod.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.nothink.tutorial_mod.Tutorial_mod;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryObject<Block> EXAMPLE_BLOCK_WITH_ITEM;
    public static final RegistryObject<Block> EXAMPLE_BLOCK_NO_ITEM;
    public static DeferredRegister<Block> BLOCKS;

    static {
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorial_mod.MOD_ID);
        EXAMPLE_BLOCK_WITH_ITEM = registerBlockWithItem("example_block_with_item", () -> new Block(Block.Properties.of()));
        EXAMPLE_BLOCK_NO_ITEM = registerBlockNoItem("example_block_no_item", () -> new Block(Block.Properties.of()));

    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

    //注册没有对应物品的方块
    private static <T extends Block> RegistryObject<Block> registerBlockNoItem(String name, Supplier<T> factory) {
        return BLOCKS.register(name, factory);
    }

    //注册有对应物品的方块(默认物品属性）
    private static <T extends Block> RegistryObject<Block> registerBlockWithItem(String name, Supplier<T> factory) {
        RegistryObject<Block> block = BLOCKS.register(name, factory);
        registerBlockItem(name, block);
        return block;
    }

    //注册有对应物品的方块(自定义物品属性）)
    private static <T extends Block> RegistryObject<Block> registerBlockWithItem(String name, Supplier<T> factory, BlockItem.Properties properties) {
        RegistryObject<Block> block = BLOCKS.register(name, factory);
        registerBlockItem(name, block, properties);
        return block;
    }

    // 注册方块物品（默认属性）
    private static <T extends BlockItem> RegistryObject<BlockItem> registerBlockItem(String name, RegistryObject<Block> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // 注册方块物品（自定义属性）
    private static <T extends BlockItem> RegistryObject<BlockItem> registerBlockItem(String name, RegistryObject<Block> block, BlockItem.Properties properties) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }


}
