package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.util.Wiz;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FullMoonHalo extends AbstractWitchStrikeCard {
    public final static String ID = makeID("FullMoonHalo");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FullMoonHalo() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
        magicNumber = baseMagicNumber = 4;
        cardsToPreview = new Bullet();
    }
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int orbs = 0;
        for (AbstractOrb o : Wiz.p().orbs) {
            if (!(o instanceof EmptyOrbSlot)) {
                orbs++;
            }
        }
        if (orbs >= 2){
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        int orbs = 0;
        for (AbstractOrb o : p.orbs) {
            if (!(o instanceof EmptyOrbSlot)) {
                orbs++;
            }
        }
        if (orbs >= 2){
            Wiz.atb(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy()));
        }
    }

    public void upp() {
        upgradeBlock(4);
    }
    @Override
    public String cardArtCopy() {
        return VoidCard.ID;
    }
}
