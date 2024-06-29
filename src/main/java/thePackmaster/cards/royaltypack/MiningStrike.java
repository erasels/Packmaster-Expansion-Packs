package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MiningStrike extends AbstractRoyaltyCard {

    public final static String ID = makeID("MiningStrike");


    public MiningStrike(){
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 15;
        baseMagicNumber = magicNumber = 15;
    }

    @Override
    public void upp() {
        this.upgradeDamage(5);
        this.upgradeMagicNumber(5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }
}
