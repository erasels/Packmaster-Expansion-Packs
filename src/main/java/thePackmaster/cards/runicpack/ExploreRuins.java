package thePackmaster.cards.runicpack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.runicpack.Glyph;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ExploreRuins extends AbstractRunicCard {

    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    public final static String ID = makeID("ExploreRuins");


    public ExploreRuins() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        PersistFields.setBaseValue(this, magicNumber);
        this.showEvokeOrbCount = 1;
        this.showEvokeValue = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new ChannelAction(new Glyph()));
        Wiz.atb(new DrawCardAction(1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        PersistFields.upgrade(this, UPG_MAGIC);
    }
}
