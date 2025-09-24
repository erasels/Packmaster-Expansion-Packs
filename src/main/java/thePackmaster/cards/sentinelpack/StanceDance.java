package thePackmaster.cards.sentinelpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ExpansionPacks;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class StanceDance extends AbstractSentinelCard {
    public final static String ID = makeID("StanceDance");

    public StanceDance() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new ChangeStanceAction(ExpansionPacks.getPackmasterStanceInstance(true)));
    }

    @Override
    public void upp() {
        exhaust = false;
    }

}


