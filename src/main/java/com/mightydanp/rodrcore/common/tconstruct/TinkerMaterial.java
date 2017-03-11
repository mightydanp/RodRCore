package com.mightydanp.rodrcore.common.tconstruct;

import java.util.HashMap;

import com.mightydanp.rodrcore.api.common.handler.CTinkersMaterialHandler;

import net.minecraft.util.EnumChatFormatting;

@SuppressWarnings("all")
public class TinkerMaterial {
    public static HashMap<String, CTinkersMaterialHandler> materials = new HashMap<String, CTinkersMaterialHandler>(){{
        putIfAbsent("Potato", new CTinkersMaterialHandler(500, "potato", 5000, 30, 5, 15, 5.0f, 10, 1, 1, 0.1f, EnumChatFormatting.YELLOW.toString(), 0xffffff00, "cropPotato", 1, false, 4, 1, 0, 3, 3, 3));
        putIfAbsent("Carrot", new CTinkersMaterialHandler(501, "carrot", 50, 10, 7, 5, 10.0f, 10, 1, 1, 0.1f, EnumChatFormatting.GOLD.toString(), 0xffffCC00, "cropCarrot", 1, false, 2, 1, 0));
        putIfAbsent("Quartz", new CTinkersMaterialHandler(502, "quartz", 50, 10, 4, 25, 1.5f, 10, 1, 1, 0.1f, EnumChatFormatting.GRAY.toString(), 0xffCCCCCC, "gemQuartz", 1, false, 2, 0, 1));
    }};
}