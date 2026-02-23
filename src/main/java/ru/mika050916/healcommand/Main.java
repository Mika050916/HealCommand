package ru.mika050916.healcommand;

import cn.nukkit.plugin.PluginBase;
import ru.mika050916.healcommand.commands.HealCommand;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class Main extends PluginBase {

    private final Map<UUID, Integer> laggedPlayers = new ConcurrentHashMap<>();

    private final Set<UUID> processingPlayers = ConcurrentHashMap.newKeySet();

    @Override
    public void onEnable() {
        getLogger().info("Плагин работает.");
        getServer().getCommandMap().register("heal", new HealCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин отключён.");

        laggedPlayers.clear();
        processingPlayers.clear();
    }

    public Map<UUID, Integer> getLaggedPlayers() {
        return laggedPlayers;
    }

    public Set<UUID> getProcessingPlayers() {
        return processingPlayers;
    }
}