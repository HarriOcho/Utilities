package github.com.harriocho.utilities.commands;

import github.com.harriocho.utilities.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Invul extends Command {
    public Invul() {
        super("invul");
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
            if(strings.length == 0){
                if(!player.isInvulnerable()){
                    player.setInvulnerable(true);
                    player.sendMessage(Utils.parseMessages("&eAhora eres invulnerable"));
                }else{
                    player.setInvulnerable(false);
                    player.sendMessage(Utils.parseMessages("&eAhora eres vulnerable"));
                }
            }
            if (strings.length == 1) {
                Player target = Bukkit.getServer().getPlayer(strings[0]);
                if (target != null) {
                    if(!target.isInvulnerable()){
                        target.setInvulnerable(true);
                        target.sendMessage(Utils.parseMessages("&a" + player.getName() + " &ete hizo invulnerable"));
                        player.sendMessage(Utils.parseMessages("&eHas hecho invulnerable a &a" + target.getName()));
                    }else{
                        target.setInvulnerable(false);
                        target.sendMessage(Utils.parseMessages("&a" + player.getName() + " &ete hizo vulnerable"));
                        player.sendMessage(Utils.parseMessages("&eHas hecho vulnerable a &a" + target.getName()));
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
