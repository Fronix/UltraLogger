package org.justyce.logger;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.*;
import org.justyce.LoggerFile;
import org.justyce.MainLogger;

public class WeatherLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/weather.log");
	private PrintWriter out =null;
	
	public WeatherLogger(MainLogger main,boolean is,int max){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max);
		out=logger.getOut();
	}
	public WeatherLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,true);
		out=logger.getOut();
	}
	public WeatherLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,s);
		out=logger.getOut();
	}
	
	@EventHandler
	public void onStrike(LightningStrikeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getLightning();
		out.println(time+"["+e.getWorld().getName()+"] "+plugin.translate("strike")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onThunder(ThunderChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		boolean i =e.toThunderState();
		String thunder =""+plugin.translate("thunder.stop")+"";
		if(i){
			thunder=""+plugin.translate("thunder.start")+"";
		}
		out.println(time+"["+e.getWorld().getName()+"] "+thunder);
	}
	@EventHandler
	public void onChange(WeatherChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		boolean i =e.toWeatherState();
		String thunder =""+plugin.translate("rain.stop")+"";
		if(i){
			thunder=""+plugin.translate("rain.start")+"";
		}
		out.println(time+"["+e.getWorld().getName()+"] "+thunder);
	}
	public void disable(){
		out.close();
	}

}
