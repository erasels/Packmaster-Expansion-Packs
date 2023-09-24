package thePackmaster.cards.pickthemallpack;

import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import thePackmaster.SpireAnniversary5Mod;

public class TreasureChest extends AbstractPickThemAllCard implements OnObtainCard, CustomSavable<Integer> {
    public static final String ID = SpireAnniversary5Mod.makeID("TreasureChest");
    private static final int COST = 2;
    private static final int PLAYS = 7;
    private static final int UPGRADE_PLAYS = -2;

    public TreasureChest() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = this.misc = PLAYS;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_PLAYS);
        if (this.magicNumber < 1) {
            this.magicNumber = this.baseMagicNumber = 1;
        }
        this.misc = this.magicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                decrementMisc();
                TreasureChest masterDeckCard = (TreasureChest)StSLib.getMasterDeckEquivalent(TreasureChest.this);
                if (masterDeckCard != null) {
                    masterDeckCard.decrementMisc();
                    if (masterDeckCard.misc <= 0) {
                        AbstractDungeon.player.masterDeck.removeCard(masterDeckCard);
                        AbstractDungeon.effectsQueue.add(new PurgeCardEffect(masterDeckCard, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                    }
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON));
    }

    @Override
    public Integer onSave() {
        return this.misc;
    }

    @Override
    public void onLoad(Integer misc) {
        this.magicNumber = this.baseMagicNumber = this.misc = misc;
    }

    private void decrementMisc() {
        this.magicNumber = this.baseMagicNumber = this.misc = this.misc - 1;
        this.initializeDescription();
    }
}
