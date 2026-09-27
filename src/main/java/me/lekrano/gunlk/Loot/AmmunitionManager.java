package me.lekrano.gunlk.Loot;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import java.util.List;
import java.util.Map;

public class AmmunitionManager {
    private static final RandomSource random = RandomSource.create();

    private static final Map<String, String> GUN_AMMUNITION = Map.<String, String>ofEntries(
            // PISTOLS
            Map.entry("tacz:glock_17",          "tacz:9mm"          ),
            Map.entry("tacz:deagle",            "tacz:50ae"         ),
            Map.entry("tacz:cz75",              "tacz:9mm"          ),
            Map.entry("tacz:deagle_golden",     "tacz:357mag"       ),
            Map.entry("tacz:p320",              "tacz:45acp"        ),
            Map.entry("tacz:m1911",             "tacz:45acp"        ),
            Map.entry("tacz:b93r",              "tacz:9mm"          ),
            Map.entry("tacz:timeless50",        "tacz:50ae"         ),
            Map.entry("tacz:taurus943",         "tacz:22wmr"        ),
            Map.entry("tacz:rhino357",          "tacz:357mag"       ),
            Map.entry("tacz:lonetrail",         "tacz:30_06"        ),
            Map.entry("tacz:hk_mk23",           "tacz:45acp"        ),
            Map.entry("tacz:taurus500",         "tacz:500mag"       ),
            Map.entry("tacz:m9a4",              "tacz:9mm"          ),
            // SNIPERS
            Map.entry("tacz:ai_awp",            "tacz:338"          ),
            Map.entry("tacz:m95",               "tacz:50bmg"        ),
            Map.entry("tacz:m700",              "tacz:30_06"        ),
            Map.entry("tacz:m107",              "tacz:50bmg"        ),
            Map.entry("tacz:springfield1873",   "tacz:45_70"        ),
            Map.entry("tacz:kar98",             "tacz:792x57"       ),
            // RIFLES
            Map.entry("tacz:ak47",              "tacz:762x39"       ),
            Map.entry("tacz:m4a1",              "tacz:556x45"       ),
            Map.entry("tacz:hk_g3",             "tacz:308"          ),
            Map.entry("tacz:sks_tactical",      "tacz:762x39"       ),
            Map.entry("tacz:scar_h",            "tacz:308"          ),
            Map.entry("tacz:scar_l",            "tacz:556x45"       ),
            Map.entry("tacz:m16a1",             "tacz:556x45"       ),
            Map.entry("tacz:m16a4",             "tacz:556x45"       ),
            Map.entry("tacz:hk416d",            "tacz:556x45"       ),
            Map.entry("tacz:aug",               "tacz:556x45"       ),
            Map.entry("tacz:mk14",              "tacz:308"          ),
            Map.entry("tacz:type_81",           "tacz:762x39"       ),
            Map.entry("tacz:qbz_95",            "tacz:58x42"        ),
            Map.entry("tacz:g36k",              "tacz:556x45"       ),
            Map.entry("tacz:spr15hb",           "tacz:556x45"       ),
            Map.entry("tacz:qbz_191",           "tacz:58x42"        ),
            Map.entry("tacz:fn_fal",            "tacz:308"          ),
            // SHOTGUNS
            Map.entry("tacz:db_short",          "tacz:12g"          ),
            Map.entry("tacz:db_long",           "tacz:12g"          ),
            Map.entry("tacz:m870",              "tacz:12g"          ),
            Map.entry("tacz:aa12",              "tacz:12g"          ),
            Map.entry("tacz:spas_12",           "tacz:12g"          ),
            Map.entry("tacz:m1014",             "tacz:12g"          ),
            // SMG
            Map.entry("tacz:hk_mp5a5",          "tacz:9mm"          ),
            Map.entry("tacz:uzi",               "tacz:9mm"          ),
            Map.entry("tacz:vector45",          "tacz:45acp"        ),
            Map.entry("tacz:ump45",             "tacz:45acp"        ),
            Map.entry("tacz:p90",               "tacz:57x28"        ),
            // HEAVY WEAPONS
            Map.entry("tacz:rpg7",              "tacz:rpg_rocket"   ),
            Map.entry("tacz:m320",              "tacz:40mm"         ),
            // MACHINE GUN
            Map.entry("tacz:m249",              "tacz:556x45"       ),
            Map.entry("tacz:rpk",               "tacz:762x39"       ),
            Map.entry("tacz:minigun",           "tacz:308"          ),
            Map.entry("tacz:fn_evolys",         "tacz:308"          )
    );
    private static final Map<String, AmmunitionInfo> AMMUNITION = Map.ofEntries(
            Map.entry("tacz:22wmr",         new AmmunitionInfo("tacz:22wmr",     1 ,80)),
            Map.entry("tacz:9mm",           new AmmunitionInfo("tacz:9mm",       1 ,80)),
            Map.entry("tacz:45acp",         new AmmunitionInfo("tacz:45acp",     1 ,80)),
            Map.entry("tacz:57x28",         new AmmunitionInfo("tacz:57x28",     3 ,30)),
            Map.entry("tacz:556x45",        new AmmunitionInfo("tacz:556x45",    2 ,60)),
            Map.entry("tacz:58x42",         new AmmunitionInfo("tacz:58x42",     2 ,60)),
            Map.entry("tacz:762x39",        new AmmunitionInfo("tacz:762x39",    2 ,60)),
            Map.entry("tacz:308",           new AmmunitionInfo("tacz:308",       3 ,50)),
            Map.entry("tacz:792x57",        new AmmunitionInfo("tacz:792x57",    3 ,50)),
            Map.entry("tacz:357mag",        new AmmunitionInfo("tacz:357mag",    5 ,40)),
            Map.entry("tacz:50ae",          new AmmunitionInfo("tacz:50ae",      5 ,40)),
            Map.entry("tacz:500mag",        new AmmunitionInfo("tacz:500mag",    5 ,40)),
            Map.entry("tacz:45_70",         new AmmunitionInfo("tacz:45_70",     5 ,40)),
            Map.entry("tacz:338",           new AmmunitionInfo("tacz:338",       5 ,40)),
            Map.entry("tacz:30_06",         new AmmunitionInfo("tacz:30_06",     5 ,40)),
            Map.entry("tacz:50bmg",         new AmmunitionInfo("tacz:50bmg",     5 ,40)),
            Map.entry("tacz:40mm",          new AmmunitionInfo("tacz:40mm",      8 ,20)),
            Map.entry("tacz:rpg_rocket",    new AmmunitionInfo("tacz:rpg_rocket",10,20)),
            Map.entry("tacz:12g",           new AmmunitionInfo("tacz:12g",       4 ,50))
    );

