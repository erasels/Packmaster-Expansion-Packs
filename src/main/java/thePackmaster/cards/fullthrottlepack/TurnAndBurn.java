package thePackmaster.cards.fullthrottlepack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.actions.fullthrottlepack.ApplyRandomNitroAction;
import thePackmaster.patches.fullthrottlepack.AddToHandPatches;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;


public class TurnAndBurn extends AbstractFullThrottleCard implements AddToHandPatches.AddToHandInterface {
    public final static String ID = makeID("TurnAndBurn");

    public TurnAndBurn() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.baseSecondMagic = 1;
        this.secondMagic = this.baseSecondMagic;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(m, p, new IgnitePower(m, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
    }

    @Override
    public void onAddCard(CardGroup group, AbstractCard card) {
        if (card == this) {
            atb(new ApplyRandomNitroAction(secondMagic, AbstractDungeon.player.hand));
        }
    }
}


