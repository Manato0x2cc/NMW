package com.mnt2cc.NMW;

import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;

import com.mnt2cc.NMW.magic.*;
import com.mnt2cc.NMW.task.FireworkTask;
import com.mnt2cc.NMW.task.ShieldTask;
import com.mnt2cc.NMW.tool.Firework;
import com.mnt2cc.NMW.tool.ManeverGear3D;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.math.Vector3;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.item.Item;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.UseItemPacket;

public class Nmw extends PluginBase implements Listener {
	
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);
		Server.getInstance().getScheduler().scheduleRepeatingTask(new ShieldTask(), 20);
		Server.getInstance().getScheduler().scheduleRepeatingTask(new FireworkTask(), FireworkTask.TICK);
	}
	
	@EventHandler
	public void onReceive(DataPacketReceiveEvent event){
		DataPacket packet = event.getPacket();
		if(packet.pid() == UseItemPacket.NETWORK_ID){
			Player player = event.getPlayer();
			magic(player);
		}
	}
	
	@EventHandler
	public void onTouch(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(player.getInventory().getItemInHand().getId() == Item.APPLE){
			BurnMagic burn = (BurnMagic) MagicFactory.getInstance().getMagic(Magic.BURN, player, event.getBlock());
			Magic.run(burn);
		}
	}


	private void magic(Player player) {
		MagicFactory mf = MagicFactory.getInstance();
		switch(player.getInventory().getItemInHand().getId()){
		case Item.STICK:
			BeamMagic beam = (BeamMagic) mf.getMagic(Magic.BEAM, player, new Vector3(player.x, player.y+player.getEyeHeight(), player.z));
			beam.setYaw(player.getYaw());
			beam.setPitch(player.getPitch());
			Magic.run(beam);
			break;
		case Item.IRON_SWORD:
			ManeverGear3D.getInstance().run(player);
			break;
		case Item.TORCH:
			if(player.getInventory().getChestplate().getId() != Item.ELYTRA) break;
			player.setGliding(true);
			Firework.run(player);
			break;
		default:
			return;
		}
	}
}
