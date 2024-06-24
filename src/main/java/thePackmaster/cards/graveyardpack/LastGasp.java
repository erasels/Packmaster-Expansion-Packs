package thePackmaster.cards.graveyardpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.graveyardpack.PlayFromExhaustAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class LastGasp
  extends AbstractGraveyardCard
{
  public static final String ID = makeID("LastGasp");

  private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
  private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
  private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
  
  private static final int COST = 1;

  
	public LastGasp() {
		super(ID, COST, TYPE, RARITY, TARGET);
		
  		this.isEthereal=true;
	}

  public void use(AbstractPlayer p, AbstractMonster m) {
	  AbstractDungeon.actionManager.addToBottom(new PlayFromExhaustAction());
  }

  
	public void upp() {
		this.isEthereal=false;
	}
}
