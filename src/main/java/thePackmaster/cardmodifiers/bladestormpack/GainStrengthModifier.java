package thePackmaster.cardmodifiers.bladestormpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

//REFS: Winterize & FrostOrbModifier & UIstrings (madsciencepack)
public class GainStrengthModifier extends AbstractMadScienceModifier {
    public GainStrengthModifier(int valueIn) {
        super(valueIn);
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("BladeStormModifiers")).TEXT[0] + this.value + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("BladeStormModifiers")).TEXT[1];
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for(int i = 0; i < this.value; ++i) {
            Wiz.applyToSelf(new StrengthPower(player, 1));
        }
    }

    public AbstractCardModifier makeCopy() {
        return new GainStrengthModifier(this.value);
    }
}
