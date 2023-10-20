package thePackmaster.cards.dancepack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import java.util.stream.Collectors;

public class Enhappe extends AbstractDanceCard {

    public final static String ID = makeID(Enhappe.class.getSimpleName());

    public Enhappe(boolean shouldPreview) {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        exhaust = true;
        if (shouldPreview) cardsToPreview = new Glisser(false);
    }

    public Enhappe()
    {
        this(true);
    }

    @Override
    public void upp() {
        if (cardsToPreview != null)
            cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new SelectCardsInHandAction(
                cardStrings.EXTENDED_DESCRIPTION[0],
                c -> {
                    if (upgraded) return c.type == CardType.ATTACK;
                    AbstractCard tmp = Wiz.getRandomItem(Wiz.p().hand.group.stream().filter(crd -> crd.type == CardType.ATTACK).collect(Collectors.toList()));
                    return c==tmp;
                },
                list -> list.forEach(c -> {
                    c.freeToPlayOnce = true;
                    AbstractMonster am = Wiz.getRandomEnemy();
                    c.calculateCardDamage(am);
                    addToTop(new NewQueueCardAction(c, am));
                    addToTop(new UnlimboAction(c));
                })
        ));
        addToBot(new MakeTempCardInDiscardAction(cardsToPreview, 1));
    }
}
