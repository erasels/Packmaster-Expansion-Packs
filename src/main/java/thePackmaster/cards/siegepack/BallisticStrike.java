package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.actions.siegepack.BallisticStrikeAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

public class BallisticStrike extends AbstractSiegeCard {
    public final static String ID = makeID("BallisticStrike");
    private static final int COST = 3;
    private static final int TARGETED_DAMAGE = 10;
    private static final int HITS = 2;
    private static final int UPGRADE_HITS = 1;

    public BallisticStrike() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = damage = TARGETED_DAMAGE;
        baseMagicNumber = magicNumber = HITS;

        tags.add(CardTags.STRIKE);

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    // Everything below will happen BEFORE anything in any action. Waits change nothing.
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i) {
            addToBot(new BallisticStrikeAction(this));
        }
    }

    // calculateCardDamage is auto-called before every card is played.
    @Override
    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        if (Wiz.isAttacking(m)) {
            damage *= 2;
            isDamageModified = damage != baseDamage;
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (Wiz.isAttacking(m)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_HITS);
    }
}