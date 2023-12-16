package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class ThrowSoulstonesAusterity extends AbstractRoyaltyCard {

    public final static String ID = makeID("ThrowSoulstonesAusterity");
    public final static int DAMAGE = 5;

    public ThrowSoulstonesAusterity(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionAusterity.png");
        baseDamage = damage = DAMAGE;
    }

    public ThrowSoulstonesAusterity(int damageToDo){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionAusterity.png");
        baseDamage = damage = damageToDo;
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption(){
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new BlizzardEffect(5, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            this.addToBot(new VFXAction(new BlizzardEffect(5, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.5F));
        }
        int[] damageMatrix = DamageInfo.createDamageMatrix(this.baseDamage, true);
        DamageAllEnemiesAction dmgAll = new DamageAllEnemiesAction(AbstractDungeon.player, damageMatrix, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE);
        addToBot(dmgAll);
    }


}
