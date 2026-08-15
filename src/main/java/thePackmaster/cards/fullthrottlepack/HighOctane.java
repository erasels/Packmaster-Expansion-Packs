package thePackmaster.cards.fullthrottlepack;


import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.actions.fullthrottlepack.ApplyRandomNitroAction;
import thePackmaster.cardmodifiers.fullthrottlepack.NitroMod;
import thePackmaster.patches.fullthrottlepack.AddToHandPatches;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;


public class HighOctane extends AbstractFullThrottleCard {
    public final static String ID = makeID("HighOctane");

    public HighOctane() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(adp(), adp(), new VigorPower(adp(), magicNumber * AbstractDungeon.player.hand.size())));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type == CardType.ATTACK) {
                        CardModifierManager.addModifier(c, new NitroMod());
                    }
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}


