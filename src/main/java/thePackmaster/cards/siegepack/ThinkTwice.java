package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ThinkTwice extends AbstractSiegeCard {
    public final static String ID = makeID("ThinkTwice");
    private static final int COST = 2;
    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 3;
    private static final int BLOCK_PER_ATTACKER = 5;
    private static final int UPGRADE_BLOCK_PER_ATTACKER = 2;

    public ThinkTwice() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = BLOCK_PER_ATTACKER;
        //this.attackers = getEnemies().size();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        //Gain 5 (7) block per enemy that intends to attack.
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDeadOrEscaped()) {
                if (mo.intent == AbstractMonster.Intent.ATTACK
                        || mo.intent == AbstractMonster.Intent.ATTACK_BUFF
                        || mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF
                        || mo.intent == AbstractMonster.Intent.ATTACK_DEFEND) {
                    //Wiz.atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
                    Wiz.doBlk(magicNumber);
                }
            }
        }
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_BLOCK_PER_ATTACKER);
    }
}
