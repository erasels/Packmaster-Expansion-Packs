package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Shakedown extends AbstractIntrigueCard {
    public final static String ID = makeID("Shakedown");

    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 3;

    public Shakedown() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        boolean holdingShiny = false;

        for (AbstractCard c : Wiz.p().hand.group) {
            if (c.rarity == CardRarity.RARE || c.rarity == CardRarity.UNCOMMON) {
                holdingShiny = true;
                break;
            }
        }

        if (holdingShiny) glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        int drawnum = 0;
        boolean unc_d = false;
        boolean rar_d = false;

        for (AbstractCard c : Wiz.p().hand.group) {
            if (c.rarity == CardRarity.UNCOMMON)
                unc_d = true;
            else if (c.rarity == CardRarity.RARE)
                rar_d = true;
        }

        if (unc_d)
            drawnum++;
        if (rar_d)
            drawnum++;

        if(drawnum > 0)
            Wiz.atb(new DrawCardAction(drawnum));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DMG);
    }
}