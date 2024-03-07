package thePackmaster.cards.grandopeningpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Starring extends AbstractGrandOpeningCard {
    public final static String ID = makeID("Starring");

    public Starring() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseBlock = block = 8;
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {
        blck();
        for(int i = 0; i<magicNumber; i++) {
            AbstractCard upC = Wiz.getRandomItem(Wiz.getCardsMatchingPredicate(c -> c instanceof AbstractPackmasterCard && c instanceof StartupCard, true)).makeCopy();
            addToBot(new MakeTempCardInHandAction(upC, false));
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    ((StartupCard) upC).atBattleStartPreDraw();
                    atb(new ShowCardAction(upC));
                }
            });
        }
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}