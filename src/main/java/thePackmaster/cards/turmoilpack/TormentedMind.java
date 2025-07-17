package thePackmaster.cards.turmoilpack;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MultiGroupSelectAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TormentedMind extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("TormentedMind");
    private static final String SCREEN_MSG = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("TormentedMindScreen")).TEXT[0];
    private static final int COST = 1;
    private static final int DRAW = 1;
    private static final int UPGRADE_DRAW = 1;
    private static final int EXHAUST = 2;

    public TormentedMind() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = DRAW;
        MultiCardPreview.add(this, new Burden(), new Dazed());
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));
        this.addToBot(new MakeTempCardInHandAction(new Burden()));
        this.addToBot(new MakeTempCardInHandAction(new Dazed()));
        this.addToBot(new MultiGroupSelectAction(SCREEN_MSG, (list, map) ->
        {
            List<AbstractCard> l = new ArrayList<>(list);
            Collections.reverse(l);
            for (AbstractCard c : l) {
                this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
        }, EXHAUST, CardGroup.CardGroupType.DRAW_PILE, CardGroup.CardGroupType.DISCARD_PILE));
    }
}
