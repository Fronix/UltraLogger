package org.justyce.logger;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.*;
import org.justyce.LoggerFile;
import org.justyce.MainLogger;

public class VehicleLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/vehicle.log");
	private PrintWriter out =null;
	
	public VehicleLogger(MainLogger main,boolean is,int max){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max);
		out=logger.getOut();
	}
	public VehicleLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,true);
		out=logger.getOut();
	}
	public VehicleLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,s);
		out=logger.getOut();
	}
	
	@EventHandler
	public void onCreated(VehicleCreateEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Location loc = e.getVehicle().getLocation();
		out.println(time+e.getVehicle().toString()+" "+plugin.translate("vehicle.place")+" "+plugin.translate("in")+" ["+(int)loc.getX()+","+(int)loc.getY()+","+(int)loc.getZ()+"]");
	}
	@EventHandler
	public void onDestroyed(VehicleDestroyEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Location loc = e.getVehicle().getLocation();
		String entity ="";
		if(e.getAttacker()!=null){
			entity="by "+e.getAttacker().getClass(); 
		}
		out.println(time+e.getVehicle().toString()+" "+plugin.translate("vehicle.destroy")+" "+entity+" "+plugin.translate("in")+" ["+(int)loc.getX()+","+(int)loc.getY()+","+(int)loc.getZ()+"]");
	}
	@EventHandler
	public void onEnter(VehicleEnterEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Location loc = e.getVehicle().getLocation();
		String entity =e.getEntered().getClass()+"";
		out.println(time+entity+" "+plugin.translate("vehicle.enter")+" "+e.getVehicle().toString()+" "+plugin.translate("in")+" ["+(int)loc.getX()+","+(int)loc.getY()+","+(int)loc.getZ()+"]");
	}
	@EventHandler
	public void onExit(VehicleExitEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Location loc = e.getVehicle().getLocation();
		String entity =e.getExited().getClass()+"";
		out.println(time+entity+" "+plugin.translate("vehucle.exit")+" "+e.getVehicle().toString()+" "+plugin.translate("in")+" ["+(int)loc.getX()+","+(int)loc.getY()+","+(int)loc.getZ()+"]");
	}
	public void disable(){
		out.close();
	}

}
