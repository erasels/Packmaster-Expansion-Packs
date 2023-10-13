package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.magnetizepack.MagnetizeAction;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MagneticDomain extends AbstractMagnetizeCard {
    public final static String ID = makeID(MagneticDomain.class.getSimpleName());

    public MagneticDomain() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(cardStrings.EXTENDED_DESCRIPTION[0], cardsSelected -> {
            AbstractCard c = cardsSelected.get(0);
            addToTop(new DiscardSpecificCardAction(c));
            addToTop(new MagnetizeAction(c));
        }));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToTop(new SelectCardsAction(p.discardPile.group, cardStrings.EXTENDED_DESCRIPTION[1], card -> CardModifierManager.hasModifier(card, MagnetizedModifier.ID), cardsSelected -> {
                    AbstractCard c = cardsSelected.get(0);
                    p.discardPile.group.remove(c);
                    AbstractDungeon.getCurrRoom().souls.remove(c);
                    p.limbo.group.add(c);
                    c.current_y = -200.0F * Settings.scale;
                    c.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                    c.target_y = (float)Settings.HEIGHT / 2.0F;
                    c.targetAngle = 0.0F;
                    c.lighten(false);
                    c.drawScale = 0.12F;
                    c.targetDrawScale = 0.75F;
                    c.applyPowers();
                    addToTop(new UnlimboAction(c));
                    addToTop(new NewQueueCardAction(c, AbstractDungeon.getRandomMonster(), true, true));
                    if (!Settings.FAST_MODE)
                        addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                    else
                        addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                }));
                isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        exhaust = false;
    }
}