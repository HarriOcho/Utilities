package github.com.harriocho.utilities.listeners;

import github.com.harriocho.utilities.Main;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerListeners implements Listener {
    private final Main plugin;
    public PlayerListeners(Main plugin){
        this.plugin = plugin;
    }
    @EventHandler
    private void noHunger(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    private void onDeath(PlayerDeathEvent e){
        e.setKeepInventory(true);
    }

}
