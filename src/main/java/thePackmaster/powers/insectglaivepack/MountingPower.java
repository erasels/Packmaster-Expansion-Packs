package thePackmaster.powers.insectglaivepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.insectglaivepack.AbstractInsectGlaiveCard;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.powers.AbstractPackmasterPower;

public class MountingPower extends AbstractPackmasterPower {
    public static String ID = SpireAnniversary5Mod.makeID(MountingPower.class.getSimpleName());
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;

    public MountingPower(AbstractCreature owner, int amount) {
        super(ID, STRINGS.NAME, PowerType.BUFF, false, owner, 0);
        loadRegion("flight");
        this.amount2 = amount;
        this.isTwoAmount = true;
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && InsectGlaivePack.isHover(2)) {
            this.amount++;
            this.flash();
            if (this.amount >= this.amount2) {
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    if (!m.isDeadOrEscaped()) {
                        addToBot(new LoseHPAction(m, this.owner, (int) (m.currentHealth * 0.5F)));
                        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
                    }
                }
            }
            updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount2 - this.amount) + DESCRIPTIONS[1];
    }
}
