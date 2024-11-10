package thePackmaster.cards.tf2pack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.tf2pack.PersistMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Ammo extends AbstractTF2Card {
    public final static String ID = makeID("Ammo");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 0;

    public Ammo() {
        super(ID, COST, TYPE, RARITY, TARGET);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Wiz.hand().getAttacks().isEmpty())
            return;
        // target an attack card in hand to gain +1 persist this combat
        addToBot(new SelectCardsAction(p.hand.group, cardStrings.EXTENDED_DESCRIPTION[0],
                card -> card.type == CardType.ATTACK,
                (cards) -> {
                    for (AbstractCard c2 : cards) {
                        CardModifierManager.addModifier(c2, new PersistMod());
                    }
                }
        ));
    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }
}
