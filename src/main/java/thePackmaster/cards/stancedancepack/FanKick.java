package thePackmaster.cards.stancedancepack;


import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.DivinityStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.stances.WrathStance;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.stancedancepack.GainEnergyOnStanceEnter;
import thePackmaster.powers.stancedancepack.NextWovenCheaper;
import thePackmaster.stances.aggressionpack.AggressionStance;
import thePackmaster.stances.cthulhupack.NightmareStance;
import thePackmaster.stances.downfallpack.AncientStance;
import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.stances.sentinelpack.Serene;
import thePackmaster.stances.serpentinepack.CunningStance;
import thePackmaster.stances.serpentinepack.VenemousStance;
import thePackmaster.stances.stancedancepack.Weaver;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.cthulhupack.AbstractCthulhuCard.loseSanity;


public class FanKick extends AbstractStanceDanceCard {
    public final static String ID = makeID("FanKick");

    public FanKick() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        //TODO: Stance switch effect madness writing to cardtext. EXTENDED DESCRIPTION has them all.


        SpireAnniversary5Mod.logger.info(p.stance.ID);

        switch (p.stance.ID) {
            case NeutralStance.STANCE_ID: {
                Wiz.applyToSelf(new GainEnergyOnStanceEnter(p, 1));
                break;
            }
            //Manual ID seems be necessary here? Weaver.Stance_ID errors out, constant expression required
            case "anniv5:Weaver": {
                Wiz.applyToSelf(new NextWovenCheaper(p, 1));
                break;
            }
            case "anniv5:Venemous": {
                Wiz.applyToEnemy(m, new PoisonPower(p, m, 5));
                break;
            }
            case "anniv5:Cunning": {
                Wiz.applyToEnemy(m, new WeakPower(m, 1, false));
                Wiz.applyToEnemy(m, new VulnerablePower(m, 1, false));
                break;
            }
            case CalmStance.STANCE_ID: {
                Wiz.atb(new DrawCardAction(2));
                break;
            }
            case WrathStance.STANCE_ID: {
                Wiz.applyToSelf(new VigorPower(p, 4));
                break;
            }
            case "anniv5:Angry": {
                Wiz.applyToSelf(new VigorPower(p, 4));
                break;
            }
            case "anniv5:Aggression": {
                dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
                break;
            }
            case "anniv5:Serene": {
                blck();
                break;
            }
            case "anniv5:Ancient": {
                Wiz.applyToSelf(new ArtifactPower(p, 1));
                break;
            }
            case "anniv5:Nightmare": {
                loseSanity(5);
                break;
            }
            case DivinityStance.STANCE_ID: {
                Wiz.applyToSelf(new MantraPower(p, 5));
                break;
            }
            default: {
                break;
            }
        }


    }


    @Override
    public void upp() {
        upgradeDamage(4);
    }
}




