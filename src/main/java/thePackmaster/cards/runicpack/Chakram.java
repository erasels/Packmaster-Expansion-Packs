package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.stances.aggressionpack.AggressionStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Chakram extends AbstractRunicCard {

    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPG_DMG = 3;
    public final static String ID = makeID("Chakram");


    public Chakram() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = DAMAGE;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        this.addToBot(new ChangeStanceAction(new AggressionStance()));
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DMG);
    }
}
