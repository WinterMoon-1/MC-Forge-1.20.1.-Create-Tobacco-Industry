package net.moonangel.tobacco_industry.datagen;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.minecraft.data.PackOutput;
import net.moonangel.tobacco_industry.TobaccoIndustry;

public class TILangProvider extends RegistrateLangProvider {
    private final static CreateRegistrate REGISTRATE = TobaccoIndustry.REGISTRATE;

    public TILangProvider(AbstractRegistrate<?> owner, PackOutput packOutput) {
        super(owner, packOutput);
    }

    public static void prepare() {
        // Creative mode tab
        REGISTRATE.addRawLang("creative_tab." + TobaccoIndustry.MOD_ID, "Tobacco Industry");
    }
}
