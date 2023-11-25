package thePackmaster.cards.intriguepack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import thePackmaster.powers.intriguepack.ApexPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Apex extends AbstractIntrigueCard {
    public final static String ID = makeID("Apex");

    public Apex() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        // PARADOX PREVENTION.
        if (isMundane(this)) {
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }

        // Unplayable if you detect any filthy, pedestrian, plebeian, run of the mill, mundane cards in the turn card list.
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(q -> q.rarity != CardRarity.RARE && q.rarity != CardRarity.UNCOMMON)) {
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }

        return super.canUse(p, m);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                // Ascend.
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        CardCrawlGame.sound.playA("ATTACK_FLAME_BARRIER", 0.25F);
                        CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
                        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.YELLOW, true));
                        isDone = true;
                    }
                });
                this.addToBot(new VFXAction(p, new InflameEffect(p), 0.1F));

                this.addToBot(new GainEnergyAction(2));
                this.addToBot(new DrawCardAction(2));
                this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 10), 10));
                this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 10), 10));
                this.addToBot(new ApplyPowerAction(p, p, new ApexPower(p),-1));

                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}