    public static void giveAmmunition(Player player) {
        List<AmmunitionInfo> ammunitions = new java.util.ArrayList<>(List.of());
        for (ItemStack item : player.getInventory().items) {

            CompoundTag tag = item.getTag();
            if (tag == null) continue;
            String gunId = tag.getString("GunId");
            String ammoId = GUN_AMMUNITION.get(gunId);
            if (ammoId == null) continue;

            ammunitions.add(AMMUNITION.get(ammoId));
        }

        if (ammunitions.isEmpty()) return;

        int budget = 30;

        int totalWeight = 0;
        for (AmmunitionInfo ammo : ammunitions) {
            totalWeight += ammo.weight();
        }

        while (budget > 0) {
            int roll = random.nextInt(totalWeight);

            AmmunitionInfo selectedAmmunition = null;
            for (AmmunitionInfo ammo : ammunitions) {
                roll -= ammo.weight();
                if (roll < 0) {
                    selectedAmmunition = ammo;
                    break;
                }
            }

            assert selectedAmmunition != null;
            if (selectedAmmunition.value() > budget) break;
            budget -= selectedAmmunition.value();

            ItemStack ammunition = new ItemStack(
                    BuiltInRegistries.ITEM.get(
                            ResourceLocation.tryParse("tacz:ammo")
                    ),
                    1
            );
            ammunition.getOrCreateTag().putString("AmmoId", selectedAmmunition.ammunitionId());
            player.addItem(ammunition);
        }
    }

}
