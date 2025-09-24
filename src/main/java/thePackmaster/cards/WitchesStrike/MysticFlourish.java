package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.CutThroughFate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.orbs.summonspack.FireSpirit;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MysticFlourish extends AbstractWitchStrikeCard {
    public final static String ID = makeID("MysticFlourish");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MysticFlourish() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 4;
        magicNumber = baseMagicNumber = 2;
    }

    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int orbs = 0;
        for (AbstractOrb o : Wiz.p().orbs) {
            if (!(o instanceof EmptyOrbSlot)) {
                orbs++;
            }
        }
        if (orbs >= 3) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        Wiz.atb(new ChannelAction(new FireSpirit()));
        int orbs = 0;
        for (AbstractOrb o : p.orbs) {
            if (!(o instanceof EmptyOrbSlot)) {
                orbs++;
            }
        }
        if (orbs >= 3) {
            Wiz.atb(new ApplyPowerAction(m, p, new IgnitePower(m, magicNumber)));
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return CutThroughFate.ID;
    }
}
