package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import thePackmaster.packs.RunicPack;
import thePackmaster.stances.downfallpack.AncientStance;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AncientTexts extends AbstractRunicCard {

    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    public final static String ID = makeID("AncientTexts");
    boolean freeFromOrb = false;

    public AncientTexts() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new DrawCardAction(magicNumber));
        if (!abstractPlayer.stance.ID.equals("Neutral")){
            Wiz.atb(new DrawCardAction(magicNumber));
        }
        Wiz.atb(new ChangeStanceAction(new AncientStance()));
    }

    @Override
    public void upp() {
        this.exhaust = false;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
