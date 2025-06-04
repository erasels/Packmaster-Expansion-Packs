package thePackmaster.cards.insectglaivepack.derivative;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.insectglaivepack.ExtractedEssenceRed;
import thePackmaster.cardmodifiers.insectglaivepack.ExtractedEssenceWhite;
import thePackmaster.cardmodifiers.insectglaivepack.ExtractedEssenceYellow;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.insectglaivepack.KinsectHarvestExtractPower;

public class CardForSectionOfIG extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID(CardForSectionOfIG.class.getSimpleName());

    public CardForSectionOfIG() {
        this(0);
    }

    public CardForSectionOfIG(int num) {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = num;
        if (num == 0) CardModifierManager.addModifier(this, new ExtractedEssenceRed());
        if (num == 1) CardModifierManager.addModifier(this, new ExtractedEssenceWhite());
        if (num == 2) CardModifierManager.addModifier(this, new ExtractedEssenceYellow());

        this.name = cardStrings.EXTENDED_DESCRIPTION[num];
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[num] + cardStrings.EXTENDED_DESCRIPTION[3];
        initializeDescription();
        initializeTitle();
    }

    @Override
    public void onChoseThisOption() {
        if (AbstractDungeon.player.hasPower(KinsectHarvestExtractPower.ID))
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    ((KinsectHarvestExtractPower) AbstractDungeon.player.getPower(KinsectHarvestExtractPower.ID)).colors[baseMagicNumber] = 1;
                    this.isDone = true;
                }
            });
        else
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KinsectHarvestExtractPower(AbstractDungeon.player, this.baseMagicNumber)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upp() {
    }
}
