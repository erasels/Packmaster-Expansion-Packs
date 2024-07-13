package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MiningStrike extends AbstractRoyaltyCard {

    public final static String ID = makeID("MiningStrike");


    public MiningStrike(){
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        baseDamage = damage = 15;
        baseMagicNumber = magicNumber = 15;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeDamage(5);
        this.upgradeMagicNumber(5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        this.addToBot(new GainGoldAction(magicNumber));
        AbstractDungeon.effectList.add(new GainGoldTextEffect(magicNumber));
        CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
    }
}
