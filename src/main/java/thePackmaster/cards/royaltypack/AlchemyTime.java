package thePackmaster.cards.royaltypack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.PotionSlot;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.royaltypack.MajesticBloodlineAction;
import thePackmaster.actions.royaltypack.PayTributeAction;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.MajesticBloodlineAusterity;
import thePackmaster.cards.royaltypack.optioncards.MajesticBloodlineTribute;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AlchemyTime extends AbstractRoyaltyCard implements OnObtainCard {

    public final static String ID = makeID("AlchemyTime");
    public final static int BASE_AMOUNT_OF_DRAWS = 2;
    public final static int UPGRADED_INCREASE_OF_DRAWS = 1;

    public final static int GOLD_COST = 25;

    public AlchemyTime(){
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = BASE_AMOUNT_OF_DRAWS;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADED_INCREASE_OF_DRAWS);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new PayTributeAction(GOLD_COST));
        Wiz.atb(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
    }

    @Override
    public void onObtainCard(){
        AbstractDungeon.player.potionSlots += 1;
        AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
        AbstractDungeon.player.gainGold(75);
        CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
    }

}
