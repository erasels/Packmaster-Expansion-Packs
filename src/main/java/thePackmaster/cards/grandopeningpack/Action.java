package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.unique.CalculatedGambleAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardColor.COLORLESS;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.evenoddpack.AbstractEvenOddCard.makeCardTextGray;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class Action extends AbstractPackmasterCard {
    public final static String ID = makeID("Action");

    public Action() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, COLORLESS);
        baseDamage = damage = 5;
        baseMagicNumber = magicNumber = 2;
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        initializeDescription();
        exhaust = true;
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + cardStrings.EXTENDED_DESCRIPTION[2] + cardStrings.EXTENDED_DESCRIPTION[3];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[2]) + cardStrings.EXTENDED_DESCRIPTION[3];
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardAction(p, p, p.hand.size(), true));
        att(new AbstractGameAction() {
            @Override
            public void update() {
                int count = AbstractDungeon.player.hand.size();
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
                    for(int i = 0; i<count; i++) {
                        atb(new DamageAction(m, new DamageInfo(p, damage * magicNumber), AttackEffect.SLASH_DIAGONAL));
                    }
                } else {
                    for(int i = 0; i<count; i++) {
                        dmg(m, AttackEffect.SLASH_DIAGONAL);
                    }
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}