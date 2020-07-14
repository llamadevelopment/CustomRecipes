package net.lldv.customrecipes.components.data;

import cn.nukkit.item.enchantment.Enchantment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class FurnaceData {

    private final int id;
    private final int meta;
    private final int count;
    private final String name;
    private final String[] lore;
    private final List<Enchantment> enchantments;

}
