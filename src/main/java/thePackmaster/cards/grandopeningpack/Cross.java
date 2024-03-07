package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.grandopeningpack.CrossPower;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.evenoddpack.AbstractEvenOddCard.makeCardTextGray;
import static thePackmaster.util.Wiz.atb;

public class Cross extends AbstractGrandOpeningCard {
    public final static String ID = makeID("Cross");

    public Cross() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseDamage = damage = 4;
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[5];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[6];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[7];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[5];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[6];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[7];
        initializeDescription();
    }
    @Override
    public void applyPowers() {
        AbstractPower cross = AbstractDungeon.player.getPower(CrossPower.POWER_ID);
        int crossAmount = magicNumber;
        if(cross!=null)
            crossAmount += cross.amount;
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + crossAmount + cardStrings.EXTENDED_DESCRIPTION[crossAmount==1 ? 1 : 2];
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3] + cardStrings.EXTENDED_DESCRIPTION[4] + cardStrings.EXTENDED_DESCRIPTION[5] + cardStrings.EXTENDED_DESCRIPTION[6] + cardStrings.EXTENDED_DESCRIPTION[7];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3] + cardStrings.EXTENDED_DESCRIPTION[4] + makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[5] + cardStrings.EXTENDED_DESCRIPTION[7]);
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower cross = p.getPower(CrossPower.POWER_ID);
        int crossAmount = 0;
        if(cross!=null)
            crossAmount = cross.amount;
        for(int i = 0; i<magicNumber+crossAmount; i++){
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            if(i+1<magicNumber+crossAmount){
                dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
                i++;
            }
        }
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
            atb(new ApplyPowerAction(p, p, new CrossPower(p, 1)));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}
