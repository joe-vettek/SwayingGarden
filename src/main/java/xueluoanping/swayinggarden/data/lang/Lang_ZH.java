package xueluoanping.swayinggarden.data.lang;


import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import xueluoanping.swayinggarden.SwayingGarden;

public class Lang_ZH extends LangHelper {
    public Lang_ZH(PackOutput gen, ExistingFileHelper helper) {
        super(gen, helper, SwayingGarden.MOD_ID, "zh_cn");
    }


    @Override
    protected void addTranslations() {
        add("menu.create_vault_terminal.tittle","\"%s\"");
        add("hint.create_vault_terminal.not_open","无法打开存储空间！");
    }


}
