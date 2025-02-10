package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bladestormpack.WindrushPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

//REFS: Transmogrifier (pickthemallpack), SmithingHammer (coresetpack), SwordBoomerang & DaggerSpray & RipAndTear (base game).
public class FlyingSwords extends AbstractBladeStormCard implements StartupCard {
    public final static String ID = makeID("FlyingSwords");
    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;
    private static final int HITS = 2;
    private static final int WINDRUSH = 3;
    private static final int UPG_WINDRUSH = 1;

    public FlyingSwords() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = WINDRUSH;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        Wiz.applyToSelf(new WindrushPower(player, magicNumber));
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < HITS; i++) {
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_WINDRUSH);
    }
}
