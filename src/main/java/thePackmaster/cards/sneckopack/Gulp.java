package thePackmaster.cards.sneckopack;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.TempHPRegenPower;
import thePackmaster.util.Wiz;

import java.util.Arrays;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Gulp extends AbstractSneckoCard {

    public final static String ID = makeID("Gulp");
    private static final int ABL = 3, UPG_ABL = 1;

    private static TooltipInfo ablation;

    public Gulp() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = ABL;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new SFXAction("EVENT_VAMP_BITE", 0.05F));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                int energySpent = Wiz.getLogicalCardCost(Gulp.this);

                if (energySpent > 0) {
                    //Triggers after gain
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractPower pow = p.getPower(TempHPRegenPower.POWER_ID);
                            if (pow != null) {
                                //Trigger power energySpent times
                                for (int i = 0; i < energySpent; i++) {
                                    pow.atEndOfTurn(true);
                                }
                            }
                        }
                    });

                    //Happens first
                    Wiz.applyToSelfTop(new TempHPRegenPower(p, energySpent * magicNumber));
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        selfRetain = true;
        if(timesUpgraded > 1) {
            upgradeMagicNumber(UPG_ABL);
        }
    }

    public List<TooltipInfo> getCustomTooltips() {
        if (ablation == null) {
            AbstractPower p = new TempHPRegenPower(null, 999);
            ablation = new TooltipInfo(p.name, p.description.replace("999", "X"));
        }

        return Arrays.asList(ablation);
    }
}
