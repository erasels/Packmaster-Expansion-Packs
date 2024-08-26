package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BlueTearstone extends AbstractDarkSoulsCard {
    public final static String ID = makeID("BlueTearstone");
    // intellij stuff skill, self, uncommon, , , 8, 3, , 

    public BlueTearstone() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < Wiz.countDebuffs(p) + 1; i++)
            blck();
    }

    public void upp() {
        upgradeBlock(3);
    }
}