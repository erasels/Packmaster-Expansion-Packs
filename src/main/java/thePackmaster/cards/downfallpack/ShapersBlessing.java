package thePackmaster.cards.downfallpack;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import thePackmaster.stances.downfallpack.AncientStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ShapersBlessing extends AbstractDownfallCard {
    public final static String ID = makeID("ShapersBlessing");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    private static final int BLOCK = 7;

    public ShapersBlessing() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = 0;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (magicNumber > 0) applyToSelf(new PlatedArmorPower(p, magicNumber));
        addToBot(new ChangeStanceAction(new AncientStance()));

    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}