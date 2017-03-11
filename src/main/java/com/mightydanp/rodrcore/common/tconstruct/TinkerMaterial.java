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
        putIfAbsent("StainlessSteel", new CTinkersMaterialHandler(503, "stainlessSteel", 635, 1470, 2, 22, 1.2f, 6.0F, 5, 6.0F, 0.0f, EnumChatFormatting.WHITE.toString(), 0xffffff00, "ingotStainlessSteel", 1, false, 1, 0, 0, 3, 3, 3));
    	putIfAbsent("Titanium", new CTinkersMaterialHandler(504, "titanium", 650, 1500, 2, 25, 1.5f, 6.5F, 5, 6.2F, 0.0f, EnumChatFormatting.GRAY.toString(), 0xffffff00, "ingotTitanium", 1, false, 2, 0, 0, 3, 3, 3));
    	putIfAbsent("TungstenSteel", new CTinkersMaterialHandler(505, "tungstenSteel", 670, 1535, 2, 27, 1.7f, 6.7F, 5, 6.5F, 0.0f, EnumChatFormatting.DARK_BLUE.toString(), 0xffffff00, "ingotTungstenSteel", 1, false, 3, 0, 0, 3, 3, 3));
    }};
}