package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.royaltypack.PayTributeAction;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class ThrowSoulstonesTribute extends AbstractRoyaltyCard {

    public final static String ID = makeID("ThrowSoulstonesTribute");
    public final static int DAMAGE = 15;
    public final static int TRIBUTE_GOLD_AMOUNT = 10;
    private AbstractCard originalThrowSoulstonesCard;

    public ThrowSoulstonesTribute(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionTribute.png");
        baseDamage = damage = DAMAGE;
    }

    public ThrowSoulstonesTribute(AbstractCard originalTSCard, int damageToDo){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionTribute.png");
        baseDamage = damage = damageToDo;
        originalThrowSoulstonesCard = originalTSCard;
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
        Wiz.atb(new PayTributeAction(TRIBUTE_GOLD_AMOUNT));
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new BlizzardEffect(this.damage, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            this.addToBot(new VFXAction(new BlizzardEffect(this.damage, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.5F));
        }
        int[] damageMatrix = DamageInfo.createDamageMatrix(this.baseDamage, true);
        DamageAllEnemiesAction dmgAll = new DamageAllEnemiesAction(AbstractDungeon.player, damageMatrix, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE);
        addToBot(dmgAll);
        if (originalThrowSoulstonesCard != null){
            AbstractCard tmp = originalThrowSoulstonesCard.makeSameInstanceOf();
            tmp.purgeOnUse = true;

            Wiz.atb(new NewQueueCardAction(tmp, false));
        }
        else {
            Logger logger = LogManager.getLogger(ID);
            logger.info("Ok, how we reached this we shouldn't be here, call Levender");
        }
    }

}
