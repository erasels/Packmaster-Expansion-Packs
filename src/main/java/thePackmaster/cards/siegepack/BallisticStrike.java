package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.actions.siegepack.BallisticStrikeAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

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
        baseDamage = damage = TARGETED_DAMAGE;
        //baseSecondDamage = TARGETED_DAMAGE;
        baseMagicNumber = magicNumber = HITS;

        isMultiDamage = true;
        tags.add(CardTags.STRIKE);
        //cardsToPreview = new Shelling();

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    // Anything below will happen BEFORE anything at all in any action happens, Waits change nothing.
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i) {
            addToBot(new BallisticStrikeAction(this));
        }
    }

    //calculateCardDamage is auto-called before every card is played.
    @Override
    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        if (isRetaliatory(m)) {
            damage *= 2;
            isDamageModified = damage != baseDamage;
        }
//        this.rawDescription = cardStrings.DESCRIPTION;
//        this.initializeDescription();
    }

    public boolean isRetaliatory(AbstractMonster m)
    {return m != null && !m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0;}

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        // Light up in a stronger color if ALL enemies are attacking. Modargo said no.
        // A custom Color would have to be created if not GREEN_ or YELLOW_ .
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

//#########################################################
//================ MAKE AND AUTOPLAY CARDS ================
//#########################################################

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

//Alchyr's proposal. WORKS PERFECTLY :
/*
public class BallisticStrike extends AbstractSiegeCard {
    public final static String ID = makeID("BallisticStrike");
    private static final int COST = 3;
    private static final int TARGETED_DAMAGE = 10;
    //private static final int UPGRADE_TARGETED_DAMAGE = 4;
    private static final int HITS = 2;
    private static final int UPGRADE_HITS = 1;

    //constructor goes here

    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        if (isRetaliatory(m)) {
            damage *= 2;
            isDamageModified = damage != baseDamage;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i) {
            //assuming magicNumber is set to number of hits
            addToBot(new BallisticStrikeAction(this));
        }
    }

    ETC...
*/