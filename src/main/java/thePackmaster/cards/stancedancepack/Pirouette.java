package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.actions.EasyXCostAction;

import thePackmaster.stances.downfallpack.AncientStance;
import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.stancedancepack.StanceDanceEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Pirouette extends AbstractStanceDanceCard {
    public final static String ID = makeID("Pirouette");

    public Pirouette() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.player.useJumpAnimation();
        atb(new VFXAction(new StanceDanceEffect(AbstractDungeon.player, false, true, true), 0.1F));

        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                dmgTop(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            }
            if (effect <= 0) {
                this.addToBot(new ChangeStanceAction(new AncientStance()));
            }
            return true;
        }));


    }


    @Override
    public void upp() {
        upgradeDamage(3);
    }

}


