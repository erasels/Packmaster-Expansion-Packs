package thePackmaster.cards.pickthemallpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DarkBargain extends AbstractPickThemAllCard implements OnObtainCard {
    public static final String ID = SpireAnniversary5Mod.makeID("DarkBargain");
    private static final int COST = 0;
    private static final int DRAW = 2;
    private static final int UPGRADE_DRAW = 1;

    public DarkBargain() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.exhaust = true;
        this.cardsToPreview = new Injury();
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));
    }

    @Override
    public String getDescriptionForCombat() {
        return cardStrings.EXTENDED_DESCRIPTION[1];
    }

    @Override
    public void onObtainCard() {
        AbstractRelic sozu = AbstractDungeon.player.getRelic(Sozu.ID);
        if (sozu != null) {
            sozu.flash();
        } else if (Wiz.isInCombat()) {
            this.addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
        } else {
            AbstractDungeon.topLevelEffectsQueue.add(new ObtainPotionEffect(AbstractDungeon.returnRandomPotion()));
        }

        ArrayList<AbstractCard> upgradeableCards = AbstractDungeon.player.masterDeck.group.stream().filter(AbstractCard::canUpgrade).collect(Collectors.toCollection(ArrayList::new));
        if (!upgradeableCards.isEmpty()) {
            AbstractCard cardToUpgrade = upgradeableCards.get(AbstractDungeon.miscRng.random(upgradeableCards.size() - 1));
            AbstractDungeon.player.bottledCardUpgradeCheck(cardToUpgrade);
            cardToUpgrade.upgrade();
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(cardToUpgrade.makeStatEquivalentCopy()));
            AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
        }

        ArrayList<AbstractCard> gainableCards = new ArrayList<>();
        gainableCards.addAll(AbstractDungeon.srcCommonCardPool.group);
        gainableCards.addAll(AbstractDungeon.srcUncommonCardPool.group);
        gainableCards.addAll(AbstractDungeon.srcRareCardPool.group);
        if (!gainableCards.isEmpty()) {
            AbstractCard cardToGain = gainableCards.get(AbstractDungeon.miscRng.random(gainableCards.size() - 1));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(cardToGain, Settings.WIDTH * 1.0f / 3.0f, Settings.HEIGHT / 2.0f));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(new Injury(), Settings.WIDTH * 2.0f / 3.0f, Settings.HEIGHT / 2.0f));
        }
    }
}
