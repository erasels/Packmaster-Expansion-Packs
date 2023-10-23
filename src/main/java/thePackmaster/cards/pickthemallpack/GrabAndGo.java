package thePackmaster.cards.pickthemallpack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.QuestionCard;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;

public class GrabAndGo extends AbstractPickThemAllCard implements OnObtainCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GrabAndGo");
    private static final int COST = -2;

    public GrabAndGo() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.isInnate = true;
    }

    @Override
    public void upp() {
        this.isInnate = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public String getDescriptionForCombat() {
        return cardStrings.EXTENDED_DESCRIPTION[1];
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, new QuestionCard());
    }

    @Override
    public void onRemoveFromMasterDeck() {
        // We wrap this in an effect in order to avoid concurrent modification exceptions in scenarios where this is
        // removed as part of a relic's on equip effect (e.g. Astrolabe, Empty Cage).
        AbstractDungeon.topLevelEffectsQueue.add(new AbstractGameEffect() {
            @Override
            public void update() {
                AbstractDungeon.player.loseRelic(QuestionCard.ID);
                this.isDone = true;
            }
            @Override
            public void render(SpriteBatch spriteBatch) {}
            @Override
            public void dispose() {}
        });
    }
}
