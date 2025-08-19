package thePackmaster.cards.runicpack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EndTurnAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.HoardPayoffAction;
import thePackmaster.orbs.runicpack.Glyph;
import thePackmaster.stances.runicpack.RunicStance;
import thePackmaster.util.Wiz;
import thePackmaster.util.cardvars.HoardField;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MeditationChamber extends AbstractRunicCard {

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;
    public final static String ID = makeID("MeditationChamber");


    public MeditationChamber() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        this.selfRetain = true;
        PersistFields.setBaseValue(this, 3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i<magicNumber; i++) {
            Wiz.atb(new ChannelAction(new Glyph()));
        }
        Wiz.atb(new DrawCardAction(1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
