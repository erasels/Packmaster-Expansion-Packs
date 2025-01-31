package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.green.Blur;
import com.megacrit.cardcrawl.cards.green.SuckerPunch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.cardmodifiers.witchesstrike.WickedModifier;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Bullet extends AbstractWitchStrikeCard {
    public final static String ID = makeID("Bullet");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Bullet() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        block = baseBlock = 4;
        exhaust = true;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doBlk(block);
    }

    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public String cardArtCopy() {
        return Blur.ID;
    }
}
