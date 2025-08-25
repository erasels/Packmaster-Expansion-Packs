package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import thePackmaster.packs.RunicPack;
import thePackmaster.stances.aggressionpack.AggressionStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AncientBlade extends AbstractRunicCard {

    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int UPG_DMG = 2;
    public final static String ID = makeID("AncientBlade");


    public AncientBlade() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (RunicPack.channeledOrbThisTurn){
            dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        }
    }

    @Override
    public void triggerOnGlowCheck(){
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (RunicPack.channeledOrbThisTurn) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DMG);
    }
}
