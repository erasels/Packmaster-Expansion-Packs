package thePackmaster.cards.needleworkpack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.vfx.instadeathpack.ColoredThrowDaggerEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Rethread extends AbstractNeedleworkCard {
    public final static String ID = makeID("Rethread");

    public Rethread() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);

        PersistFields.setBaseValue(this, 2);
        baseDamage = damage = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
            addToBot(new VFXAction(new ColoredThrowDaggerEffect(m.hb.cX, m.hb.cY, Color.WHITE)));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        addToBot(new BetterDiscardPileToHandAction(1));
    }

    @Override
    public void upp() {
        PersistFields.upgrade(this, 1);
    }
}
