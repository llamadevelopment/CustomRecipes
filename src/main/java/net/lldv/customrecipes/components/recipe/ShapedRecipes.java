package net.lldv.customrecipes.components.recipe;

import cn.nukkit.Server;
import cn.nukkit.inventory.ShapedRecipe;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.Config;
import net.lldv.customrecipes.CustomRecipes;

import java.util.ArrayList;
import java.util.HashMap;

public class ShapedRecipes {

    public static void loadRecipes() {
        CustomRecipes.getInstance().saveResource("craftrecipes.yml");
        Config r = new Config(CustomRecipes.getInstance().getDataFolder() + "/craftrecipes.yml", Config.YAML);

        r.getSection("recipes").getAll().getKeys(false).forEach(s -> {
            Item item = Item.get(r.getInt("recipes." + s + ".id"), r.getInt("recipes." + s + ".meta"), r.getInt("recipes." + s + ".amount"));
            if (r.exists("recipes." + s + ".name")) item.setCustomName(r.getString("recipes." + s + ".name"));
            if (r.exists("recipes." + s + ".lore")) item.setLore(r.getString("recipes." + s + ".lore").split("@"));
            if (r.exists("recipes." + s + ".enchantments")) {
                r.getStringList("recipes." + s + ".enchantments").forEach(d -> {
                    String[] e = d.split(":");
                    item.addEnchantment(Enchantment.get(Integer.parseInt(e[0])).setLevel(Integer.parseInt(e[1])));
                });
            }

            String[] shape = r.getString("recipes." + s + ".shape").split(":");
            HashMap<Character, Item> identifiers = new HashMap<>();
            for (String f : r.getString("recipes." + s + ".identifiers").split(",")) {
                String[] g = f.split(":");
                identifiers.put(g[0].toCharArray()[0], Item.get(Integer.parseInt(g[1]), Integer.parseInt(g[2])));
            }

            ShapedRecipe recipe = new ShapedRecipe(s, 1, item, shape, identifiers, new ArrayList<>());
            Server.getInstance().addRecipe(recipe);
            Server.getInstance().getCraftingManager().rebuildPacket();
        });
    }

}
