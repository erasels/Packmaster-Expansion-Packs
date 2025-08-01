package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.orbs.runicpack.Glyph;
import thePackmaster.stances.runicpack.RunicStance;
import thePackmaster.util.Wiz;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Secrets extends AbstractRunicCard {

    private static final int COST = 0;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    public final static String ID = makeID("Secrets");


    public Secrets() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new IncreaseMaxOrbAction(magicNumber));
        Wiz.atb(new AbstractGameAction() {
            public void update() {
                this.isDone = true;
                Iterator var1 = abstractPlayer.orbs.iterator();

                while(true) {
                    AbstractOrb orb;
                    do {
                        if (!var1.hasNext()) {
                            return;
                        }

                        orb = (AbstractOrb)var1.next();
                    } while(!(orb instanceof EmptyOrbSlot));
                    Wiz.att(new ChannelAction(new Glyph()));
                }
            }
        });

        this.addToBot(new ChangeStanceAction(new RunicStance()));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}
