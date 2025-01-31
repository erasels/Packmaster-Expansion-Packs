package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.cards.green.Blur;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Bullet extends AbstractWitchStrikeCard {
    public final static String ID = makeID("Bullet");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Bullet() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        block = baseBlock = 4;
        exhaust = true;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doBlk(block);
    }

    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public String cardArtCopy() {
        return Blur.ID;
    }
}
