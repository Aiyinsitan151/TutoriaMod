# 基础方块的注册

方块的注册过程和物品基本一致，但比物品稍微多了一些东西。例如：方块(Block)有对应的方块物品(BlockItem)
。以及方块放置后还要有对应的方块状态(BlockState)。
> 世界(level)中的方块和物品栏中的“方块”是截然不同的东西。世界(level)中的方块由BlockState表示，其行为由Block的实例定义。
> 而物品栏中的物品则是ItemStack，由Item控制。作为连接方块（Block）和物品（Item）这两个不同世界的桥梁，存在一个名为BlockItem的类。
> BlockItem是Item的子类，它有一个字段block，用于保存对其所代表的Block的引用。BlockItem定义了作为物品的“方块”的一些行为，比如右键点击如何放置方块。
> 一个Block可能没有对应的BlockItem。（例如，minecraft:water存在一个方块，但没有对应的物品。因此，无法将其作为物品保存在物品栏中。）

## 1.基础方块的注册

这里就不一步一步来了，和物品一样，使用DeferredRegister和RegistryObject来完成。

```java
public class ModBlocks {
    //1.创建方块注册器
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorial_mod.MOD_ID);

    //2.使用注册器注册方块和方块物品
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(Block.Properties.of()));
    public static final RegistryObject<BlockItem> EXAMPLE_BLOCK_ITEM = ModItems.ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new BlockItem.Properties()));

    //3.将注册器加载到MOD总线上
    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
```

实际上，大多数方块都会用对应的方块物品，所以，我们一般会封装一个方法来同时注册方块和方块。同时为没有对应物品的方块也写一个重载方法。这样完整的类就是：

```java
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
```

封装的函数，使得注册方块和方块物品的代码更加简洁。当然没有对应物品的方块我们无法直接获取，不过我们可以通过/setblock命令来直接在世界中放置。

## 2.BlockState,Model,Texture和Lang文件

### 1.BlockState

> 方块状态定义（BlockState,也称为方块状态模型调度）用于渲染方块，其中存在多种不同的变体（如门，可以是打开或关闭的状态）。
> 每个方块都有其自己的方块状态定义文件，该文件列出了所有现有的变体，并将它们链接到相应的模型。
> 方块也可以同时由多个不同的模型组成，称为“多部件”。然后，根据方块的方块状态来使用这些模型。
> 方块状态的定义以JSON文件的形式存储在资源包中，位于assets/<namespace>/blockstates文件夹下。

首先我们定义BlockState,在/resource/assets/tutorial_mod/blockstates/中
,创建两个名字分别为example_block_with_item.json和example_block_no_item.json的文件
以example_block_with_item.json为例

```json
{
  "variants": {
    "": {
      "model": "tutorial_mod:block/example_block_no_item"
    }
  }
}
```

这是最小的BlockState 文件，它定义了一个名为""的变体，该变体使用名为“tutorial_mod:block/example_block_no_item”的模型。

### 2.Model

#### blockModel
在/resource/assets/tutorial_mod/models/block/中新建一个名字为example_block_with_item.json的文件
```json
{
  "parent": "block/cube_all",
  "textures": {
    "all": "tutorial_mod:block/example_block_with_item"
  }
}
```

Model定义了方块的模型，它继承了block/cube_all，并定义了方块的纹理。
cube_all是BlockModel的预设，它定义了一个方块，其所有面都使用相同的纹理。

#### BlockItemModel
对于有对应方块物品的方块我们还需要设置物品的model，当然没有对应物品的方块就不需要了。
在/resource/assets/tutorial_mod/models/item/中新建一个名字为example_block_with_item.json的文件
```json
{
  "parent" : "tutorial_mod:block/example_block_with_item"
}
```
这里我们直接继承了方块的model，这样物品的model就会和方块的model一致。

### 3.Texture
在/resource/assets/tutorial_mod/textures/block/中新建一个名字为example_block_with_item.png的文件
这是方块的纹理，我们使用rgba格式的png图片，大小为16*16。

### 4.Lang文件
在/resource/assets/tutorial_mod/lang/下的语言文件中，添加本地化。这里就略过了，大家自己尝试