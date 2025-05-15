package net.moonangel.tobacco_industry.compat;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEITobaccoIndustryPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TobaccoIndustry.MOD_ID, "jei_plugin");
    }


}
