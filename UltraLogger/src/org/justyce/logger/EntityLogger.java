package org.justyce.logger;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.justyce.LoggerFile;
import org.justyce.MainLogger;

public class EntityLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/entity.log");
	private PrintWriter out =null;
	
	public EntityLogger(MainLogger main,boolean is,int max){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max);
		out=logger.getOut();
	}
	public EntityLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,true);
		out=logger.getOut();
	}
	public EntityLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,s);
		out=logger.getOut();
	}
	
	@EventHandler
	public void onEvent1(CreatureSpawnEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("creature.spawn")+" ("+e.getSpawnReason().name()+
				") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");

	}
	@EventHandler
	public void onEvent2(CreeperPowerEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("creature.lightning")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");

	}
	@EventHandler
	public void onEvent3(EntityBreakDoorEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("creature.broke.door")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent4(EntityDamageEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		int dmg = e.getDamage();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("damaged")+" "+dmg+" "+plugin.translate("half.heart")+"  "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"] "+plugin.translate("cause")+" "+e.getCause().name());

	}
	@EventHandler
	public void onEvent5(EntityDeathEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.death")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent6(EntityExplodeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.explode")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent7(EntityInteractEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Block i =e.getBlock();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+e.getEntity().toString()+" "+plugin.translate("entity.interact")+" "+new ItemStack(i.getTypeId())+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent8(EntityRegainHealthEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.regain")+" "+e.getAmount()+" "+plugin.translate("half.heart")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"] "+plugin.translate("cause")+" "+e.getRegainReason().name());
	}
	@EventHandler
	public void onEvent9(EntityTameEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.tame")+" "+e.getOwner().getName()+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent10(ExpBottleEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+plugin.translate("expbottle.throw")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent11(PotionSplashEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getPotion();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("entity.throw")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent12(ProjectileHitEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		Entity i =e.getEntity();
		out.println(time+"["+e.getEntity().getWorld().getName()+"] "+i.toString()+" "+plugin.translate("projectile.hit")+" "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	
	
	public void disable(){
		out.close();
	}

}
