package thePackmaster.cardmodifiers.witchesstrike;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.InfestModifier;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

public class WickedModifier extends AbstractCardModifier {
    public static String MOD_ID = SpireAnniversary5Mod.makeID("Wicked");
    private int wickedcost = 0;
    private static final Texture tex = TexLoader.getTexture("anniv5Resources/images/ui/wickedicon.png");

    public WickedModifier(int wickedcost) {
        this.wickedcost = wickedcost;
    }

    public boolean isInherent(AbstractCard card) {
        return true;// 31
    }
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).text(String.valueOf(this.wickedcost)).render(card);
    }
    public void onRender(AbstractCard card, SpriteBatch sb) {
        Color textColor = Color.WHITE.cpy();
        if (AbstractDungeon.player != null) {
            int orbs = 0;
            for (AbstractOrb o : AbstractDungeon.player.orbs) {
                if (!(o instanceof EmptyOrbSlot)) {
                    orbs++;
                }
            }
            if (orbs < wickedcost){
                textColor = Color.RED.cpy();
            }
        }
        ExtraIcons.icon(tex).text(String.valueOf(this.wickedcost)).textColor(textColor).render(card);// 51
    }// 52

    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, MOD_ID);// 61
    }

    public AbstractCardModifier makeCopy() {
        return new WickedModifier(wickedcost);// 66
    }

    public String identifier(AbstractCard card) {
        return MOD_ID;// 56
    }
}