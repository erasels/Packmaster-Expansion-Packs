package thePackmaster.cards.siegepack;

import basemod.cardmods.ExhaustMod;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

//The single-target damage is stored in SecondDamage because "DmgAllEnemies" methods hardcodedly use Damage.
public class BallisticStrike extends AbstractSiegeCard {
    public final static String ID = makeID("BallisticStrike");
    private static final int COST = 3;
    private static final int TARGETED_DAMAGE = 11;
    private static final int UPGRADE_TARGETED_DAMAGE = 4;
    /*private static final int DAMAGE_OTHERS = 6;
    private static final int UPGRADE_DAMAGE_OTHERS = 5;*/
    private static final int TEMP_SHELLINGS = 1;
    //private static final int UPGRADE_TEMP_SHELLINGS = 1;

    public BallisticStrike() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        //baseDamage = DAMAGE_OTHERS;
        baseSecondDamage = TARGETED_DAMAGE;
        baseMagicNumber = magicNumber = TEMP_SHELLINGS;
        //isMultiDamage = true;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Deal damage to target, double value if it's attacking. Then damage all Other enemies.
        this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.35F));

        if (isRetaliatory(m)) {
            //Wiz.doDmg(m, secondDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            altDmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        } else {
            altDmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }

        //Add temp Shellings
        AbstractCard tempShelling = new Shelling();
        CardModifierManager.addModifier(tempShelling, new ExhaustMod());
        CardModifierManager.addModifier(tempShelling, new RetainMod());
        tempShelling.cost = 0;  //For some reason this waits until end of turn.
        tempShelling.costForTurn = 0;
        //Fine-tune this and amount of Shellings for balance.
        if (upgraded) {
            tempShelling.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(tempShelling, magicNumber));

        //Auto-play the Shellings whenever possible. Not sure how the below works.
        //AbstractDungeon.actionManager.addCardQueueItem(Shelling);

        //Does nothing.
        /*for (AbstractMonster mo : getEnemies()) {
            if (mo != m) {
                calculateTrueDamage(mo, false);
                dmg(m, AbstractGameAction.AttackEffect.FIRE);   //"isFast" ?.
            }
        }*/

        //REF: Striking Strike (creativitypack)
        /*addToBot(new FlexibleDiscoveryAction(JediUtil.createCardsForDiscovery(cards), selectedCard -> {
            CardModifierManager.addModifier(selectedCard, new ExhaustMod());
            selectedCard.cost = 0;
            },
                true));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Cannonball(), this.magicNumber, true));*/
    }

    //calculateCardDamage is Auto-called before every card is played.
    @Override
    public void calculateCardDamage(AbstractMonster mo){
        calculateTrueDamage(mo, true);
    }
    private void calculateTrueDamage(AbstractMonster mo, boolean isTarget) {
        if (mo == null) {return;}

        super.calculateCardDamage(mo);
        if (isTarget && isRetaliatory(mo)) {
            secondDamage *= 2;
            this.isSecondDamageModified = true;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public boolean isRetaliatory(AbstractMonster m) {return m != null && m.getIntentBaseDmg() >= 0;}

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void upp() {
        //upgradeDamage(UPGRADE_DAMAGE_OTHERS);
        upgradeSecondDamage(UPGRADE_TARGETED_DAMAGE);
    }
}
/*
REFS: Into The Breach's Smoldering Shell and much Discord channel code.

public void use(AbstractPlayer p, AbstractMonster m) {
    this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
    dmg(m, AbstractGameAction.AttackEffect.NONE);
    applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    for (AbstractMonster mo : getEnemies())
        if (mo != m)
            applyToEnemy(mo, new WeakPower(mo, secondMagic, false));
}

//What ENBEON would do:
@Override
public void calculateCardDamage(AbstractMonster mo) {
    calculateTrueDamage(mo, true);
}

public void calculateTrueDamage(AbstractMonster mo, boolean isTarget) {
    super.calculateCardDamage(mo);

    if (isTarget && isRetaliatory) {
        // double secondDamage
    }
}

@Override
public void use(AbstractPlayer p, AbstractMonster m) {
    altDmg(AttackEffect.WHATEVER);
    for (AbstractMonster mo : getEnemies()) {
        calculateTrueDamage(mo, false);
        dmg(mo, AttackEffect.WHATEVER);
    }
}
*/


