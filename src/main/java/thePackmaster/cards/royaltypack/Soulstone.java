package thePackmaster.cards.royaltypack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Soulstone extends AbstractRoyaltyCard implements StartupCard {

    public final static String ID = makeID("Soulstone");

    public Soulstone(){
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 7;
        magicNumber = baseMagicNumber = 5;
        exhaust = true;
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
        int totalGoldToGain = calculateTotalAmountOfGold();
        if (totalGoldToGain > 0){
            this.addToBot(new GainGoldAction(totalGoldToGain));
            showGainGoldTextEffect(totalGoldToGain);
        }
        CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
        return true;
    }

    private int calculateTotalAmountOfGold(){
        ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;
        int currentPosition = 0;
        for (int i = 0; i < masterDeck.size(); i++){
            currentPosition = i;
            if (masterDeck.get(i).cardID == Soulstone.ID){
                if (masterDeck.get(i).uuid != this.uuid){
                    return 0;
                }
                else {
                    break;
                }
            }
        }
        int totalAmountOfGold = 0;
        for (int i = currentPosition; i < masterDeck.size(); i++){
            if (masterDeck.get(i).cardID == Soulstone.ID){
                totalAmountOfGold += masterDeck.get(i).magicNumber;
            }
        }
        return totalAmountOfGold;
    }

    private void showGainGoldTextEffect(int totalAmountOfGold) {
        AbstractDungeon.effectList.add(new GainGoldTextEffect(totalAmountOfGold));
    }

}
