package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.actions.sneckopack.RandomizeCostAction;
import thePackmaster.powers.boardgamepack.AdvantagePower;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.legacypack.ShootAnythingEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sneckpot extends AbstractSneckoCard {


    public final static String ID = makeID("Sneckpot");

    public Sneckpot() {
        super(ID, -1, AbstractCard.CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 0;
        magicNumber = baseMagicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this,
                (energy, params) -> {

                    int rolls = energy;
                    AbstractPower pow = Wiz.p().getPower(AdvantagePower.POWER_ID);
                    if (pow != null) {
                        rolls += pow.amount;
                        AbstractDungeon.effectList.add(new FlashPowerEffect(pow));
                    }

                    //Happens after, deal damage equal to cost of hand * modifier
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            int dmg = Wiz.hand().group.stream()
                                    .mapToInt(Wiz::getLogicalCardCost)
                                    .sum();
                            int tmp = Sneckpot.this.baseDamage;

                            Sneckpot.this.baseDamage += dmg;
                            Sneckpot.this.calculateCardDamage(m);
                            Sneckpot.this.dmg(m, AttackEffect.BLUNT_HEAVY);
                            Sneckpot.this.baseDamage = tmp;
                        }
                    });

                    //Happens first, draw X cards and muddle their cost
                    Wiz.att(new DrawCardAction(rolls, new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            if(!DrawCardAction.drawnCards.isEmpty()) {
                                Wiz.att(new RandomizeCostAction(DrawCardAction.drawnCards.toArray(new AbstractCard[0])));
                            }
                        }
                    }));
                    return true;
                }, damage));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}