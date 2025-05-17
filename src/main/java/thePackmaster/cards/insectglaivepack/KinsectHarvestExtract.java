package thePackmaster.cards.insectglaivepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.insectglaivepack.ExtractedEssenceRed;
import thePackmaster.cardmodifiers.insectglaivepack.ExtractedEssenceWhite;
import thePackmaster.cardmodifiers.insectglaivepack.ExtractedEssenceYellow;
import thePackmaster.packs.InsectGlaivePack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class KinsectHarvestExtract extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(KinsectHarvestExtract.class.getSimpleName());

    public KinsectHarvestExtract() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded)
            addToBot(new DrawCardAction(p, 1));

        List<AbstractCardModifier> list = new ArrayList<>();
        list.add(new ExtractedEssenceRed());
        list.add(new ExtractedEssenceWhite());
        list.add(new ExtractedEssenceYellow());

        list = list.stream().filter(e -> !(CardModifierManager.hasModifier(this, e.identifier(this))
                || AbstractDungeon.player.hasPower(e.identifier(this)+"Power"))).collect(Collectors.toList());

//        InsectGlaivePack.logger.info("====" + list + "====");

        if (!list.isEmpty())
            list.get(cardRandomRng.random(list.size() - 1)).onUse(null, null, null);
    }

    @Override
    public void upp() {
    }


    public static void atTurnStartOwn() {
        ArrayList<AbstractCard> cards = (ArrayList<AbstractCard>) AbstractDungeon.player.exhaustPile.group.clone();
        for (AbstractCard c : cards) {
            if (c instanceof KinsectHarvestExtract) {
                AbstractDungeon.player.hand.addToHand(c);
                AbstractDungeon.player.exhaustPile.removeCard(c);
                c.unhover();
                c.fadingOut = false;
            }
        }
    }
}
