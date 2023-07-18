package thePackmaster.cards.maridebuffpack;


import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.actions.maridebuffpack.GiveBlockAction;

import static com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting.SELF_OR_ENEMY;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class OldCostume extends AbstractMariDebuffCard {
    public final static String ID = makeID("OldCostume");

    public OldCostume() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, SELF_OR_ENEMY);
        this.baseBlock = this.block = 10;
        this.baseMagicNumber = this.magicNumber = 1;
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = SelfOrEnemyTargeting.getTarget(this); //thanks alch
        atb(new GainBlockAction(p, p, this.block));
        if (target == null){
            target = AbstractDungeon.player;
        }else if(target != AbstractDungeon.player){
            atb(new GiveBlockAction(p.currentBlock, this.block, target));
        }
        atb(new ApplyPowerAction(target, p, new VulnerablePower(target, this.magicNumber, false), this.magicNumber));
    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }


}


