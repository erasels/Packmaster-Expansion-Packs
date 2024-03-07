package thePackmaster.cards.grandopeningpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.InnateMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.gemspack.AbstractGemsCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Kunzite extends AbstractGemsCard {
    public final static String ID = makeID("Kunzite");

    public Kunzite() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW, "grandopening");
        isInnate = true;
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public AbstractCardModifier myMod() {
        return new InnateMod();
    }

    public boolean needsUpgrade() {
        return !upgraded;
    }

    @Override
    public void upp() {
        isInnate = false;
    }
}