package io.github.yourname.bonusminer.listeners;

import io.github.yourname.bonusminer.BonusMiner;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class BlockPlaceListener implements Listener {

    private final BonusMiner plugin;
    // 定义元数据的键名
    private static final String METADATA_KEY = "BONUS_MINER_PLACED";

    public BlockPlaceListener(BonusMiner plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material type = block.getType();

        // 检查放置的是否是矿石类方块
        if (isOre(type)) {
            // ★★★ 修改点：使用 setMetadata 替代 getPersistentDataContainer ★★★
            // 为方块添加“人为放置”的元数据标记，标记在插件卸载时会自动清理
            block.setMetadata(METADATA_KEY, new FixedMetadataValue(plugin, true));
        }
    }

    /**
     * 提供给矿机挖掘逻辑调用的静态方法，用于判断方块是否被此监听器标记过。
     * 矿机在挖掘前应调用此方法进行检查。
     */
    public static boolean isBlockManuallyPlaced(Block block) {
        return block.hasMetadata(METADATA_KEY);
    }

    private boolean isOre(Material material) {
        // 这是一个简单的判断，您可以根据需要扩展
        String name = material.name();
        return name.endsWith("_ORE") || material == Material.ANCIENT_DEBRIS;
    }
}
