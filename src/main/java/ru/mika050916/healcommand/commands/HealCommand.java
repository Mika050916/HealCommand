package ru.mika050916.healcommand.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HealCommand extends Command {

    private static final long COOLDOWN_TIME = 5 * 60 * 1000;
    private static final Map<UUID, Long> cooldowns = new HashMap<>();

    public HealCommand() {
        super("heal");
        this.setDescription("Лечит игрока");
        this.setUsage("/heal");
        this.setPermission("testplugin.heal");
        this.setPermissionMessage(TextFormat.RED + "У вас нет прав для этого!");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage(TextFormat.RED + "Команда только для игроков.");
            return true;
        }

        UUID uuid = p.getUniqueId();
        long currentTime = System.currentTimeMillis();

        if (cooldowns.containsKey(uuid)) {
            long lastUse = cooldowns.get(uuid);
            long timeLeft = (lastUse + COOLDOWN_TIME) - currentTime;

            if (timeLeft > 0) {
                long secondsLeft = timeLeft / 1000;
                p.sendMessage(TextFormat.RED + "Команду можно использовать через " + secondsLeft + " сек.");
                return true;
            }
        }

        p.setHealth(p.getMaxHealth());
        p.sendMessage(TextFormat.GREEN + "Вы успешно " + TextFormat.LIGHT_PURPLE + "вылечили " + TextFormat.GREEN + "себя!");

        cooldowns.put(uuid, currentTime);
        return true;
    }
}