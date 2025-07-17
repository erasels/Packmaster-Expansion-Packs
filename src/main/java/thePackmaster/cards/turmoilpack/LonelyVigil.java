package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.turmoilpack.AbandonAction;
import thePackmaster.cards.spherespack.AbstractSpheresCard;

public class LonelyVigil extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("LonelyVigil");
    private static final int COST = 2;
    private static final int BLOCK = 12;
    private static final int UPGRADE_BLOCK = 2;
    private static final int EXTRA_BLOCK = 2;
    private static final int UPGRADE_EXTRA_BLOCK = 1;

    public LonelyVigil() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = EXTRA_BLOCK;
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
        this.upgradeMagicNumber(UPGRADE_EXTRA_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbandonAction(c -> c.type == CardType.ATTACK, l -> {
            this.addToTop(new GainBlockAction(p, this.block + this.magicNumber * l.size(), false));
        }));
    }
}
