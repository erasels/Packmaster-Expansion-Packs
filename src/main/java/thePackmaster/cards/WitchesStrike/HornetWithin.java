package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.unique.FlechetteAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.Bane;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.util.Wiz;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HornetWithin extends AbstractWitchStrikeCard {
    public final static String ID = makeID("HornetWithin");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public HornetWithin() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 3;
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new FlechetteAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        Wiz.atb(new ChannelAction(new Arcane()));
        this.rawDescription = cardStrings.DESCRIPTION;// 34
        this.initializeDescription();// 35
    }// 36

    public void applyPowers() {
        super.applyPowers();// 40
        int count = 0;// 42
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();// 43

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.type == CardType.SKILL) {// 44
                ++count;// 45
            }
        }

        this.rawDescription = cardStrings.DESCRIPTION;// 49
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;// 50
        if (count == 1) {// 51
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];// 52
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];// 54
        }

        this.initializeDescription();// 56
    }// 57

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;// 61
        this.initializeDescription();// 62
    }
    public void upp() {
        upgradeDamage(2);
    }
    @Override
    public String cardArtCopy() {
        return Bane.ID;
    }
}
