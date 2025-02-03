package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;

//REFS: Transmogrifier (pickthemall)
public class FlyingSwords extends AbstractBladeStormCard implements StartupCard {
    public final static String ID = makeID("FlyingSwords");
    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;
    private static final int HITS = 2;
    private static final int PLATED_ARMOR = 2;

    public FlyingSwords() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = PLATED_ARMOR;
    }

    @Override
    public boolean atBattleStartPreDraw() {
        Wiz.applyToSelf(new PlatedArmorPower(player, magicNumber));
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < HITS; i++) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}
