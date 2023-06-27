package thePackmaster;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class ExpansionPacks implements PostInitializeSubscriber {

    private static ExpansionPacks thismod;
    public static final String modID = "expansionPacks";

    //You shouldn't be making much use of this. Use the SpireAnniversary5Mod class instead
    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public ExpansionPacks() {
        BaseMod.subscribe(this);
    }

    /*public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }*/

    public static void initialize() {
        thismod = new ExpansionPacks();

    }

    @Override
    public void receivePostInitialize() {

    }
}
