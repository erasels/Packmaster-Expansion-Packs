package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cross extends AbstractGrandOpeningCard {
    public final static String ID = makeID("Cross");

    public Cross() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        baseDamage = damage = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i<magicNumber; i++){
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            if(i+1<magicNumber){
                dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
                i++;
            }
        }
        ArrayList<Iterator<AbstractCard>> vars = new ArrayList();
        vars.add(AbstractDungeon.player.hand.group.iterator());
        vars.add(AbstractDungeon.player.drawPile.group.iterator());
        vars.add(AbstractDungeon.player.discardPile.group.iterator());
        AbstractCard c;
        for(Iterator<AbstractCard> var : vars) {
            while (var.hasNext()) {
                c = var.next();
                if (c instanceof Cross) {
                    c.magicNumber++;
                }
            }
        }

    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}
