package thePackmaster.cards.dancepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dancepack.DrawCallbackShuffleAction;

import static thePackmaster.util.Wiz.doDmg;

public class Updraft
    extends AbstractDanceCard
{
    public static final String ID = makeID(Updraft.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public Updraft()
    {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        setDamage(8);
    }

    @Override
    public void upp()
    {
        upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, damage, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addToBot(new DrawCallbackShuffleAction(c -> c.type == CardType.SKILL));
    }
}
