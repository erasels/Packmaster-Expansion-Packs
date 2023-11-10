package thePackmaster.cards.discopack;


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.actions.discopack.ElectricAction;
import thePackmaster.actions.discopack.SpecificToHandFromDiscardAction;
import thePackmaster.vfx.discopack.ElectricFX;

import java.util.Iterator;
import java.util.function.Predicate;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class ElectricSlide extends AbstractSmoothCard {
    public static final String ID = makeID("ElectricSlide");

    public ElectricSlide() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = this.block = 6;
        this.baseMagicNumber = magicNumber = 2;
        this.baseSecondMagic = secondMagic = 1;
    }
    public String text = cardStrings.EXTENDED_DESCRIPTION[0];

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(magicNumber, text, true, true, card -> true,(list) -> atb(new ElectricAction(list, block))));
        }

    public void triggerOnManualDiscard() {
        att(new SpecificToHandFromDiscardAction(this));
        this.addToBot(new DrawCardAction(AbstractDungeon.player, secondMagic));
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(1);
    }
}
