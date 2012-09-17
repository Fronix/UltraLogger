package org.justyce.sql.logger;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.justyce.sql.SQLManager;

public class BlockLogger implements Listener{
	
	private SQLManager manager;
	
	public BlockLogger(SQLManager sql){
		manager=sql;
		sql.register(this);
		//Create the def block table
		sql.queryU("CREATE TABLE IF NOT EXISTS Block (id SMALLINT UNSIGNED NOT NULL,event TEXT NOT NULL,x INT NOT NULL,y INT NOT NULL," + " z INT NOT NULL,entity_name TEXT)");
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
		}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		Block i = e.getBlock();
		manager.query("INSERT INTO Block VALUES ("+i.getTypeId()+", 'break', "+toSQLquery(i.getLocation())+",'"+name+"')");
	}
	@EventHandler
	public void onBurn(BlockBurnEvent e){
		Block i = e.getBlock();
		manager.query("INSERT INTO Block VALUES ("+i.getTypeId()+", 'burn', "+toSQLquery(i.getLocation())+",NULL)");
	}
	@EventHandler
	public void onDispense(BlockDispenseEvent e){
		Block i = e.getBlock();
		manager.query("INSERT INTO Block VALUES ("+i.getTypeId()+", 'dispense', "+toSQLquery(i.getLocation())+",NULL)");
	}
	@EventHandler
	public void onForm(BlockFormEvent e){
		Block i = e.getBlock();
		manager.query("INSERT INTO Block VALUES ("+i.getTypeId()+", 'form', "+toSQLquery(i.getLocation())+",NULL)");
	}
	@EventHandler
	public void onGrow(BlockGrowEvent e){
		Block i = e.getBlock();
		manager.query("INSERT INTO Block VALUES ("+i.getTypeId()+", 'grow', "+toSQLquery(i.getLocation())+",NULL)");
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		String name = e.getPlayer().getName();
		if(e.getPlayer().isOp()){
			name="[Admin] "+name;
		}
		name="("+e.getPlayer().getGameMode().name()+")"+name;
		Block i = e.getBlock();
		manager.query("INSERT INTO Block VALUES ("+i.getTypeId()+", 'place', "+toSQLquery(i.getLocation())+",'"+name+"')");
	}
	public String toSQLquery(Location loc){
		String s =""+loc.getBlockX()+","+loc.getBlockX()+","+loc.getBlockZ();
		return s;
	}

}
