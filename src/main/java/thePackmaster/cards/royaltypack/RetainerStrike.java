package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.royaltypack.RetainerStrikeRetainAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RetainerStrike extends AbstractRoyaltyCard {

    public final static String ID = makeID("RetainerStrike");
    private static final String[] CHOOSE_RETAIN_TEXT = CardCrawlGame.languagePack.getUIString(
            SpireAnniversary5Mod.makeID("RetainerStrikeAction")).TEXT;

    public RetainerStrike(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        baseDamage = 9;
    }

    @Override
    public void upp()
    {
        this.upgradeDamage(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        Wiz.atb(new RetainerStrikeRetainAction(uuid, upgraded, CHOOSE_RETAIN_TEXT[0]));
    }
}
