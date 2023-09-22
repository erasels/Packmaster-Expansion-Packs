package thePackmaster.cards.pickthemallpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.blue.Aggregate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;

public class PackGear extends AbstractPickThemAllCard implements OnObtainCard {
    public static final String ID = SpireAnniversary5Mod.makeID("PackGear");
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int CARDS_PER_METALLICIZE = 10;

    public PackGear() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = CARDS_PER_METALLICIZE;
        this.cardsToPreview = new Aggregate();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.addToTop(new ApplyPowerAction(p, p, new MetallicizePower(p, getAmount())));
                this.isDone = true;
            }
        });
    }

    @Override
    public void applyPowers() {
        this.secondMagic = this.baseSecondMagic = this.getAmount();
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    private int getAmount() {
        return (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() + this.magicNumber - 1) / this.magicNumber;
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new Aggregate(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
    }
}
