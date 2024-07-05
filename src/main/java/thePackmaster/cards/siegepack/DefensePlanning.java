package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class DefensePlanning extends AbstractEasyCard {
    public final static String ID = makeID("DefensePlanning");
    // intellij stuff skill, self, basic, , ,  6, 3, , 

    public DefensePlanning() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 6;
        tags.add(CardTags.STARTER_DEFEND);
        //Add Gain 1 Blur
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}