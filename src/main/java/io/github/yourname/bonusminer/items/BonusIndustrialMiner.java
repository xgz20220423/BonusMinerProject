package io.github.yourname.bonusminer.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.miner.IndustrialMiner;
import io.github.yourname.bonusminer.BonusMiner;
import io.github.yourname.bonusminer.listeners.BlockPlaceListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

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
        // 使用配置文件中的范围，默认为5
        super(BonusMiner.getCategory(), BONUS_MINER, Material.ENCHANTING_TABLE, false,
                BonusMiner.getInstance().getConfig().getInt("bonus-miner.range", 5));
        setRecipeType(RecipeType.MULTIBLOCK);
        setRecipe(RECIPE);
    }

    /**
     * 重写父类方法，在挖掘前进行防刷检查。
     */
    @Override
    public boolean canMine(@Nonnull org.bukkit.block.Block block) {
        // 1. 先调用父类的基础检查（类型、权限等）
        if (!super.canMine(block)) {
            return false;
        }

        // 2. ★★★ 新增：调用防刷监听器的检查 ★★★
        // 如果方块被标记为人工放置，则拒绝挖掘
        if (BlockPlaceListener.isBlockManuallyPlaced(block)) {
            return false;
        }

        // 3. 所有检查通过，允许挖掘
        return true;
    }

    /**
     * 重写父类方法，实现多级奖励系统。
     */
    @Override
    @Nonnull
    public ItemStack getOutcome(@Nonnull Material material) {
        // 1. 获取基础掉落（原版逻辑）
        ItemStack baseDrop;
        if (hasSilkTouch()) {
            baseDrop = new ItemStack(material); // 精准采集
        } else {
            baseDrop = getOreDictionary().getDrops(material, ThreadLocalRandom.current());
        }

        // 2. ★★★ 多级奖励逻辑 ★★★
        // 从配置读取奖励层级，此处为示例框架，您可根据最终 config.yml 结构调整
        // 示例：50%无额外，30%+1，15%+2，5%+3
        double roll = ThreadLocalRandom.current().nextDouble() * 100.0; // 生成0-100的随机数
        int bonusAmount = 0;

        if (roll < 50.0) {
            bonusAmount = 0;  // 50% 概率
        } else if (roll < 80.0) { // 50 + 30 = 80
            bonusAmount = 1;  // 30% 概率
        } else if (roll < 95.0) { // 80 + 15 = 95
            bonusAmount = 2;  // 15% 概率
        } else {
            bonusAmount = 3;  // 5% 概率
        }

        // 3. 应用额外奖励
        if (bonusAmount > 0) {
            ItemStack finalDrop = baseDrop.clone();
            finalDrop.setAmount(baseDrop.getAmount() + bonusAmount);
            return finalDrop;
        }

        // 4. 无额外奖励，返回基础掉落
        return baseDrop;
    }

    // 此方法用于主类设置分类，保持不动
    public static void setCategory(ItemGroup category) {
        // 由主类BonusMiner调用
    }
}
