package thePackmaster.patches.magnetizepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.*;
import org.clapper.util.classutil.*;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.util.Wiz;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.util.ArrayList;

@SpirePatch(clz = CardCrawlGame.class, method = SpirePatch.CONSTRUCTOR)
public class OnDiscardPatch {
    public static void Raw(CtBehavior ctBehavior) throws NotFoundException {
        ClassFinder finder = new ClassFinder();

        finder.add(new File(Loader.STS_JAR));

        for (ModInfo modInfo : Loader.MODINFOS) {
            if (modInfo.jarURL != null) {
                try {
                    finder.add(new File(modInfo.jarURL.toURI()));
                } catch (URISyntaxException e) {
                    // do nothing
                }
            }
        }

        // Get all classes for AbstractPotion
        ClassFilter filter = new AndClassFilter(
                new NotClassFilter(new InterfaceOnlyClassFilter()),
                new ClassModifiersClassFilter(Modifier.PUBLIC),
                new OrClassFilter(
                        new org.clapper.util.classutil.SubclassClassFilter(AbstractCard.class),
                        (classInfo, classFinder) -> classInfo.getClassName().equals(AbstractCard.class.getName())
                )
        );

        ArrayList<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass ctClass = ctBehavior.getDeclaringClass().getClassPool().get(classInfo.getClassName());

            try {
                CtMethod[] methods = ctClass.getDeclaredMethods();
                for (CtMethod m : methods) {
                    if (m.getName().equals("triggerOnManualDiscard")) {
                        m.insertAfter("{" +
                                OnDiscardPatch.class.getName() + ".triggerOnDiscard(this);" +
                                "}");
                    }
                }
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        }
    }

    public static void triggerOnDiscard(AbstractCard card) {
        for (AbstractCardModifier mod : CardModifierManager.getModifiers(card, MagnetizedModifier.ID))
            ((MagnetizedModifier)mod).onDiscarded(card);

        for (AbstractPower p : Wiz.adp().powers)
            if (p instanceof DiscardListener)
                ((DiscardListener) p).onManualDiscard(card);
    }

    public interface DiscardListener {
        void onManualDiscard (AbstractCard card);
    }
}