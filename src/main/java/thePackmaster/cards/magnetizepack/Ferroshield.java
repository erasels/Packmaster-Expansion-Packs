package thePackmaster.cards.magnetizepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.magnetizepack.MagnetizeAction;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Ferroshield extends AbstractMagnetizeCard {
    public final static String ID = makeID(Ferroshield.class.getSimpleName());

    public Ferroshield() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        MagnetizedModifier mod = new MagnetizedModifier(false);
        List<AbstractCard> eligibleCards = p.drawPile.group.stream().filter(mod::shouldApply).collect(Collectors.toList());
        Collections.shuffle(eligibleCards, AbstractDungeon.cardRandomRng.random);
        AbstractDungeon.cardRandomRng.counter++;
        for (int i=0; i < magicNumber && i < eligibleCards.size(); i++)
            addToBot(new MagnetizeAction(eligibleCards.get(i)));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}