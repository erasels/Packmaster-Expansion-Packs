package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.orbs.runicpack.Glyph;
import thePackmaster.util.Wiz;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Secrets extends AbstractRunicCard {

    private static final int COST = 2;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    public final static String ID = makeID("Secrets");


    public Secrets() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new IncreaseMaxOrbAction(magicNumber));
        Wiz.atb(new AbstractGameAction() {
            public void update() {
                this.isDone = true;
                for (AbstractOrb o : AbstractDungeon.player.orbs){
                    if (o instanceof EmptyOrbSlot){
                        Wiz.att(new ChannelAction(new Glyph()));
                    }
                }
            }
        });
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}
