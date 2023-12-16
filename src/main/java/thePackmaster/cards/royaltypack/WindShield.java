package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.WindShieldAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WindShield extends AbstractRoyaltyCard {
    public final static String ID = makeID("WindShield");
    public final static int BLOCK_GIVEN_BY_DISCARD = 4;

    public WindShield(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block =  BLOCK_GIVEN_BY_DISCARD;
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new WindShieldAction(block));
    }
}
