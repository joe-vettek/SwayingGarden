package xueluoanping.swayinggarden.data.lang;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import xueluoanping.swayinggarden.SwayingGarden;

public class Lang_EN extends LangHelper {
    public Lang_EN(PackOutput gen, ExistingFileHelper helper) {
        super(gen, helper, SwayingGarden.MOD_ID, "en_us");
    }


    @Override
    protected void addTranslations() {
        add("menu.create_vault_terminal.tittle","\"%s\"");
        add("hint.create_vault_terminal.not_open","Can not open the storage block!");
    }


}
