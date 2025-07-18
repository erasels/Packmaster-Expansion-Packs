package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.turmoilpack.AbandonAction;
import thePackmaster.powers.shamanpack.IgnitePower;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BurntOffering extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("BurntOffering");
    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 2;
    private static final int IGNITE = 4;
    private static final int UPGRADE_IGNITE = 1;

    public BurntOffering() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = IGNITE;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
        this.upgradeMagicNumber(UPGRADE_IGNITE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbandonAction(c -> c.color == CardColor.COLORLESS || c.color == CardColor.CURSE || c.type == CardType.CURSE, l -> {
            this.addToTop(new ApplyPowerAction(m, p, new IgnitePower(m, this.magicNumber * l.size())));
            this.addToTop(new DamageAction(m, new DamageInfo(p, this.damage * l.size(), this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
            Collections.reverse(l);
            for (AbstractCard c : l) {
                List<CardGroup> piles = Arrays.asList(p.discardPile, p.drawPile, p.hand);
                piles = piles.stream().filter(pile -> pile.contains(c)).collect(Collectors.toList());
                if (!piles.isEmpty()) {
                    this.addToTop(new ExhaustSpecificCardAction(c, piles.get(0)));
                }
            }
        }));
    }
}
