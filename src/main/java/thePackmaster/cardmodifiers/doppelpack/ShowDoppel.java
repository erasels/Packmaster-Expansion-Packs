package thePackmaster.cardmodifiers.doppelpack;

import basemod.ReflectionHacks;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.doppelpack.AbstractDoppel;

import java.util.Collections;
import java.util.List;

@AbstractCardModifier.SaveIgnore
public class ShowDoppel extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID("ShowDoppel");

    public AbstractDoppel doppel;

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        this.doppel = AbstractDoppel.createDoppel(card);
        this.doppel.showCharacter = false;
    }

    @Override
    public void onUpdate(AbstractCard card) {
        doppel.updateAnimation();
        doppel.tX = doppel.cX = card.current_x - card.hb.height / 2.0f * MathUtils.sinDeg(card.angle);
        doppel.tY = doppel.cY = card.current_y + card.hb.height / 2.0f * MathUtils.cosDeg(card.angle);
        ReflectionHacks.setPrivate(doppel, AbstractOrb.class, "scale", Settings.scale * Math.min(card.drawScale * 1.33f, 1f));
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        if (doppel != null) {
            return Collections.singletonList(new TooltipInfo(doppel.name, doppel.description));
        }
        return null;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ShowDoppel();
    }
}
