package com.emeraldblade;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(EmeraldBladeMod.MOD_ID)
public class EmeraldBladeMod {
    public static final String MOD_ID = "emeraldblade";

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // Iron is 250 uses; Emerald Blade is slightly better at 350. Other stats match iron.
    public static final ForgeTier EMERALD_TIER = new ForgeTier(
            2, 350, 6.0F, 2.0F, 14,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(Items.EMERALD));

    public static final RegistryObject<Item> EMERALD_BLADE = ITEMS.register("emerald_blade",
            () -> new SwordItem(EMERALD_TIER, 3, -2.4F, new Item.Properties()));

    public EmeraldBladeMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        bus.addListener(this::addToCreativeTab);
    }

    private void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(EMERALD_BLADE);
        }
    }
}
