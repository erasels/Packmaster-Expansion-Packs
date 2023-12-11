package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Vanguard extends AbstractIntrigueCard {
    public final static String ID = makeID("Vanguard");

    private static final int BLOCK = 8;
    private static final int BLOCK_UP = 3;

    public Vanguard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.rarity == CardRarity.RARE || (c.rarity == CardRarity.UNCOMMON && upgraded)) {
            Wiz.atb(new NewQueueCardAction(this, true, false, true));
        }
    }

    public void upp() {
        upgradeBlock(BLOCK_UP);
    }
}
