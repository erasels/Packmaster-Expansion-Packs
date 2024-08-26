package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.stances.NeutralStance;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.powers.monsterhunterpack.TempRetainCardsPower;
import thePackmaster.stances.serpentinepack.CunningStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ColdCalculated extends AbstractSerpentineCard {

    private static final int COST = 1;
    private static final int MAGIC = 4;
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;
    private static final int UPG_MAGIC = 2;
    public final static String ID = makeID("ColdCalculated");


    public ColdCalculated() {
        super(ID, COST, AbstractCard.CardType.ATTACK, CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new FrostbitePower(abstractMonster, magicNumber), magicNumber));
        if (!AbstractDungeon.player.stance.ID.equals(NeutralStance.STANCE_ID)) {
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new TempRetainCardsPower(abstractPlayer, 2), 2));
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
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_MAGIC);
    }
}
