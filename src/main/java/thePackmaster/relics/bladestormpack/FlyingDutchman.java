package thePackmaster.relics.bladestormpack;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.BladeStormPack;
import thePackmaster.powers.bladestormpack.WindrushPower;
import thePackmaster.powers.instadeathpack.Precision;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

//REFS: Necronomicon & Shuriken & Centennial Puzzle (base game)
public class FlyingDutchman extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("FlyingDutchman");
    public static final int WINDRUSH = 2;
    public static final int PRECISION = 3;
    public static final int ATTACKS_FOR_TRIGGER = 1;

    private boolean activated = true;

    public FlyingDutchman() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, BladeStormPack.ID);
        this.counter = 0;
    }

    @Override
    public void onEquip() {
        CardCrawlGame.sound.play("NECRONOMICON");
        init();
    }

    @Override
    public void atTurnStart() {
        init();
    }

    public void init() {
        activated = false;
        counter = 0;
        pulse = true;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type != AbstractCard.CardType.ATTACK) { return; }
        counter++;

        if (activated) { return; }

        if (counter > ATTACKS_FOR_TRIGGER) {
            Wiz.applyToSelf(new WindrushPower(player, WINDRUSH));
            Wiz.applyToSelf(new Precision(player, PRECISION));

            pulse = false;
            addToTop(new RelicAboveCreatureAction( player, this));
            flash();

            activated = true;
        } else {
            if (counter == ATTACKS_FOR_TRIGGER) {
                beginPulse();
            }
        }
    }

    @Override
    public void onPlayerEndTurn () {
        pulse = false;
    }

    @Override
    public void onVictory () {
        pulse = false;
        activated = false;
        this.counter = -1;  //Shuriken hides counter between battles this way.
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + WINDRUSH + this.DESCRIPTIONS[1] + PRECISION + this.DESCRIPTIONS[2];
    }
}
