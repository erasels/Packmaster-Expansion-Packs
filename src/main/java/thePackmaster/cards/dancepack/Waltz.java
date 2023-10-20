package thePackmaster.cards.dancepack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.dancepack.DamageDanceMod;
import thePackmaster.util.dancepack.JediUtil;

public class Waltz
        extends AbstractDanceCard
{
    public static final String ID = makeID(Waltz.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public Waltz()
    {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        setBlock(10);
    }

    @Override
    public void upp()
    {
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new GainBlockAction(p, block));
        addToBot(new FindCardForAddModifierAction(new DamageDanceMod(magicNumber), 1, upgraded, p.hand, c -> c.type == CardType.ATTACK));
    }
}