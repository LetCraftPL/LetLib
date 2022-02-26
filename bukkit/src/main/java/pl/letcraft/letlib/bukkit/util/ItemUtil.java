package pl.letcraft.letlib.bukkit.util;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class ItemUtil {

    private ItemUtil() {}

    public static int getAmountOf(@NotNull Material material, @NotNull Player player) {
        int amount = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null || item.getType() != material) {
                continue;
            }

            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.getBoolean("special")) {
                continue;
            }

            amount += item.getAmount();
        }
        return amount;
    }

    public static void removeItemsOfType(@NotNull Material material, int amount, @NotNull Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (amount <= 0) {
                break;
            }

            if (item == null || item.getType() != material) {
                continue;
            }

            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.getBoolean("special")) {
                continue;
            }

            int newAmount = item.getAmount() - amount;
            amount -= item.getAmount();

            if (newAmount <= 0) {
                player.getInventory().removeItem(item);
            } else {
                item.setAmount(newAmount);
            }
        }
    }

    public static boolean canContain(@NotNull Inventory inventory, @NotNull ItemStack itemStack, int amount) {
        return canContain(inventory, itemStack.asQuantity(amount));
    }

    public static boolean canContain(@NotNull Inventory inventory, @NotNull ItemStack itemStack) {
        boolean can = true;
        if (inventory.firstEmpty() == -1) {
            int itemAmount = itemStack.getAmount();
            if (inventory.contains(itemStack.getType())) {
                int amount = 0;
                ItemStack[] contents = inventory.getContents();
                for (ItemStack is : contents) {
                    if (itemStack.isSimilar(is)) {
                        amount = is.getAmount();
                    }
                }

                if (amount + itemAmount > 64) {
                    can = false;
                }
            } else {
                can = false;
            }
        }
        return can;
    }


    public static void giveItem(@NotNull Player player, @NotNull ItemStack itemStack) {
        Map<Integer, ItemStack> overflow = player.getInventory().addItem(itemStack);
        overflow.forEach((i, is) -> player.getWorld().dropItemNaturally(player.getLocation(), is));
    }
}
