package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.green.LegSweep;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CrescentSweep extends AbstractWitchStrikeCard {
    public final static String ID = makeID("CrescentSweep");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CrescentSweep() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = 7;
        baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new ChannelAction(new CrescentMoon()));
        if (upgraded){
            addToBot(new ChannelAction(new CrescentMoon()));
        }
    }
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return LegSweep.ID;
    }
}
