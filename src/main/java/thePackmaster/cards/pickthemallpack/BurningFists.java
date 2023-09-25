package thePackmaster.cards.pickthemallpack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;

public class BurningFists extends AbstractPickThemAllCard implements OnObtainCard {
    public static final String ID = SpireAnniversary5Mod.makeID("BurningFists");
    private static final int COST = 1;
    private static final int DAMAGE = 4;
    private static final int UPGRADE_DAMAGE = 2;
    private static final int STRENGTH = 1;
    private static final int PICKUP_HP_LOSS = 4;

    public BurningFists() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = STRENGTH;
        this.secondMagic = this.baseSecondMagic = PICKUP_HP_LOSS;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
    }

    @Override
    public void onObtainCard() {
        // We want to avoid negative scenarios like getting this from Astrolabe or Pandora's Box at low enough HP that
        // it would kill the player, so we make this not reduce the player below 1 HP
        int hpLoss = Math.min(PICKUP_HP_LOSS, AbstractDungeon.player.currentHealth);
        if (hpLoss > 0) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, hpLoss, DamageInfo.DamageType.HP_LOSS)));
            }
            else {
                AbstractDungeon.topLevelEffectsQueue.add(new AbstractGameEffect() {
                    @Override
                    public void update() {
                        AbstractDungeon.player.damage(new DamageInfo(null, hpLoss, DamageInfo.DamageType.HP_LOSS));
                        this.isDone = true;
                    }
                    @Override
                    public void render(SpriteBatch spriteBatch) {}
                    @Override
                    public void dispose() {}
                });
            }
        }
    }
}
