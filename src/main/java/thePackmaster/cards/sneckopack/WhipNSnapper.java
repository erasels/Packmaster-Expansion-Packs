package thePackmaster.cards.sneckopack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WhipNSnapper extends AbstractSneckoCard {


    public final static String ID = makeID("WhipNSnapper");

    public WhipNSnapper() {
        super(ID, 2, AbstractCard.CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.GREEN.cpy()), 0.2F));
        addToBot(new WaitAction(0.2f));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        for (int i = 0; i < magicNumber; i++) {
            if (AbstractDungeon.cardRandomRng.randomBoolean()) {
                Wiz.applyToEnemy(m, new WeakPower(m, 1, false));
            } else {
                Wiz.applyToEnemy(m, new VulnerablePower(m, 1, false));
            }
        }
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}
