package net.lldv.customrecipes.components.recipe;

import cn.nukkit.Server;
import cn.nukkit.inventory.FurnaceRecipe;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.Config;
import net.lldv.customrecipes.CustomRecipes;
import net.lldv.customrecipes.components.data.FurnaceData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FurnaceRecipes {

    public static HashMap<FurnaceRecipe, FurnaceData> resultData = new HashMap<>();

    public static void loadRecipes() {
        CustomRecipes.getInstance().saveResource("furnacerecipes.yml");
        Config r = new Config(CustomRecipes.getInstance().getDataFolder() + "/furnacerecipes.yml", Config.YAML);

        r.getSection("recipes").getAll().getKeys(false).forEach(s -> {
            int id = r.getInt("recipes." + s + ".result_id");
            int meta = r.getInt("recipes." + s + ".result_meta");
            int count = r.getInt("recipes." + s + ".result_amount");
            String name = null;
            String[] lore = null;
            List<Enchantment> enchantments = new ArrayList<>();
            if (r.exists("recipes." + s + ".result_name")) name = r.getString("recipes." + s + ".result_name");
            if (r.exists("recipes." + s + ".result_lore")) lore = r.getString("recipes." + s + ".result_lore").split("@");
            if (r.exists("recipes." + s + ".result_enchantments")) {
                r.getStringList("recipes." + s + ".result_enchantments").forEach(d -> {
                    String[] e = d.split(":");
                    enchantments.add(Enchantment.get(Integer.parseInt(e[0])).setLevel(Integer.parseInt(e[1])));
                });
            }
            Item output = Item.get(id, meta);
            Item input = Item.get(r.getInt("recipes." + s + ".input_id"), r.getInt("recipes." + s + ".input_meta"));

            FurnaceRecipe recipe = new FurnaceRecipe(output, input);
            resultData.put(recipe, new FurnaceData(id, meta, count, name, lore, enchantments));
            Server.getInstance().addRecipe(recipe);
        });
    }

}
