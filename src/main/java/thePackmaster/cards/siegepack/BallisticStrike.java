package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.DarkSmokePuffEffect;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.actions.siegepack.BallisticStrikeAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;

//The single-target damage is stored in SecondDamage because "DmgAllEnemies" methods hardcodedly use Damage.
public class BallisticStrike extends AbstractSiegeCard {
    public final static String ID = makeID("BallisticStrike");
    private static final int COST = 3;
    private static final int TARGETED_DAMAGE = 10;
    //private static final int UPGRADE_TARGETED_DAMAGE = 4;

    private static final int HITS = 2;
    private static final int UPGRADE_HITS = 1;

    /*private static final int DAMAGE_OTHERS = 6;
    private static final int UPGRADE_DAMAGE_OTHERS = 5;*/

    /*private static final int TEMP_SHELLINGS = 1;
    private static final int UPGRADE_TEMP_SHELLINGS = 1;*/

    public BallisticStrike() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = TARGETED_DAMAGE;
//        baseSecondDamage = TARGETED_DAMAGE;
        baseMagicNumber = magicNumber = HITS;
        isMultiDamage = true;
        tags.add(CardTags.STRIKE);
//        cardsToPreview = new Shelling();

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Queue actions to damage random enemy X times, double damage if attacking.
        for (int i = 0; i < this.magicNumber; ++i) {
            /*if (p.hasPower(ShellPower.POWER_ID)) {
                this.addToTop(new VFXAction(new ExplosionSmallEffect(p.hb.cX, p.hb.cY), 0.04F));
            }*/
            this.addToTop(new VFXAction(new ExplosionSmallEffect(p.hb.cX, p.hb.cY), 0.05F));
            this.addToTop(new VFXAction(new DarkSmokePuffEffect(p.hb.cX, p.hb.cY), 0.10F));
            atb(new BallisticStrikeAction(p, this));
        }

        /*if (isRetaliatory(m)) {
            //Wiz.doDmg(m, secondDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            altDmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        } else {
            altDmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }*/

        //Add temp Shellings
        /*AbstractCard tempShelling = new Shelling();
        CardModifierManager.addModifier(tempShelling, new ExhaustMod());
        CardModifierManager.addModifier(tempShelling, new RetainMod());
        tempShelling.cost = 0;  //For some reason this waits until end of turn.
        tempShelling.costForTurn = 0;*/

        //Fine-tune this and amount of Shellings for balance.
        /*if (upgraded) {
            tempShelling.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(tempShelling, magicNumber));*/

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
    /*@Override
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
    }*/

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        //Light up in a stronger color if ALL enemies are attacking. Modargo said no.
        /*ArrayList<AbstractMonster> enemies = AbstractDungeon.getCurrRoom().monsters.monsters;
        int attackers = 0;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
                attackers += 1;
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
        if (attackers == enemies.size()) {this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();}
        return;*/
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            //if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
            if (isRetaliatory(m)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void upp() {
        //upgradeDamage(UPGRADE_DAMAGE_OTHERS);
        //upgradeSecondDamage(UPGRADE_TARGETED_DAMAGE);
        upgradeMagicNumber(UPGRADE_HITS);
    }

    public boolean isRetaliatory(AbstractMonster m)
    {return m != null && !m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0;}
}
/*
(Recorded elsewhere : much Discord channel code.)

REF: Damage OTHER enemies. intothebreachpack's SmolderingShell.
(Did not work)

public void use(AbstractPlayer p, AbstractMonster m) {
    this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
    dmg(m, AbstractGameAction.AttackEffect.NONE);
    applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    for (AbstractMonster mo : getEnemies())
        if (mo != m)
            applyToEnemy(mo, new WeakPower(mo, secondMagic, false));
}

//What Enbeon would do:
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

//================ MAKE AND AUTOPLAY CARDS ================

PERFECT REF : Create a card and autoplay it immediately at a random enemy. Daggerstorm (dimensiongatepack2).

public void use(AbstractPlayer p, AbstractMonster m) {
    applyToSelf(new DaggerstormPower(p, 1));
}

In the power:

for (int i = 0; i < amount; i++) {
    Shiv shiv = new Shiv();
    shiv.purgeOnUse = true;
    Wiz.atb(new NewQueueCardAction(shiv, true));
}

REF: Makes and autoplays a specific card (though every turn). odditiespack's FinalForm.

public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if(!p.hand.isEmpty()) {
                        AbstractCard target = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                        applyToSelfTop(new FinalFormPower(target.makeStatEquivalentCopy()));
                        att(new ExhaustSpecificCardAction(target, AbstractDungeon.player.hand));
                    }
                }
            });
        } else {
            atb(new SelectCardsInHandAction(cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
                for (AbstractCard c : cards) {
                    applyToSelfTop(new FinalFormPower(c.makeStatEquivalentCopy()));
                    att(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }
            }));
        }
    }

REF: Auto Battler (odditiespack) uses a complex patch class, way out of scope.
*/


