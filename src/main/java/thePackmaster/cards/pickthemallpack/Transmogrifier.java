package thePackmaster.cards.pickthemallpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.Strike;

import java.util.ArrayList;

public class Transmogrifier extends AbstractPickThemAllCard implements OnObtainCard, StartupCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Transmogrifier");
    private static final int COST = -2;

    public Transmogrifier() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.cardsToPreview = new Strike();
    }

    @Override
    public void upp() {
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(new Strike(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
    }

    @Override
    public boolean atBattleStartPreDraw() {
        ArrayList<AbstractCard> commons = new ArrayList<>();
        ArrayList<AbstractCard> uncommons = new ArrayList<>();
        ArrayList<AbstractCard> rares = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.rarity == CardRarity.BASIC || c.rarity == CardRarity.COMMON) {
                commons.add(c);
            }
            else if (c.rarity == CardRarity.UNCOMMON) {
                uncommons.add(c);
            }
            else if (c.rarity == CardRarity.RARE || c.rarity == CardRarity.SPECIAL) {
                rares.add(c);
            }
        }

        ArrayList<AbstractCard> allCards = new ArrayList<>();
        allCards.addAll(commons);
        allCards.addAll(uncommons);
        allCards.addAll(rares);

        if (!allCards.isEmpty()) {
            AbstractCard oldCard = allCards.get(0);
            AbstractDungeon.player.masterDeck.removeCard(oldCard);
            AbstractDungeon.effectsQueue.add(new PurgeCardEffect(oldCard, Settings.WIDTH / 3.0f, Settings.HEIGHT / 2.0f));
            AbstractDungeon.transformCard(oldCard, false, AbstractDungeon.miscRng);
            AbstractCard newCard = AbstractDungeon.getTransformedCard();
            AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(newCard, Settings.WIDTH * 2.0f / 3.0f, Settings.HEIGHT / 2.0f));

            replaceCard(oldCard, newCard, AbstractDungeon.player.drawPile);
            replaceCard(oldCard, newCard, AbstractDungeon.player.discardPile);
            replaceCard(oldCard, newCard, AbstractDungeon.player.exhaustPile);
            replaceCard(oldCard, newCard, AbstractDungeon.player.hand);
            replaceCard(oldCard, newCard, AbstractDungeon.player.limbo);
        }

        return false;
    }

    public void replaceCard(AbstractCard oldCard, AbstractCard newCard, CardGroup group) {
        for (int i = 0; i < group.group.size(); i++) {
            AbstractCard c = group.group.get(i);
            if (c.uuid.equals(oldCard.uuid)) {
                group.group.set(i, newCard.makeStatEquivalentCopy());
            }
        }
    }
}
