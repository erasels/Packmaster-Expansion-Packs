package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.insectglaivepack.derivative.CardForSectionOfIG;

import java.util.ArrayList;

public class KinsectHarvestExtract extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(KinsectHarvestExtract.class.getSimpleName());

    public KinsectHarvestExtract() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new CardForSectionOfIG(0));
        stanceChoices.add(new CardForSectionOfIG(1));
        stanceChoices.add(new CardForSectionOfIG(2));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToBot(new ChooseOneAction(stanceChoices));
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}
