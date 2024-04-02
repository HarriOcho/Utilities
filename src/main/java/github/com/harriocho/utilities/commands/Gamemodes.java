package github.com.harriocho.utilities.commands;

import github.com.harriocho.utilities.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Gamemodes extends Command {
    public Gamemodes() {
        super("gamemode", "Cambia el modo de juego de los jugadores o de ti mismo", "/gamemode [args]", List.of("gm"));
    }
    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(Utils.deniedConsole());
        } else {
            if (!player.hasPermission("ag.admin")) {
                player.sendMessage(Utils.noPermission());
                return true;
            }
            if (strings.length == 0) {
                player.sendMessage(Utils.parseMessages("&cDefine un modo de juego"));
                return true;
            }
            org.bukkit.GameMode mode = null;
            try {
                mode = switch (Integer.parseInt(strings[0])) {
                    case 0 -> org.bukkit.GameMode.SURVIVAL;
                    case 1 -> org.bukkit.GameMode.CREATIVE;
                    case 2 -> org.bukkit.GameMode.ADVENTURE;
                    case 3 -> org.bukkit.GameMode.SPECTATOR;
                    default -> null;
                };
            } catch (Exception e) {
                for (org.bukkit.GameMode modes : org.bukkit.GameMode.values()) {
                    if (modes.name().startsWith(strings[0].toUpperCase())) {
                        mode = modes;
                        break;
                    }
                }
            }
            if (mode == null) {
                player.sendMessage(Utils.parseMessages("&cModo de juego no válido"));
                return true;
            }
            if (strings.length == 2) {
                Player target = Bukkit.getServer().getPlayer(strings[1]);
                if (target != null) {
                    target.setGameMode(mode);
                    target.sendMessage(Utils.parseMessages("&a" + player.getName() + " &eCambió tu modo de juego a " + mode.name().toLowerCase()));
                    player.sendMessage(Utils.parseMessages("&eCambiaste el modo de juego de &a" + target.getName() + " &ea " + mode.name().toLowerCase()));
                } else {
                    player.sendMessage(Utils.parseMessages("&cEl jugador no existe"));
                }
                return true;
            }
            player.sendMessage(Utils.parseMessages("&eCambiaste tu modo de juego a " + mode.name().toLowerCase()));
            player.setGameMode(mode);

        }
        return true;
    }
}
