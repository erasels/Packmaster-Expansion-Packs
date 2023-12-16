package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.ThrowSoulstonesAusterity;
import thePackmaster.cards.royaltypack.optioncards.ThrowSoulstonesTribute;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ThrowSoulstones extends AbstractRoyaltyCard {

    public final static String ID = makeID("ThrowSoulstones");
    public final static int TRIBUTE_DAMAGE = 15;
    public final static int AUSTERITY_DAMAGE = 5;


    public ThrowSoulstones(){
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = TRIBUTE_DAMAGE;
        baseSecondDamage = secondDamage = AUSTERITY_DAMAGE;
    }

    @Override
    public void upp() {
        this.upgradeDamage(5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractRoyaltyCard tsTributeChoiceCard = new ThrowSoulstonesTribute(this, damage);
        AbstractRoyaltyCard tsAusterityChoiceCard = new ThrowSoulstonesAusterity(secondDamage);
        for (int i = 0; i < this.timesUpgraded; i++){
            tsTributeChoiceCard.upgrade();
            tsAusterityChoiceCard.upgrade();
        }

        Wiz.atb(new TributeOrAusterityAction(tsTributeChoiceCard, tsAusterityChoiceCard));
    }
}
