package thePackmaster.cards.pickthemallpack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.gemspack.AbstractGemsCard;
import thePackmaster.cards.gemspack.DiceGem;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DeckCheck extends AbstractPickThemAllCard implements OnObtainCard, SpawnModificationCard, OnCreateCardInterface {
    public static final String ID = SpireAnniversary5Mod.makeID("DeckCheck");
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int DAMAGE = 0;

    private AbstractGemsCard gem;
    private ArrayList<AbstractCard> rewardCards;

    public DeckCheck() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.cardsToPreview = this.getGem();    }

    @Override
    public void upp() {
        this.upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.resetDescriptionForCombat();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.countPiles();
        super.calculateCardDamage(m);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.countPiles();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    private int countPiles() {
        return Math.max(AbstractDungeon.player.drawPile.size(), AbstractDungeon.player.discardPile.size());
    }

    @Override
    public void onCreateCard(AbstractCard c) {
        super.onCreateCard(c);
        if (c == this && AbstractDungeon.player.hand.contains(this)) {
            this.applyPowers();
        }
    }

    @Override
    public void onMoveToDiscard() {
        this.resetDescriptionForCombat();
    }

    @Override
    public void onObtainCard() {
        if (this.rewardCards != null) {
            for (int i = 0; i < this.rewardCards.size(); i++) {
                AbstractCard c = this.rewardCards.get(i);
                CardModifierManager.addModifier(c, this.getGem().myMod());
                float spacingMultiplier = (i + 1) / (float)(this.rewardCards.size() + 1);
                // You normally aren't able to get multiple copies of the same card in rewards, but it could be possible
                // with mods, so to avoid infinite loops we disable the pickup logic for any other copies of Deck Check
                if (c instanceof DeckCheck) {
                    ((DeckCheck)c).rewardCards = null;
                }
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(c, Settings.WIDTH * spacingMultiplier, Settings.HEIGHT / 2.0f));
            }
        }
    }

    @Override
    public void onRewardListCreated(ArrayList<AbstractCard> rewardCards) {
        this.rewardCards = rewardCards.stream().filter(c -> c != this).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String getPickupDescription() {
        return super.getPickupDescription().replace("{0}", "*" + this.getGem().name.replace(" ", "\u00a0"));
    }

    private AbstractGemsCard getGem() {
        if (this.gem == null) {
            this.gem = new DiceGem();
        }
        return this.gem;
    }
}
