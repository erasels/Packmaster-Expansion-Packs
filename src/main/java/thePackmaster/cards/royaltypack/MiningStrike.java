package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.MiningStrikeAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MiningStrike extends AbstractRoyaltyCard {

    public final static String ID = makeID("MiningStrike");


    public MiningStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        baseDamage = damage = 15;
        baseMagicNumber = magicNumber = 15;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeDamage(5);
        this.upgradeMagicNumber(5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new MiningStrikeAction(
                abstractMonster,
                new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn),
                magicNumber));
    }
}
