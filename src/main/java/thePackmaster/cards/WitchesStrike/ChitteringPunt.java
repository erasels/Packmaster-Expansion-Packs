package thePackmaster.cards.WitchesStrike;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.green.SuckerPunch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ChitteringPunt extends AbstractWitchStrikeCard {
    public final static String ID = makeID("ChitteringPunt");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ChitteringPunt() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Bullet();
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(wickedString.TEXT[0],wickedString.TEXT[1]));
        return retVal;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        Wiz.atb(new MakeTempCardInHandAction(new Bullet(),magicNumber));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return SuckerPunch.ID;
    }
}