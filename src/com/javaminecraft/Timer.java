package com.javaminecraft;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Timer extends JavaPlugin {

    public static final Logger logger = Logger.getLogger("Minecraft");
    Player player;
    public long totalCountdown;
    public long timeBetween;

    @Override
    public void onDisable() {
        this.logger.info("[Timer] has been Disabled");

    }

    @Override
    public void onEnable() {
        this.logger.info("[Timer] has been Enabled");

    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        player = (Player) sender;
        if (label.equalsIgnoreCase("timer")) {
            if (arguments.length == 2) {
                totalCountdown = calculateTime(arguments[0], 0);
                timeBetween = calculateTime(arguments[1], 1);

                this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {

                    public void run() {

                        if (totalCountdown != -1) {
                            if (totalCountdown != 0) {
                                Bukkit.broadcastMessage(ChatColor.RED + "" + totalCountdown);
                                totalCountdown--;
                            }
                            else {
                                Bukkit.broadcastMessage(ChatColor.GOLD + "Go!!!!");
                                totalCountdown--;
                            }
                        }
                    }
                }, 0L, timeBetween);
            } else {
                player.sendMessage("please type /timer [# in countdown] [time between each number]");
            }
        }
        return false;
    }

    public long calculateTime(String label, int argument) {
        int time = Integer.parseInt(label);
        if (argument == 0) {
            totalCountdown = time;
            return totalCountdown;
        } else {
            timeBetween = time * 20;
            return timeBetween;
        }

    }
    

}
