上一节我们创建了基本物品，但是我们获取物品的方式是通过指令获取的。这一节我们将创建自定义的创造模式物品栏，以便我们查看并获取我们注册的物品。

创造模式物品栏的创建方式和物品基本一致。
## 1.创建创造模式物品栏注册器
```java
public class ModCreativeModeTabs {
    public static  final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tutorial_mod.MOD_ID);
}

```
## 2.使用注册器注册创造模式物品栏
```java
public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tutorial_mod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB
            = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.EXAMPLE_ITEM.get().getDefaultInstance())
                    .title(Component.translatable("tab.example"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.EXAMPLE_ITEM.get());
                    })
                    .build());
}

```
可以看到创造模式物品栏的创建比物品要麻烦一点，接下来我们对参数进行介绍
1. icon  决定了创造模式物品栏的图标
2. title 决定了创造模式物品栏的标题，同时用于本地化
3. displayItems 向创造模式物品栏中添加物品
以上三个只是最小实现，我们可以添加更多的参数来扩展创造模式物品栏的功能，如自定义背景等，大家自行探索。
## 3.将注册器加载到MOD总线上
```java
public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tutorial_mod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB
            = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.EXAMPLE_ITEM.get().getDefaultInstance())
                    .title(Component.translatable("tab.example"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.EXAMPLE_ITEM.get());
                    })
                    .withLabelColor(0xffffff)
                    .withSearchBar()
                    .withBackgroundLocation(new ResourceLocation("tutorial_mod:textures/gui/container/creative_inventory/tab_example.png"))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
```
## 4.本地化
这里就不带着大家一步一步来了，大家可以根据创建基本物品那一节来自己尝试本地化