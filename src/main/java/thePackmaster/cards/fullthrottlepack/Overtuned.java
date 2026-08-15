package thePackmaster.cards.fullthrottlepack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.fullthrottlepack.ApplyRandomNitroAction;
import thePackmaster.patches.fullthrottlepack.AddToHandPatches;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Overtuned extends AbstractFullThrottleCard{
    public final static String ID = makeID("Overtuned");

    public Overtuned() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.shuffleBackIntoDrawPile = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyRandomNitroAction(magicNumber, AbstractDungeon.player.hand));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}