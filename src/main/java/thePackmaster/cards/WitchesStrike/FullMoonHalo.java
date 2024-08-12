package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.cardmodifiers.InscribedMod;
import thePackmaster.cardmodifiers.witchesstrike.WickedModifier;
import thePackmaster.powers.witchesstrikepack.LoseFocusPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FullMoonHalo extends AbstractWitchStrikeCard {
    public final static String ID = makeID("FullMoonHalo");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FullMoonHalo() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 12;
        baseMagicNumber = 4;
        CardModifierManager.addModifier(this, new WickedModifier(2));
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(wickedString.TEXT[0],wickedString.TEXT[1]));
        return retVal;
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            int orbs = 0;
            for (AbstractOrb o : p.orbs) {
                if (!(o instanceof EmptyOrbSlot)) {
                    orbs++;
                }
            }
            return orbs >= 2;
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new EvokeOrbAction(2));
        blck();
        Wiz.applyToSelf(new FocusPower(p,magicNumber));
        Wiz.applyToSelf(new LoseFocusPower(p,magicNumber));
    }

    public void upp() {
        upgradeBlock(4);
    }
    @Override
    public String cardArtCopy() {
        return VoidCard.ID;
    }
}
