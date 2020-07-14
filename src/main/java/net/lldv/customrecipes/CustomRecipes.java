package net.lldv.customrecipes;

import cn.nukkit.plugin.PluginBase;
import net.lldv.customrecipes.listeners.EventListener;
import net.lldv.customrecipes.components.recipe.FurnaceRecipes;
import net.lldv.customrecipes.components.recipe.ShapedRecipes;

public class CustomRecipes extends PluginBase {

    private static CustomRecipes instance;

    @Override
    public void onEnable() {
        instance = this;
        try {
            ShapedRecipes.loadRecipes();
            FurnaceRecipes.loadRecipes();
            getServer().getPluginManager().registerEvents(new EventListener(), this);
            getLogger().info("§aCustomRecipes successfully started.");
        } catch (Exception e) {
            e.printStackTrace();
            getLogger().error("§4Failed to load CustomRecipes.");
        }
    }

    public static CustomRecipes getInstance() {
        return instance;
    }
}
