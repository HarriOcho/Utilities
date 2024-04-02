package github.com.harriocho.utilities.commands;

import github.com.harriocho.utilities.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fly extends Command {
    public Fly() {
        super("fly");
    }
    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player player)){
            commandSender.sendMessage(Utils.deniedConsole());
        }else{
            if (!player.hasPermission("ag.admin")) {
                player.sendMessage(Utils.noPermission());
                return true;
            }
            if(!player.getAllowFlight()){
                player.setAllowFlight(true);
                player.sendMessage(Utils.parseMessages("&eHas activado el modo de vuelo"));
                return true;
            }else{
                player.setAllowFlight(false);
                player.sendMessage(Utils.parseMessages("&eHas desactivado el modo de vuelo"));
            }
            if (strings.length == 1) {
                Player target = Bukkit.getServer().getPlayer(strings[0]);
                if (target != null) {
                    if(!target.getAllowFlight()){
                        target.setAllowFlight(true);
                        target.sendMessage(Utils.parseMessages("&a" + player.getName() + " &eactivó tu modo de vuelo "));
                        player.sendMessage(Utils.parseMessages("&eActivaste el modo de vuelo de &a" + target.getName()));
                    }else{
                        target.setAllowFlight(false);
                        target.sendMessage(Utils.parseMessages("&a" + player.getName() + " &edesactivó tu modo de vuelo "));
                        player.sendMessage(Utils.parseMessages("&eDesactivaste el modo de vuelo de &a" + target.getName()));
                    }
                } else {
                    player.sendMessage(Utils.parseMessages("&cEl jugador no existe"));
                }
                return true;
            }
        }
        return true;
    }
}
