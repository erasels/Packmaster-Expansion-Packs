package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Patience extends AbstractSerpentineCard {

    private static final int COST = 1;
    public final static String ID = makeID("Patience");
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 1;

    public Patience() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = block = BLOCK;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, block));

        if (!abstractPlayer.stance.ID.equals(NeutralStance.STANCE_ID)){
            addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.1F)));
            addToBot(new ChangeStanceAction(new NeutralStance()));
            addToBot(new GainBlockAction(abstractPlayer, block));
        }
    }

    @Override
    public void triggerOnGlowCheck(){
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (!AbstractDungeon.player.stance.ID.equals(NeutralStance.STANCE_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}
