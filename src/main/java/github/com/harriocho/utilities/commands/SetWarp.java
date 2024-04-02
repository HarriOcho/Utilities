package github.com.harriocho.utilities.commands;

import github.com.harriocho.utilities.Main;
import github.com.harriocho.utilities.utils.Utils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SetWarp extends Command {
    private final Main plugin;
    public SetWarp(Main plugin) {
        super("setwarp","Establece zonas de aparición","/setwarp <nombre>", List.of("sw"));
        this.plugin = plugin;

    }
    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        File file = new File(plugin.getDataFolder(), "warps.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        if(!(commandSender instanceof  Player player)){
            commandSender.sendMessage(Utils.deniedConsole());
        }else{
            String world = player.getWorld().getName();
            Location coord = player.getLocation();
            Double x = coord.getX();
            Double y = coord.getY();
            Double z = coord.getZ();
            Float pitch = coord.getPitch();
            Float yaw = coord.getYaw();
            if (!player.hasPermission("ag.admin")) {
                player.sendMessage(Utils.noPermission());
                return true;
            }
            if(yml.get(strings[0]) != null){
                player.sendMessage(Utils.parseMessages("&cEste warp ya existe"));
            }
            if(strings.length == 1){
                String name = "Warps."+strings[0];
                yml.set(name+".World", world);
                yml.set(name+".X", x);
                yml.set(name+".Y", y);
                yml.set(name+".Z", z);
                yml.set(name+".Pitch", pitch);
                yml.set(name+".Yaw", yaw);
                player.sendMessage(Utils.parseMessages("&aWarp establecido"));
                try {
                    yml.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
            player.sendMessage(Utils.parseMessages("&eUtiliza /setwarp <nombre>"));
            return true;
        }return true;
    }
}
