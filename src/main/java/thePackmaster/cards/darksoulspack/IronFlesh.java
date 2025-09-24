package thePackmaster.cards.darksoulspack;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class IronFlesh extends AbstractDarkSoulsCard {
    public final static String ID = makeID("IronFlesh");
    // intellij stuff skill, self, common, , , 15, 5, 2, 

    public IronFlesh() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 15;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        DrawReductionPower pow = new DrawReductionPower(p,magicNumber);
        ReflectionHacks.setPrivate(pow,DrawReductionPower.class,"justApplied",false);

        this.addToBot(new ApplyPowerAction(p, p, pow, magicNumber));

    }

    public void upp() {
        upgradeBlock(5);
    }
}