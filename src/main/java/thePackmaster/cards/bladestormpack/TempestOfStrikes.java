package thePackmaster.cards.bladestormpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import thePackmaster.powers.needlework.BindPower;
import thePackmaster.powers.strikepack.StrikeDummyJrPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

/*REFS: StrikeDummyJr (strikepack), Bees (infestpack), ShrapnelAction (siegepack), Master's Strike (upgradespack),
Finisher (base game)*/
public class TempestOfStrikes extends AbstractBladeStormCard {
    public final static String ID = makeID("TempestOfStrikes");
    private static final int COST = 2;
    private static final int DAMAGE = 3;
    private static final int BIND = 1;
    private static final int STRIKES_BOOST = 0;
    private static final int UPG_STRIKES_BOOST = 2;

    public TempestOfStrikes() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseSecondMagic = secondMagic = BIND;

        baseMagicNumber = magicNumber = STRIKES_BOOST;
        tags.add(CardTags.STRIKE);
        exhaust = true;

        updateDescription();

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        updateDescription();
    }

    //New
    public void updateDescription () {
        int attacks = countAttacksInDeck();
        if (!upgraded) {
            rawDescription = cardStrings.DESCRIPTION
                            + attacks + cardStrings.EXTENDED_DESCRIPTION[0]
                            + cardStrings.EXTENDED_DESCRIPTION[5];
        } else {
            rawDescription = cardStrings.DESCRIPTION
                            + attacks + cardStrings.EXTENDED_DESCRIPTION[0]
                            + cardStrings.EXTENDED_DESCRIPTION[1]
                            + cardStrings.EXTENDED_DESCRIPTION[2]
                            + magicNumber
                            + cardStrings.EXTENDED_DESCRIPTION[3]
                            + cardStrings.EXTENDED_DESCRIPTION[4]
                            + cardStrings.EXTENDED_DESCRIPTION[5];
        }
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int attacksInDeck = countAttacksInDeck();
        if (attacksInDeck <= 0) { return; }

        AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.CYAN));

        for (int i = 0; i < attacksInDeck; i++) {
            dmg(m, getRandomAttackEffect());
        }
        //Separate loop to apply debuff(s), to avoid surprise damage changes from FieldResearch (intothebreachpack) and such.
        for (int i = 0; i < attacksInDeck; i++) {
            addToBot(new ApplyPowerAction(m, p, new BindPower(m, secondMagic), secondMagic, true));
        }

        if (magicNumber <= 0) {return;}
        Wiz.applyToSelf(new StrikeDummyJrPower(p, magicNumber));
    }

    private AbstractGameAction.AttackEffect getRandomAttackEffect() {
        int result = MathUtils.random(0, 6);
        switch (result) {
            case 0:
                return AbstractGameAction.AttackEffect.BLUNT_LIGHT;
            case 1:
                return AbstractGameAction.AttackEffect.BLUNT_HEAVY;
            case 2:
                return AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
            case 3:
                return AbstractGameAction.AttackEffect.SMASH;
            case 4:
                return AbstractGameAction.AttackEffect.SLASH_HEAVY;
            case 5:
                return AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
            case 6:
                return AbstractGameAction.AttackEffect.SLASH_VERTICAL;
            default:
                return AbstractGameAction.AttackEffect.NONE;
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_STRIKES_BOOST);
        updateDescription();
    }

    public static int countAttacksInDeck() {
        if (AbstractDungeon.player == null) { return 0; }

        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.type == CardType.ATTACK) {
                count += 1;
            }
        }
        return count;
    }
}
