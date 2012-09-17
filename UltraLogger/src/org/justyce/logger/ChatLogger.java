package org.justyce.logger;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.justyce.LoggerFile;
import org.justyce.MainLogger;

public class ChatLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/chat.log");
	private PrintWriter out =null;
	
	public ChatLogger(MainLogger main,boolean is,int max){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max);
		out=logger.getOut();
	}
	
	public ChatLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,true);
		out=logger.getOut();
	}
	public ChatLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,s);
		out=logger.getOut();
	}
	
	@EventHandler
	public void onSpeak(AsyncPlayerChatEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
			}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out.println(time+name+" --> "+e.getMessage());
	}
	public void disable(){
		out.close();
	}

}
