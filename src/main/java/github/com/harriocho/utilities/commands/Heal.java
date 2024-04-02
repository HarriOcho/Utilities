package github.com.harriocho.utilities.commands;

import github.com.harriocho.utilities.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal extends Command {
    public Heal() {
        super("heal");
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
                if(player.getHealth() < 20.0){
                    player.setHealth(20.0);
                    player.sendMessage(Utils.parseMessages("&eHas sido curado"));
                }
            }
            if (strings.length == 1) {
                Player target = Bukkit.getServer().getPlayer(strings[0]);
                if (target != null) {
                    if(target.getHealth() < 20.0 ){
                        target.setHealth(20.0);
                        target.sendMessage(Utils.parseMessages("&a" + player.getName() + " &ete curó"));
                        player.sendMessage(Utils.parseMessages("&eCuraste a &a" + target.getName()));
                        return true;
                    }
                } else {
                    player.sendMessage(Utils.parseMessages("&cEl jugador no existe"));
                    return true;
                }
            }
        }
        return true;
    }
}
