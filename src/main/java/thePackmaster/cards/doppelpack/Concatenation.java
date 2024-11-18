package thePackmaster.cards.doppelpack;

import basemod.ReflectionHacks;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.SummonAction;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;

import java.util.HashMap;

public class Concatenation extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Concatenation.class.getSimpleName());

    public Concatenation() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void hover() {
        super.hover();
        if (AbstractDungeon.player != null) {
            AbstractPlayer player = AbstractDungeon.player;
            if (player.hand.contains(this)) {
                if (affectedCards == null) {
                    affectedCards = new HashMap<>();
                }
                player.hand.group.stream()
                        .filter(c -> c.type == CardType.ATTACK && c != this)
                        .findFirst()
                        .ifPresent(card -> {
                            if (!affectedCards.containsKey(card)) {
                                ShowDoppel showDoppel = new ShowDoppel();
                                affectedCards.put(card, showDoppel);
                                CardModifierManager.addModifier(card, showDoppel);
                            }
                        });
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null && !m.isDeadOrEscaped()) {
            this.addToBot(new SFXAction("THUNDERCLAP", 0.05F));
            LightningEffect effect = new LightningEffect(m.drawX, m.drawY);
            ReflectionHacks.setPrivate(effect, AbstractGameEffect.class, "color", Color.GOLD.cpy());
            this.addToBot(new VFXAction(effect, 0.05F));
        }
        this.addToBot(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                    AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new DrawCardAction(1));
        this.addToBot(new WaitAction(0.1f));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                p.hand.group.stream()
                        .filter(c -> c.type == CardType.ATTACK)
                        .findFirst()
                        .ifPresent(c -> {
                            AbstractDungeon.player.hand.empower(c);
                            SummonAction.doSummon(c, true);
                        });
                isDone = true;
            }
        });
    }
}
