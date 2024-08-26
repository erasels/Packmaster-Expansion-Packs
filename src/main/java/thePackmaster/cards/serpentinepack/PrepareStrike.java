package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.powers.serpentinepack.EvasiveTacticsPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PrepareStrike extends AbstractSerpentineCard {

    private static final int COST = 1;
    public final static String ID = makeID("PrepareStrike");
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public PrepareStrike() {
        super(ID, COST, AbstractCard.CardType.SKILL, CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = block = BLOCK;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, block));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int uniqueDebuffs = numberUniqueDebuffs();
                if (uniqueDebuffs != 0){
                    addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new VigorPower(abstractPlayer, uniqueDebuffs * magicNumber), uniqueDebuffs * magicNumber));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void applyPowers(){
        super.applyPowers();

        int uniqueDebuffs = numberUniqueDebuffs();
        if (uniqueDebuffs == 1){
            this.rawDescription = EXTENDED_DESCRIPTION[0] + uniqueDebuffs + EXTENDED_DESCRIPTION[1];
        }
        else {
            this.rawDescription = EXTENDED_DESCRIPTION[0] + uniqueDebuffs + EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public int numberUniqueDebuffs(){
        ArrayList<String> debuffList = new ArrayList<>();
        for (AbstractMonster mo : Wiz.getEnemies()){
            for (AbstractPower p : mo.powers){
                if (p.type.equals(AbstractPower.PowerType.DEBUFF) && !debuffList.contains(p.ID)){
                    debuffList.add(p.ID);
                }
            }
        }
        return debuffList.size();
    }

    @Override
    public void triggerOnGlowCheck(){
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (numberUniqueDebuffs() > 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeBlock(UPG_BLOCK);
    }
}
