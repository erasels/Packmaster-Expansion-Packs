package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class DefensePlanning extends AbstractSiegeCard {
    public final static String ID = makeID("DefensePlanning");
    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int UPGRADE_BLOCK = 3;

    public DefensePlanning() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();

        // Gain 1 Blur if none present.
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (!player.hasPower(BlurPower.POWER_ID)) {
                    Wiz.applyToSelfTop(new BlurPower(player, 1));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}