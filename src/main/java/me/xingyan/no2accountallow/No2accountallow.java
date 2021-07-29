package me.xingyan.no2accountallow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class No2accountallow extends JavaPlugin implements Listener {

    String kickmsg = ChatColor.RED+"One ip address can be used only one account";



    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void join(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(player.hasPermission("No2accountallow.bypass")) return;
        ArrayList<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());

        if(list.size() == 1) return;

        for(int i = 1; i<= list.size(); i++){
            if(Objects.requireNonNull(list.get(i).getAddress()).getAddress().equals(player.getAddress().getAddress())){
                player.kickPlayer(kickmsg);
            }
        }

    }

}
