package org.justyce.logger;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.justyce.LoggerFile;
import org.justyce.MainLogger;

public class InventoryLogger implements Listener{
	
	private MainLogger plugin =null;
	private File log = new File("./Log/inventory.log");
	private PrintWriter out =null;
	
	public InventoryLogger(MainLogger main,boolean is,int max){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max);
		out=logger.getOut();
	}
	
	public InventoryLogger(MainLogger main,boolean is,int max,boolean s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,true);
		out=logger.getOut();
	}
	public InventoryLogger(MainLogger main,boolean is,int max,Date s){
		this.plugin=main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		LoggerFile logger =new LoggerFile(log,is,max,s);
		out=logger.getOut();
	}
	
	@EventHandler
	public void onOpen(InventoryOpenEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		HumanEntity i =e.getPlayer();
		out.println(time+name+" "+plugin.translate("inventory.open")+" "+e.getView().getTitle()+" ("+e.getView().getType().name()+") "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	@EventHandler
	public void onClose(InventoryCloseEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;}name="("+e.getPlayer().getGameMode().name()+")"+name;
		
		HumanEntity i =e.getPlayer();
		out.println(time+name+" "+plugin.translate("inventory.close")+" "+e.getView().getTitle()+" ("+e.getView().getType().name()+") "+plugin.translate("in")+" ["+
				(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
	}
	private ItemStack last = null;
	private Inventory lastI = null;
	private int amount = -1;
	@EventHandler
	public void onClick(InventoryClickEvent e){
		String time = DateFormat.getInstance().format(new Date(System.currentTimeMillis()))+" ";
		String name = e.getWhoClicked().getName();
		if(e.getWhoClicked().isOp()){
			name="[Admin] "+name;}name="("+e.getWhoClicked().getGameMode().name()+")"+name;
		
		if(e.getCurrentItem()==null){ return;}
		HumanEntity i =e.getWhoClicked();
		ItemStack item =e.getCursor();
		boolean in = e.getView().getBottomInventory().getSize()<=e.getRawSlot();
		
		if(last!=null&&lastI!=null&&e.getView().getTopInventory().getType()==lastI.getType()&&item.getTypeId()==0){
			last.setAmount(amount);
			if(in){
				out.println(time+name+" "+plugin.translate("inventory.put")+" "+last.toString()+" "+plugin.translate("from")+" "+lastI.getType().name()+
						" "+plugin.translate("inventory.to")+"" +
						" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
			}
			else{
				out.println(time+name+" "+plugin.translate("inventory.put")+" "+last.toString()+" "+plugin.translate("inventory.from")+" "+e.getView().getType().name()+
						" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
			}
			last=e.getCurrentItem();
			amount=item.getAmount();
			lastI=e.getView().getTopInventory();
		}
		else{
			last=item;
			amount=item.getAmount();
			lastI=e.getView().getTopInventory();
			out.println(time+name+" "+plugin.translate("inventory.click")+" "+item.toString()+" "+plugin.translate("from")+" "+e.getView().getTitle()+
					" "+plugin.translate("in")+" ["+(int)i.getLocation().getX()+","+(int)i.getLocation().getY()+","+(int)i.getLocation().getZ()+"]");
		}
		
	}
	public void disable(){
		out.close();
	}

}
