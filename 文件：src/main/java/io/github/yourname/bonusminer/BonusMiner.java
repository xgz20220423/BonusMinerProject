package io.github.yourname.bonusminer;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.yourname.bonusminer.items.BonusIndustrialMiner;
import io.github.yourname.bonusminer.listeners.BlockPlaceListener;
import org.bukkit.plugin.java.JavaPlugin;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BonusMiner extends JavaPlugin implements SlimefunAddon {

    private static BonusMiner instance;
    private BlockPlaceListener blockPlaceListener;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // 注册防刷监听器
        if (getConfig().getBoolean("bonus-miner.anti-dupe", true)) {
            this.blockPlaceListener = new BlockPlaceListener(this);
            getServer().getPluginManager().registerEvents(blockPlaceListener, this);
            getLogger().info("防刷矿物放置监听器已启用。");
        }

        // 注册矿机物品
        new BonusIndustrialMiner();
        getLogger().info("附魔工业矿机已注册！");
        getLogger().info("插件已成功启用。");
    }

    @Override
    public void onDisable() {
        getLogger().info("插件已卸载。");
    }

    public static BonusMiner getInstance() {
        return instance;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public @Nullable String getBugTrackerURL() {
        return null;
    }

    @Override
    public @Nonnull String getWikiURL() {
        return "https://your-wiki.com";
    }
}
