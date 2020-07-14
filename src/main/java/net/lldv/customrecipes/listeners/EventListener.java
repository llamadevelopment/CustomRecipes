package net.lldv.customrecipes.listeners;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.FurnaceSmeltEvent;
import cn.nukkit.inventory.FurnaceRecipe;
import cn.nukkit.item.Item;
import net.lldv.customrecipes.components.data.FurnaceData;
import net.lldv.customrecipes.components.recipe.FurnaceRecipes;

public class EventListener implements Listener {

    @EventHandler
    public void on(FurnaceSmeltEvent event) {
        FurnaceRecipe recipe = Server.getInstance().getCraftingManager().matchFurnaceRecipe(event.getSource());
        FurnaceData data = FurnaceRecipes.resultData.get(recipe);
        if (data != null) {
            Item item = Item.get(data.getId(), data.getMeta());
            if (event.getResult().getCount() == 0) item.setCount(data.getCount() - 1);
            else item.setCount(event.getResult().getCount() + data.getCount() - 1);
            if (data.getName() != null) item.setCustomName(data.getName());
            if (data.getLore() != null) item.setLore(data.getLore());
            if (data.getEnchantments() != null) data.getEnchantments().forEach(item::addEnchantment);
            event.setResult(item);
        }
    }

}
