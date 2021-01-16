package net.ddns.maikeio.elytra_auto_equipper;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Equipper {
	
	int chestSlot;

	ArrayList<Player> flyingPlayer;
	
	public Equipper() {
		chestSlot  = 38;
		flyingPlayer  = new ArrayList<Player>();
	}
	
	private boolean isWearingElytra(Inventory inv) {
		ItemStack is = inv.getItem(chestSlot);
		if (is == null)
			return false;
		if (is.getType() == Material.ELYTRA)
			return true;
		return false;
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean StartGliding(Player player) {
		if (player.getItemInHand().getType() == Material.FIREWORK_ROCKET
				&& !isWearingElytra((Inventory) player.getInventory()) && !player.isOnGround()
				&& !flyingPlayer.contains(player) && player.getInventory().contains(Material.ELYTRA)) {
			Inventory inv = player.getInventory();
			int size = inv.getSize();
			for (int slot = 0; slot < size; slot++) {
				ItemStack is = inv.getItem(slot);
				if (is == null)
					continue;
				if (is.getType() == Material.ELYTRA) {

					if (inv.getItem(chestSlot) != null)
						inv.setItem(slot, inv.getItem(chestSlot));
					else
						inv.setItem(slot, new ItemStack(Material.AIR));
					inv.setItem(chestSlot, is);
					flyingPlayer.add(player);
					player.setGliding(true);
					return true;

				}
			}
		} return false;
	}
	
	@SuppressWarnings("deprecation")
	public void Landing(Player player) {
		if (player.isOnGround() && flyingPlayer.contains(player)) {
			Inventory inv = player.getInventory();
			int size = player.getInventory().getSize();
			for (int slot = 0; slot < size; slot++) {
				ItemStack is = inv.getItem(slot);
				if (is == null)
					continue;
				if (is.getType() == Material.CHAINMAIL_CHESTPLATE || is.getType() == Material.DIAMOND_CHESTPLATE
						|| is.getType() == Material.GOLDEN_CHESTPLATE || is.getType() == Material.IRON_CHESTPLATE
						|| is.getType() == Material.LEATHER_CHESTPLATE
						|| is.getType() == Material.NETHERITE_CHESTPLATE) {

					inv.setItem(slot, inv.getItem(chestSlot));
					inv.setItem(chestSlot, is);
					flyingPlayer.remove(player);
					return;

				}
			}
			flyingPlayer.remove(player);
		}
	}
}
