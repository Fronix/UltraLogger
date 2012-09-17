package org.justyce.logger;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.justyce.LoggerFile;
import org.justyce.MainLogger;

public class PlayerLogger implements Listener{

	private MainLogger plugin =null;
	private File log1 = new File("./Log/player.log");
	private PrintWriter out1 =null;

	
	public PlayerLogger(MainLogger main,boolean is,int max){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log1,is,max);
		out1=logger.getOut();
	}
	public PlayerLogger(MainLogger main,boolean is,int max,boolean x){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log1,is,max,true);
		out1=logger.getOut();
	}
	public PlayerLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log1,is,max,s);
		out1=logger.getOut();
	}
	
	@EventHandler
	public void onEvent1(PlayerBedEnterEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
		}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		Block bed = e.getBed();
		out1.println(time+name+" "+plugin.translate("bed.enter")+" "+plugin.translate("in")+"  ["+(int)bed.getX()+","+(int)bed.getY()+","+(int)bed.getZ()+"]");
	}
	@EventHandler
	public void onEvent2(PlayerBedLeaveEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block bed = e.getBed();
		out1.println(time+name+" "+plugin.translate("bed.leave")+" "+plugin.translate("in")+"  ["+(int)bed.getX()+","+(int)bed.getY()+","+(int)bed.getZ()+"]");
	}
	@EventHandler
	public void onEvent3(PlayerBucketEmptyEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block bed = e.getBlockClicked();
		out1.println(time+name+" "+plugin.translate("bucket.empty")+" "+plugin.translate("in")+" ["+(int)bed.getX()+","+(int)bed.getY()+","+(int)bed.getZ()+"]");
	}
	@EventHandler
	public void onEvent4(PlayerBucketFillEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Block bed = e.getBlockClicked();
		out1.println(time+name+" "+plugin.translate("bucket.fill")+" "+plugin.translate("in")+" ["+(int)bed.getX()+","+(int)bed.getY()+","+(int)bed.getZ()+"]");
	}
	@EventHandler
	public void onEvent5(PlayerChangedWorldEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out1.println(time+name+" "+plugin.translate("player.change.world")+" "+plugin.translate("from")+" "+e.getFrom().getName()+" "+plugin.translate("to")+" "+e.getPlayer().getWorld().getName());
	}
	@EventHandler
	public void onEvent6(PlayerDropItemEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Item i =e.getItemDrop();
		out1.println(time+name+" "+plugin.translate("player.drop")+" "+i.getItemStack().toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+
		(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent7(PlayerEggThrowEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Egg i =e.getEgg();
		out1.println(time+name+" "+plugin.translate("player.throw.egg")+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent8(PlayerExpChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		int i =e.getAmount();
		out1.println(time+name+" "+plugin.translate("player.earns.xp")+" "+i+" xp(s) !");
	}
	@EventHandler
	public void onEvent9(PlayerFishEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.getState()==State.CAUGHT_ENTITY){
			Entity i =e.getCaught();
			if(i!=null){
				out1.println(time+name+" "+plugin.translate("player.fishing")+" "+i.toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"]");
			}
		}
		else{
			Player i =e.getPlayer();
			out1.println(time+name+" "+plugin.translate("player.fish")+"("+e.getState().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"]");
		}
	}
	@EventHandler
	public void onEvent10(PlayerGameModeChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out1.println(time+name+" "+plugin.translate("player.change.gamemode")+" "+e.getNewGameMode().name());
	}
	@EventHandler
	public void onEvent11(PlayerInteractEntityEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Entity i = e.getRightClicked();
		out1.println(time+name+" "+plugin.translate("entity.interact")+" "+i.toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","
		+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent12(PlayerInteractEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.getAction()==Action.LEFT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_AIR){
			Player i = e.getPlayer();
			if(e.hasItem()){
				out1.println(time+name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"] "+plugin.translate("with")+" "+e.getItem().toString());
			}
			else{
				out1.println(time+name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"] "+plugin.translate("player.with.hand")+"");
			}
		}
		else{
			Block i = e.getClickedBlock();
			if(e.hasItem()){
				out1.println(time+name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"] "+plugin.translate("with")+" "+e.getItem().toString());
			}
			else{
				out1.println(time+name+" "+plugin.translate("player.interact")+" ("+e.getAction().name()+") "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
			(int)i.getLocation().getZ()+"] "+plugin.translate("player.with.hand")+"");
			}
		}
	}
	@EventHandler
	public void onEvent13(PlayerItemBreakEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		ItemStack i =e.getBrokenItem();
		out1.println(time+name+" "+plugin.translate("player.break.item")+" "+i.toString());
	}
	@EventHandler
	public void onEvent14(PlayerJoinEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out1.println(time+name+" "+plugin.translate("player.connected")+"");
	}
	@EventHandler
	public void onEvent15(PlayerPickupItemEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Item i =e.getItem();
		out1.println(time+name+" "+plugin.translate("player.pickup.item")+" "+i.getItemStack().toString()+
				" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent16(PlayerPortalEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Location i = e.getFrom();
		Location f = e.getTo();
		out1.println(time+name+" "+plugin.translate("player.teleport")+" "+plugin.translate("from")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+plugin.translate("to")+" ["+(int)f.getX()+","+(int)f.getY()+","+
		(int)f.getZ()+"] "+plugin.translate("cause")+" "+e.getCause().name());
	}
	@EventHandler
	public void onEvent17(AsyncPlayerPreLoginEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getName()+" ("+e.getAddress().getHostAddress()+")";
		if(e.getKickMessage()!=null&&e.getKickMessage()!=""){
			out1.println(time+name+" "+plugin.translate("was")+" "+e.getResult().name()+" "+plugin.translate("cause")+" : "+e.getKickMessage());
		}
		else{
			out1.println(time+name+" "+plugin.translate("was")+" "+e.getResult().name());
		}
	}
	@EventHandler
	public void onEvent18(PlayerQuitEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out1.println(time+name+" "+plugin.translate("player.disconnect")+" ");
	}
	@EventHandler
	public void onEvent19(PlayerRespawnEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Location i = e.getRespawnLocation();
		out1.println(time+name+" "+plugin.translate("player.respawn")+" "+plugin.translate("in")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"]");
	}
	@EventHandler
	public void onEvent20(PlayerShearEntityEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
			}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Entity i = e.getEntity();
		out1.println(time+name+" "+plugin.translate("player.shear")+" "+i.toString()+" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+
		(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onEvent21(PlayerTeleportEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		Location i = e.getFrom();
		Location f = e.getTo();
		out1.println(time+name+" "+plugin.translate("player.teleport")+" "+plugin.translate("from")+" ["+(int)i.getX()+","+(int)i.getY()+","+(int)i.getZ()+"] "+plugin.translate("to")+" ["+(int)f.getX()+","+(int)f.getY()+","+
		(int)f.getZ()+"] "+plugin.translate("cause")+" "+e.getCause().name());
	}
	@EventHandler
	public void onEvent22(PlayerToggleFlightEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.isFlying()){
			out1.println(time+name+" "+plugin.translate("player.start.fly"));
		}
		else{
			out1.println(time+name+" "+plugin.translate("player.stop.fly"));
		}
	}
	@EventHandler
	public void onEvent23(PlayerToggleSprintEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.isSprinting()){
			out1.println(time+name+" "+plugin.translate("player.start.sprint"));
		}
		else{
			out1.println(time+name+" "+plugin.translate("player.stop.sprint"));
		}
	}
	@EventHandler
	public void onEvent24(PlayerToggleSneakEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		if(e.isSneaking()){
			out1.println(time+name+" "+plugin.translate("player.start.sneak"));
		}
		else{
			out1.println(time+name+" "+plugin.translate("player.stop.sneak"));
		}
	}
	@EventHandler
	public void onEvent25(PlayerDeathEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getEntity().getName();
		if(e.getEntity().isOp()){
			name="[Admin] "+name;}name="("+e.getEntity().getGameMode().name()+")"+name;
		
		out1.println(time+name+" "+plugin.translate("entity.death")+"  : "+e.getDeathMessage());
	}
	@EventHandler
	public void onEvent26(PlayerLevelChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		out1.println(time+name+" "+plugin.translate("player.to.lvl")+" "+e.getNewLevel()+" "+plugin.translate("player.from.lvl")+" "+e.getOldLevel());
	}
	@EventHandler
	public void onEvent26(FoodLevelChangeEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getEntity().getName();
		if(e.getEntity().isOp()){
			name="[Admin] "+name;}name="("+e.getEntity().getGameMode().name()+")"+name;
		
		out1.println(time+name+" "+plugin.translate("player.food.change")+" "+e.getFoodLevel());
	}
	

	public void disable(){
		out1.close();
	}

}
