package thePackmaster.cards.needleworkpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.needlework.StitchAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Patchwork extends AbstractNeedleworkCard {
    public final static String ID = makeID("Patchwork");

    public Patchwork() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

        baseBlock = block = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();

        AbstractCard cpy = makeStatEquivalentCopy();

        cpy.drawScale = cpy.targetDrawScale = drawScale;
        cpy.current_x = cpy.target_x = current_x;
        cpy.current_y = cpy.target_y = current_y;

        addToBot(new StitchAction(cpy));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}
