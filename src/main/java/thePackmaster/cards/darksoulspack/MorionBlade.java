package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MorionBlade extends AbstractDarkSoulsCard{
    public final static String ID = makeID("MorionBlade");

    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 2;

    public MorionBlade(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = 0;
    }

    @Override
    public void applyPowers() {
        magicNumber = Wiz.countDebuffs(Wiz.p());
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.applyPowers();

        if (magicNumber == 0) {
            this.rawDescription = cardStrings.DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < Wiz.countDebuffs(p) + 1; i++)
            dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upp(){
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
