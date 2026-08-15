package thePackmaster.cardmodifiers.fullthrottlepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

public class NitroMod extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID("NitroMod");
    private static UIStrings nitroString = CardCrawlGame.languagePack.getUIString(ID);

    private static final Texture tex = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/ui/nitroIcon.png");

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).render(card);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            Wiz.atb(new DrawCardAction(1));
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    CardModifierManager.removeModifiersById(card, NitroMod.ID, true);
                    this.isDone = true;
                }
            });
        }
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public void onInitialApplication(AbstractCard card) {
        card.flash();
    }

    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card, NitroMod.ID)) return false;
        return super.shouldApply(card);
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return nitroString.TEXT[0] + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new NitroMod();
    }
}
