package thePackmaster.cards.royaltypack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.PotionSlot;
import thePackmaster.actions.royaltypack.NeedsGoldToBePartiallyDoneAction;
import thePackmaster.powers.royaltypack.AlchemyTimePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AlchemyTime extends AbstractRoyaltyCard implements OnObtainCard {

    public final static String ID = makeID("AlchemyTime");
    public final static int BASE_AMOUNT_OF_DRAWS = 2;
    public final static int UPGRADED_INCREASE_OF_DRAWS = 1;

    public final static int GOLD_COST = 25;

    public AlchemyTime() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(CardTags.HEALING);
        exhaust = true;
        baseMagicNumber = magicNumber = BASE_AMOUNT_OF_DRAWS;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADED_INCREASE_OF_DRAWS);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractGameAction[] actionArrayAfterPay = new AbstractGameAction[1];
        actionArrayAfterPay[0] = new ApplyPowerAction(
                Wiz.p(), Wiz.p(), new AlchemyTimePower(Wiz.p(), magicNumber));

        Wiz.atb(new NeedsGoldToBePartiallyDoneAction(GOLD_COST,
                new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)),
                actionArrayAfterPay,
                cardStrings.EXTENDED_DESCRIPTION[0]));
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.player.potionSlots += 1;
        AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
        AbstractDungeon.player.gainGold(75);
        CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
    }

}
