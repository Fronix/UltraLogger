package org.justyce.sql.logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.justyce.sql.SQLManager;

public class CommandLogger implements Listener{

	private SQLManager manager;
	
	public CommandLogger(SQLManager sql){
		manager=sql;
		sql.register(this);
		//Create the def block table
		sql.queryU("CREATE TABLE `command` (`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT, `time` DATETIME NOT NULL, `playername` VARCHAR(255) NOT NULL, `cmd` VARCHAR(255) NOT NULL, `worked` VARCHAR(255) NOT NULL, PRIMARY KEY (`id`)) COLLATE='utf8_general_ci' ENGINE=InnoDB ROW_FORMAT=DEFAULT");}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name= "[Admin] "+name;
		}
		String display ="No";
		if(manager.getPlugin().getServer().getPluginCommand(e.getMessage())!=null||isServerCommand(e.getMessage())){
			display ="Yes";
		}
		manager.query("INSERT INTO `command`(`time`, `playername`, `cmd`, `worked`) VALUES (NOW(), '"+name+"', '"+e.getMessage()+"', '"+display+"');");
	}
	
	private boolean isServerCommand(String message) {
		if(message.startsWith("/help")||message.startsWith("/time set")||message.startsWith("/time add")||message.startsWith("/gamemode")
				||message.startsWith("/ban")||message.startsWith("/op")||message.startsWith("/deop")||message.startsWith("/defaultgamemode")
				||message.startsWith("/give")||message.startsWith("/kick")||message.startsWith("/list")||message.startsWith("/me")
				||message.startsWith("/pardon")||message.startsWith("/plugins")||message.startsWith("/pl")||message.startsWith("/reload")
				||message.startsWith("/save")||message.startsWith("/say")||message.startsWith("/xp")||message.startsWith("/seed")
				||message.startsWith("/time")||message.startsWith("/stop")||message.startsWith("/tell")||message.startsWith("/timings")
				||message.startsWith("/toggledownfall")||message.startsWith("/tp")||message.startsWith("/version")||message.startsWith("/whitelist")
				||message.startsWith("/kill")){
			return true;
		}
		return false;
	}

}
