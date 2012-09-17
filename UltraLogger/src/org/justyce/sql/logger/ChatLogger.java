package org.justyce.sql.logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.justyce.sql.SQLManager;

public class ChatLogger implements Listener{
	
	private SQLManager manager;
	
	public ChatLogger(SQLManager sql){
		manager=sql;
		sql.register(this);
		//Create the def block table
		sql.queryU("CREATE TABLE `chat` (`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,`time` DATETIME NOT NULL,`playername` VARCHAR(255) NOT NULL,`text` VARCHAR(255) NOT NULL,PRIMARY KEY (`id`)) COLLATE='utf8_general_ci' ENGINE=InnoDB ROW_FORMAT=DEFAULT");
	}
	
	@EventHandler
	public void onSpeak(AsyncPlayerChatEvent e){
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
			}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		manager.query("INSERT INTO `chat` (`time`, `playername`, `text`) VALUES (NOW(), '"+name+"', '"+e.getMessage()+"');");
	}

}
