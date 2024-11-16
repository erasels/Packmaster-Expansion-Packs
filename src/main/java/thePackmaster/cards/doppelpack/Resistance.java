package thePackmaster.cards.doppelpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.AnonymousAction;
import thePackmaster.actions.doppelpack.SummonAction;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;

import java.util.HashMap;

public class Resistance extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Resistance.class.getSimpleName());

    public Resistance() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void hover() {
        super.hover();
        if (AbstractDungeon.player != null) {
            AbstractPlayer player = AbstractDungeon.player;
            if (player.hand.contains(this)) {
                if (affectedCards == null) {
                    affectedCards = new HashMap<>();
                }
                player.hand.group.stream()
                        .filter(c -> c.type == CardType.SKILL && c != this)
                        .findFirst()
                        .ifPresent(card -> {
                            if (!affectedCards.containsKey(card)) {
                                ShowDoppel showDoppel = new ShowDoppel();
                                affectedCards.put(card, showDoppel);
                                CardModifierManager.addModifier(card, showDoppel);
                            }
                        });
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
        this.addToBot(new DrawCardAction(1));
        this.addToBot(new WaitAction(0.1f));
        this.addToBot(new AnonymousAction(() -> p.hand.group.stream()
                .filter(c -> c.type == CardType.SKILL)
                .findFirst()
                .ifPresent(c -> {
                    AbstractDungeon.player.hand.empower(c);
                    SummonAction.doSummon(c, true);
                })));
    }
}
