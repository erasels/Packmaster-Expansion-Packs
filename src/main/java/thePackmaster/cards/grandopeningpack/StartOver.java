package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.ForceField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.grandopening.StartupTriggerAction;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class StartOver extends AbstractGrandOpeningCard {
    public final static String ID = makeID("StartOver");

    public StartOver() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 3;
        tags.add(CardTags.HEALING);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        atb(new ShuffleAllAction());
        atb(new ShuffleAction(AbstractDungeon.player.drawPile, false));
        atb(new DrawCardAction(magicNumber));
        atb(new StartupTriggerAction());
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}