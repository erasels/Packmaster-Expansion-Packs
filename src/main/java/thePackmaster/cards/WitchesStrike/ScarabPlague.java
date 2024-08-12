package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.green.Bane;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.cardmodifiers.InfestModifier;
import thePackmaster.cards.OnInfestCard;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ScarabPlague extends AbstractWitchStrikeCard {
    public final static String ID = makeID("ScarabPlague");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ScarabPlague() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseBlock = 9;
        baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToEnemy(m,new WeakPower(m,magicNumber,false));
        Wiz.applyToEnemy(m,new LockOnPower(m,magicNumber));
        Wiz.atb(new ChannelAction(new CrescentMoon()));
    }
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return Bane.ID;
    }
}