package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.red.SeverSoul;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import thePackmaster.actions.witchesstrikepack.MoonlightBarrageAction;
import thePackmaster.cardmodifiers.InscribedMod;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.orbs.spherespack.Blaze;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoonlightBarrage extends AbstractWitchStrikeCard {
    public final static String ID = makeID("MoonlightBarrage");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MoonlightBarrage() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MoonlightBarrageAction(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        this.addToBot(new ChannelAction(new Dark()));
        if (upgraded){
            addToBot(new ChannelAction(new Dark()));
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return SeverSoul.ID;
    }
}
