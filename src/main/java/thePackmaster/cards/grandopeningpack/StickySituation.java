package thePackmaster.cards.grandopeningpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class StickySituation extends AbstractGrandOpeningCard implements StartupCard {
    public final static String ID = makeID("StickySituation");

    public StickySituation() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 7;
        tags.add(CardTags.HEALING);
        exhaust = true;
        cardsToPreview = new Slimed();
    }

    public void triggerWhenDrawn() {
        this.addToTop(new MakeTempCardInHandAction(cardsToPreview.makeCopy(), 1));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new HealAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));
        return true;
    }
}