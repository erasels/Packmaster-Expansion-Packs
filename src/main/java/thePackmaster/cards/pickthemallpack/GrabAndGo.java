package thePackmaster.cards.pickthemallpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.QuestionCard;
import thePackmaster.SpireAnniversary5Mod;

public class GrabAndGo extends AbstractPickThemAllCard implements OnObtainCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GrabAndGo");
    private static final int COST = -2;

    public GrabAndGo() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.isInnate = true;
    }

    @Override
    public void upp() {
        this.isInnate = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public String getDescriptionForCombat() {
        return cardStrings.EXTENDED_DESCRIPTION[1];
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, new QuestionCard());
    }

    @Override
    public void onRemoveFromMasterDeck() {
        AbstractDungeon.player.loseRelic(QuestionCard.ID);
    }
}
