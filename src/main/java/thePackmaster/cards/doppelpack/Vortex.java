package thePackmaster.cards.doppelpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.SummonAction;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;
import thePackmaster.orbs.doppelpack.AbstractDoppel;

import java.util.HashMap;

public class Vortex extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Vortex.class.getSimpleName());

    private int lastPlayedTurn = -1;

    public Vortex() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
                if (!affectedCards.containsKey(this)) {
                    ShowDoppel showDoppel = new ShowDoppel();
                    affectedCards.put(this, showDoppel);
                    CardModifierManager.addModifier(this, showDoppel);
                }
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public static boolean onUsedCard(AbstractCard card) {
        if (card instanceof Vortex) {
            return ((Vortex) card).onUsedCard();
        }

        return true;
    }

    public boolean onUsedCard() {
        AbstractPlayer p = AbstractDungeon.player;
        if (lastPlayedTurn != GameActionManager.turn) {
            lastPlayedTurn = GameActionManager.turn;
            for (AbstractCard card : GetAllInBattleInstances.get(uuid)) {
                if (card instanceof Vortex) {
                    ((Vortex) card).lastPlayedTurn = GameActionManager.turn;
                }
            }
            for (AbstractOrb orb : p.orbs) {
                if (orb instanceof AbstractDoppel) {
                    AbstractDoppel doppel = (AbstractDoppel) orb;
                    if (doppel.card.uuid.equals(uuid) && doppel.card instanceof Vortex) {
                        ((Vortex) doppel.card).lastPlayedTurn = GameActionManager.turn;
                    }
                }
            }
            SummonAction.doSummon(this, false);
            AbstractDungeon.player.hand.empower(this);
            return false;
        }

        return true;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        Vortex card = (Vortex) super.makeStatEquivalentCopy();
        card.lastPlayedTurn = this.lastPlayedTurn;
        return card;
    }
}
