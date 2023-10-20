package thePackmaster.powers.dancepack;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BreakdancePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(BreakdancePower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private AbstractCard.CardType lastCardsPlayed[] = {null, null};
    private boolean oncePerTurn = true;

    public BreakdancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        loadRegion("echo");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[amount == 1 ? 0 : 1], amount);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (oncePerTurn && (lastCardsPlayed[0] != lastCardsPlayed[1]) && (lastCardsPlayed[1] == card.type))
        {
            flash();
            GameActionManager.queueExtraCard(card, m);
            oncePerTurn = false;
        }

        lastCardsPlayed[0] = lastCardsPlayed[1];
        lastCardsPlayed[1] = card.type;
    }

    @Override
    public void atStartOfTurn() {
        oncePerTurn = true;
    }
}
