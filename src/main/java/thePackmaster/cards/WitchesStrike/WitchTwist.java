package thePackmaster.cards.WitchesStrike;

import basemod.devcommands.draw.Draw;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Leap;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.cardmodifiers.InscribedMod;
import thePackmaster.cardmodifiers.witchesstrike.WickedModifier;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.powers.witchesstrikepack.WitchTwistPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.ISCARDMODIFIED;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WitchTwist extends AbstractWitchStrikeCard {
    public final static String ID = makeID("WitchTwist");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public WitchTwist() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new WitchTwistPower(p,magicNumber));
    }
    public void upp() {
        upgradeMagicNumber(3);
    }
    @Override
    public String cardArtCopy() {
        return Leap.ID;
    }
}

