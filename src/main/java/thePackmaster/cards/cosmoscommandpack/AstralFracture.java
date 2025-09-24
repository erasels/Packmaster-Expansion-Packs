package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.vfx;

public class AstralFracture extends AbstractCosmosCard implements AmplifyCard {
    public final static String ID = makeID("AstralFracture");

    public AstralFracture() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        magicNumber = baseMagicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ORB_DARK_CHANNEL"));
        if (Settings.FAST_MODE)
            vfx(new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F);
        else
            vfx(new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F);
        atb(new AllEnemyApplyPowerAction(p, magicNumber, (q) -> new DistortionPower(q, p, magicNumber)));
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        CardGroup grp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        AbstractCard c = this.makeStatEquivalentCopy();
        c.current_x = c.target_x = this.current_x + AbstractCard.RAW_W * Settings.scale;
        c.current_y = c.target_y = this.current_y;
        grp.addToBottom(c);
        atb(new ExhaustSpecificCardAction(c, grp, true));
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}