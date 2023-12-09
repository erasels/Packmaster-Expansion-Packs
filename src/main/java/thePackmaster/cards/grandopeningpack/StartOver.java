package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.grandopening.StartupTriggerAction;
import thePackmaster.actions.transmutationpack.DrawFilteredCardsAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class StartOver extends AbstractGrandOpeningCard {
    public final static String ID = makeID("StartOver");

    public StartOver() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        atb(new ShuffleAllAction());
        atb(new ShuffleAction(AbstractDungeon.player.drawPile, false));
        int toDraw = AbstractDungeon.player.gameHandSize;
        while (toDraw > 0) {
            atb(new DrawFilteredCardsAction(1, (c) -> c.isInnate));
            toDraw--;
        }
        atb(new StartupTriggerAction());

    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}