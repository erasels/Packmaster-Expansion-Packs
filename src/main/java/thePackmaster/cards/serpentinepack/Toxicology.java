package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import thePackmaster.stances.serpentinepack.VenemousStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Toxicology extends AbstractSerpentineCard {

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;
    public final static String ID = makeID("Toxicology");

    public Toxicology() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new DrawCardAction(2));

        if (abstractPlayer.stance.ID.equals(VenemousStance.STANCE_ID)){
            this.addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.1F)));
            this.addToBot(new ChangeStanceAction("Neutral"));
            this.addToBot(new GainEnergyAction(magicNumber));
        }
        else {
            addToBot(new ChangeStanceAction(new VenemousStance()));
        }
    }

    @Override
    public void triggerOnGlowCheck(){
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.stance.ID.equals(VenemousStance.STANCE_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}
