package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import thePackmaster.cards.serpentinepack.AbstractSerpentineCard;
import thePackmaster.stances.runicpack.RunicStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ReadRunes extends AbstractRunicCard {

    private static final int COST = 1;
    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 3;
    public final static String ID = makeID("ReadRunes");


    public ReadRunes() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        blck();
        this.addToBot(new ChangeStanceAction(new RunicStance()));
    }

    @Override
    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}
