package thePackmaster.cards.tf2pack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;
import thePackmaster.actions.tf2pack.ExhaustToEnergyAndCreateCardAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BigEarner extends AbstractTF2Card {
    public final static String ID = makeID("BigEarner");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 1;

    private static final int DAMAGE = 10;
    private static final int UPGRADE_DAMAGE = 3;

    public BigEarner() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
        PersistFields.setBaseValue(this, 2);
        this.cardsToPreview = new Ammo();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new GoldenSlashEffect(m.hb.cX + 30.0F * Settings.scale, m.hb.cY, true), 0.1F));
        this.dmg(m, AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new ExhaustToEnergyAndCreateCardAction(cardsToPreview));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}