package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.turmoilpack.AbandonAction;

public class EternalToil extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("EternalToil");
    private static final int COST = 1;
    private static final int CARDS = 1;
    private static final int UPGRADE_CARDS = 1;

    public EternalToil() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = CARDS;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_CARDS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbandonAction(c -> !c.upgraded, l -> {
            for (AbstractCard c : l) {
                if (c.canUpgrade()) {
                    c.upgrade();
                    c.superFlash();
                    c.applyPowers();
                }
            }
        }));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
                    if (card.type == CardType.POWER) {
                        this.addToTop(new DiscardToHandAction(card));
                    }
                }
                this.isDone = true;
            }
        });
        this.addToBot(new BetterDiscardPileToHandAction(this.magicNumber));
    }
}
