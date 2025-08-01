package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import thePackmaster.orbs.runicpack.Glyph;
import thePackmaster.stances.runicpack.RunicStance;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DaringEscape extends AbstractRunicCard {

    private static final int COST = 2;
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 3;
    public final static String ID = makeID("DaringEscape");


    public DaringEscape() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractPlayer p = abstractPlayer;
        blck();
        for (int i = 0; i<2; i++) {
            Wiz.atb(new ChannelAction(new Glyph()));
        }
        this.addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
        this.addToBot(new ChangeStanceAction("Neutral"));
    }

    @Override
    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}
