package io.github.yourname.bonusminer.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.miner.IndustrialMiner;
import io.github.yourname.bonusminer.BonusMiner;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nonnull;

public class BonusIndustrialMiner extends IndustrialMiner {

    private static final SlimefunItemStack BONUS_MINER = new SlimefunItemStack(
        "BONUS_INDUSTRIAL_MINER",
        Material.ENCHANTING_TABLE,
        "&b附魔工业矿机",
        "",
        "&7一种先进的采矿设备",
        "&7以附魔台为核心，",
        "&7有概率获得额外收获！"
    );

    private static final ItemStack[] RECIPE = {
        null, null, null,
        new ItemStack(Material.PISTON), new ItemStack(Material.CHEST), new ItemStack(Material.PISTON),
        new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.ENCHANTING_TABLE), new ItemStack(Material.DIAMOND_BLOCK)
    };

    public BonusIndustrialMiner() {
        // 调用父类构造：核心方块为 Material.ENCHANTING_TABLE
        super(BonusMiner.getCategory(), BONUS_MINER, Material.ENCHANTING_TABLE, false,
                BonusMiner.getInstance().getConfig().getInt("bonus-miner.range", 5));
        setRecipeType(RecipeType.MULTIBLOCK);
        setRecipe(RECIPE);
    }

    // 设置物品分类（需要在BonusMiner类中创建并传入）
    public static void setCategory(ItemGroup category) {
        // 此方法由主类调用，用于设置分类
    }
}
