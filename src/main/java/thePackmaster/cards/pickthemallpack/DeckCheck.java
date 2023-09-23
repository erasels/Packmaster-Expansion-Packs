package thePackmaster.cards.pickthemallpack;

import basemod.abstracts.CustomSavable;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.gemspack.AbstractGemsCard;
import thePackmaster.packs.GemsPack;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DeckCheck extends AbstractPickThemAllCard implements OnObtainCard, SpawnModificationCard, ResetDescriptionForCombat, CustomSavable<String> {
    public static final String ID = SpireAnniversary5Mod.makeID("DeckCheck");
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int DAMAGE = 0;

    private AbstractGemsCard gem;
    private ArrayList<AbstractCard> rewardCards;

    public DeckCheck() {
        this(!CardCrawlGame.isInARun() || AbstractDungeon.miscRng == null ? null : getRandomGem());
    }

    public DeckCheck (AbstractGemsCard gem) {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.gem = gem;
        this.baseDamage = DAMAGE;
        this.updateDescription();
        this.cardsToPreview = this.gem;
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.resetDescriptionForCombat();
    }

    private void updateDescription() {
        String extraDescription = this.gem == null
                ? cardStrings.EXTENDED_DESCRIPTION[0]
                : cardStrings.EXTENDED_DESCRIPTION[1].replace("{0}", "*" + this.gem.name.replace(" ", "\u00a0"));
        this.rawDescription = cardStrings.DESCRIPTION + extraDescription;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.countPiles();
        super.calculateCardDamage(m);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
        this.cardsToPreview = null;
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.countPiles();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
        this.cardsToPreview = null;
    }

    private int countPiles() {
        return Math.max(AbstractDungeon.player.drawPile.size(), AbstractDungeon.player.discardPile.size());
    }

    public void resetDescriptionForCombat() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
        this.cardsToPreview = null;
    }

    @Override
    public void onObtainCard() {
        if (this.rewardCards != null) {
            for (int i = 0; i < this.rewardCards.size(); i++) {
                AbstractCard c = this.rewardCards.get(i);
                CardModifierManager.addModifier(c, this.gem.myMod());
                float spacingMultiplier = (i + 1) / (float)(this.rewardCards.size() + 1);
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(c, Settings.WIDTH * spacingMultiplier, Settings.HEIGHT / 2.0f));
            }
        }
    }

    @Override
    public void onRewardListCreated(ArrayList<AbstractCard> rewardCards) {
        this.rewardCards = rewardCards.stream().filter(c -> c != this).collect(Collectors.toCollection(ArrayList::new));
    }

    public static AbstractGemsCard getRandomGem() {
        ArrayList<AbstractGemsCard> validGems = SpireAnniversary5Mod.packsByID.get(GemsPack.ID).cards.stream().filter(c -> c.rarity == CardRarity.COMMON || c.rarity == CardRarity.UNCOMMON).map(c -> (AbstractGemsCard)c).collect(Collectors.toCollection(ArrayList::new));
        return validGems.get(AbstractDungeon.miscRng.random(validGems.size() - 1));
    }

    @Override
    public String onSave() {
        return this.gem.cardID;
    }

    @Override
    public void onLoad(String s) {
        this.gem = (AbstractGemsCard)CardLibrary.getCard(s);
        this.updateDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return this.gem == null ? new DeckCheck() : new DeckCheck((AbstractGemsCard)this.gem.makeCopy());
    }
}
