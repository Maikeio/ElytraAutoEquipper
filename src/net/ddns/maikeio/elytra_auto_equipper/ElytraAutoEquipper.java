package net.ddns.maikeio.elytra_auto_equipper;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ElytraAutoEquipper extends JavaPlugin implements Listener {
	
	Equipper equ;
	@Override
	public void onEnable() {

		equ = new Equipper();;
		// adds Listener for AutoCrafter
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void EntityToggleGlideEvent(org.bukkit.event.entity.EntityToggleGlideEvent event) {
		if (event.getEntity().getType() != EntityType.PLAYER)
			return;

		equ.Landing((Player) event.getEntity());
	}

	@EventHandler
	public void PlayerInteractEvent(org.bukkit.event.player.PlayerInteractEvent event) {
		event.setCancelled(equ.StartGliding(event.getPlayer()));
	}
}
