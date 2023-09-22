package thePackmaster.cards.pickthemallpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.blue.Stack;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;

public class BigBoots extends AbstractPickThemAllCard implements OnObtainCard {
    public static final String ID = SpireAnniversary5Mod.makeID("BigBoots");
    private static final int COST = 1;
    private static final int DRAW = 2;
    private static final int UPGRADE_DRAW = 1;
    private static final int THRESHOLD = 15;

    public BigBoots() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.secondMagic = this.baseSecondMagic = THRESHOLD;
        this.cardsToPreview = new Stack();
    }

    @Override
    public void upp() {
        this.upgradeSecondMagic(UPGRADE_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.addToTop(new DrawCardAction(1));
                if (checkPileSizes()) {
                    this.addToTop(new DrawCardAction(magicNumber));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = this.checkPileSizes() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new Stack(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
    }

    private boolean checkPileSizes() {
        return Math.max(AbstractDungeon.player.drawPile.size(), AbstractDungeon.player.discardPile.size()) > this.secondMagic;
    }
}
