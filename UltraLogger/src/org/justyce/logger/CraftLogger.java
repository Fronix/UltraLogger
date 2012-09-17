package org.justyce.logger;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.BrewerInventory;
import org.justyce.LoggerFile;
import org.justyce.MainLogger;

public class CraftLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/craft.log");
	private PrintWriter out =null;
	
	public CraftLogger(MainLogger main,boolean is,int max){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max);
		out=logger.getOut();
	}
	public CraftLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,true);
		out=logger.getOut();
	}
	public CraftLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,s);
		out=logger.getOut();
	}
	
	@EventHandler
	public void onBrew(BrewEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		BrewerInventory i =e.getContents();
		out.println(time+" "+plugin.translate("brew")+" "+i.getItem(0)+" "+plugin.translate("with")+" "+i.getItem(1));
	}
	@EventHandler
	public void onCraft(CraftItemEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name ="";
		Player p =(Player) e.getView().getPlayer();
		if(p!=null){
			name =" "+p.getName();
		}
		out.println(time+name+" "+plugin.translate("craft.item")+" "+e.getRecipe().getResult().toString());
	}
	@EventHandler
	public void onSmelt(FurnaceSmeltEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		out.println(time+" "+plugin.translate("furnace.smelt")+" "+e.getResult().toString());
	}
	public void disable(){
		out.close();
	}

}
