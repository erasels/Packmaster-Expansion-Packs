package thePackmaster.cards.royaltypack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Soulstone extends AbstractRoyaltyCard implements StartupCard {

    public final static String ID = makeID("Soulstone");

    public Soulstone(){
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 7;
        magicNumber = baseMagicNumber = 5;
    }

    @Override
    public void upp() {

        upgradeDamage(2);
        upgradeMagicNumber(4);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public boolean atBattleStartPreDraw()
    {
        this.addToBot(new GainGoldAction(magicNumber));
        int amountOfSoulstones = 0;
        for(AbstractCard card: AbstractDungeon.player.masterDeck.group){
            if (card.cardID == Soulstone.ID){
                amountOfSoulstones += 1;
            }
        }
        AbstractDungeon.effectList.add(new GainGoldTextEffect(magicNumber * amountOfSoulstones));
        CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
        return true;
    }
}
