package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.LegSweep;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CrescentSweep extends AbstractWitchStrikeCard {
    public final static String ID = makeID("CrescentSweep");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CrescentSweep() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Bullet();
    }
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int skillcount = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if (c.type == CardType.SKILL && !(c == AbstractDungeon.actionManager.cardsPlayedThisTurn.get(0))){
                skillcount++;
            }
        }
        if (skillcount >= 2) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy(),magicNumber));
        int skillcount = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if (c.type == CardType.SKILL && !(c == AbstractDungeon.actionManager.cardsPlayedThisTurn.get(0))){
                skillcount++;
            }
        }
        if (skillcount >= 2) {
            Wiz.atb(new ChannelAction(new Arcane()));
        }
    }
    public void upp() {
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return LegSweep.ID;
    }
}
