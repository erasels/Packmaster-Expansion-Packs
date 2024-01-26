package thePackmaster.cards.dimensiongatepack2;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGordian;
import thePackmaster.util.Wiz;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WallOfFire extends AbstractDimensionalCardGordian {
    public final static String ID = makeID("WallOfFire");

    public WallOfFire() {
        super(ID, 5, CardRarity.RARE, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new FlamePillar();
        isMultiDamage = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; ++i) {
            Wiz.doAllDmg(this, AbstractGameAction.AttackEffect.FIRE, false);
        }

        for (int i = 0; i < BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size() + 1; ++i) {
            AbstractCard c = new FlamePillar();
            if (upgraded) c.upgrade();
            addToBot(new MakeTempCardInHandAction(c, 1));
        }
    }

    public void upp() {
        cardsToPreview.upgrade();
    }
}