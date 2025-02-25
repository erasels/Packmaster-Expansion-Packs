package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.unique.FlechetteAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.Bane;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.util.Wiz;

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
        super.applyPowers();
        int skills = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.SKILL) {
                ++skills;
            }
        }

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + skills;
        if (skills == 1) {// 51
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
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
