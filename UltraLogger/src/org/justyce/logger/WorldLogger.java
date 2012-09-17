package org.justyce.logger;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.*;
import org.justyce.LoggerFile;
import org.justyce.MainLogger;

public class WorldLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/world.log");
	private PrintWriter out =null;
	
	public WorldLogger(MainLogger main,boolean is,int max){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max);
		out=logger.getOut();
	}
	public WorldLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,true);
		out=logger.getOut();
	}
	public WorldLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,s);
		out=logger.getOut();
	}
	
	@EventHandler
	public void onPortal(PortalCreateEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Block i = e.getBlocks().get(0);
		out.println(time+"["+e.getWorld().getName()+"] "+plugin.translate("portal.create")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onGrow(StructureGrowEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name ="";
		if(e.getPlayer()!=null){
			name = e.getPlayer().getName();
			if(e.getPlayer().isOp()){
				name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
			
			name = ""+plugin.translate("by")+" "+name;
		}
		String bonemeal ="";
		if(e.isFromBonemeal()){
			bonemeal = " "+plugin.translate("cause.bonemeal")+"";
		}
		Location i = e.getLocation();
		out.println(time+"["+e.getWorld().getName()+"] a "+e.getSpecies().name()+" "+plugin.translate("structure.grow")+" "+plugin.translate("in")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+bonemeal);
	}
	@EventHandler
	public void onSpawnChanges(SpawnChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Location i =e.getPreviousLocation();
		Location it = e.getWorld().getSpawnLocation();
		out.println(time+"["+e.getWorld().getName()+"] "+plugin.translate("spawn.change")+" "+plugin.translate("from")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+plugin.translate("to")+" ["+(int)it.getX()+","+(int)it.getY()+","+(int)it.getZ()+"] ");
	}
	public void disable(){
		out.close();
	}

}
