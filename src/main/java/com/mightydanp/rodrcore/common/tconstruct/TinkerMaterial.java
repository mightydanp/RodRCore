package com.mightydanp.rodrcore.common.tconstruct;

import java.util.HashMap;

import com.mightydanp.rodrcore.api.common.handler.CTinkersMaterialHandler;

import net.minecraft.util.EnumChatFormatting;

@SuppressWarnings("all")
public class TinkerMaterial {
    public static HashMap<String, CTinkersMaterialHandler> materials = new HashMap<String, CTinkersMaterialHandler>(){{
        putIfAbsent("Stainlesssteel", new CTinkersMaterialHandler(500, "stainlesssteel", 635, 1470, 2, 22, 1.2f, 6F, 5, 6, 0.0f, EnumChatFormatting.WHITE.toString(), 0xC9C0BB, "ingotStainlessSteel", 1, false, 1, 0, 0));
        putIfAbsent("Titanium", new CTinkersMaterialHandler(501, "titanium", 650, 1500, 2, 25, 1.5f, 6.5F, 5, 6, 0.0f, EnumChatFormatting.GRAY.toString(), 0x73787E, "ingotTitanium", 1, false, 2, 0, 0));
        putIfAbsent("Tungstensteel", new CTinkersMaterialHandler(502, "tungstensteel", 670, 1535, 2, 27, 1.7f, 6, 5, 6, 0.0f, EnumChatFormatting.DARK_BLUE.toString(), 0x555589, "ingotTungstenSteel", 1, false, 3, 0, 0));
    }};
}