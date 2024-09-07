package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.WindShieldAction;
import thePackmaster.powers.royaltypack.HiredSupportPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HiringSpecialists extends AbstractRoyaltyCard {
    public final static String ID = makeID("HiringSpecialists");

    public HiringSpecialists(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block =  5;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new GainBlockAction(abstractPlayer, block));
        Wiz.atb(new DrawCardAction(1));
        Wiz.atb(new ApplyPowerAction(abstractPlayer,
                abstractPlayer,
                new HiredSupportPower(abstractPlayer, 1)));

    }
}
