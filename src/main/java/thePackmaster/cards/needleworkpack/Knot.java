package thePackmaster.cards.needleworkpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.needlework.StitchAction;
import thePackmaster.powers.needlework.BindPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Knot extends AbstractNeedleworkCard {
    public final static String ID = makeID("Knot");

    public Knot() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

        baseMagicNumber = magicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (target != null) {
            addToBot(new ApplyPowerAction(target, p, new BindPower(target, this.magicNumber), this.magicNumber, true));
        }
        addToBot(new StitchAction(this));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}
