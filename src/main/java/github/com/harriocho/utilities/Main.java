package github.com.harriocho.utilities;

import github.com.harriocho.utilities.commands.*;
import github.com.harriocho.utilities.listeners.PlayerListeners;
import io.papermc.paper.plugin.configuration.PluginMeta;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    private final PluginMeta META = this.getPluginMeta();
    private final Logger LOGGER = this.getSLF4JLogger();
    private final String name = META.getName();
    private final String author = META.getAuthors().toString();
    private final String version = META.getVersion();
    @Override
    public void onEnable() {
        this.registerListeners(new PlayerListeners(this));
        this.registerCommands();
        this.registerConfigsFiles();
        LOGGER.info(name + " " + version + " se cargó con éxito \nAutor:" + author);
    }
    @Override
    public void onDisable(){
        LOGGER.info(name + " " + version + " se cargó con éxito");
    }
    private void registerCommands(){
        Command gm = new Gamemodes();
        Command fly = new Fly();
        Command heal = new Heal();
        Command setwarp = new SetWarp(this);
        Command invul = new Invul();

        List<Command> cmds = new ArrayList<>();
        cmds.add(gm);
        cmds.add(fly);
        cmds.add(heal);
        cmds.add(setwarp);
        cmds.add(invul);

        getServer().getCommandMap().registerAll("Utilities", cmds);

    }
    private void registerListeners(Listener... listeners){
        final PluginManager pluginManager = getServer().getPluginManager();
        for (final Listener listener : listeners){
            pluginManager.registerEvents(listener, this);
        }
    }
    private void registerConfigsFiles(){
        List<String> configFiles = new ArrayList<>();
        configFiles.add("config.yml");
        configFiles.add("warps.yml");
        for(String e: configFiles){
            saveResource(e,false);
        }
        this.saveDefaultConfig();
    }
}
