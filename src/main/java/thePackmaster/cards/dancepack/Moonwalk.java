package thePackmaster.cards.dancepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.actions.dancepack.DrawCallbackShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Moonwalk
        extends AbstractDanceCard
{
    public static final String ID = makeID(Moonwalk.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 1;

    public Moonwalk()
    {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        setMN(2);
    }

    @Override
    public void upp()
    {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
        addToBot(new DrawCallbackShuffleAction(c -> c.type == CardType.ATTACK));
    }
}