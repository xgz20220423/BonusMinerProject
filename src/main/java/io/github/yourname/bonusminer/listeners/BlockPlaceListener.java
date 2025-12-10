package io.github.yourname.bonusminer.listeners;

import io.github.yourname.bonusminer.BonusMiner;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

public class BlockPlaceListener implements Listener {

    private final BonusMiner plugin;
    private final NamespacedKey placedKey;

    public BlockPlaceListener(BonusMiner plugin) {
        this.plugin = plugin;
        this.placedKey = new NamespacedKey(plugin, "manually_placed");
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material type = block.getType();

        // 检查放置的是否是矿石类方块
        if (isOre(type)) {
            // 为方块添加“人为放置”的持久化标记
            block.getPersistentDataContainer().set(placedKey, PersistentDataType.BYTE, (byte) 1);
        }
    }

    private boolean isOre(Material material) {
        // 这是一个简单的判断，您可以根据需要扩展
        String name = material.name();
        return name.endsWith("_ORE") || material == Material.ANCIENT_DEBRIS;
    }
}
