package thePackmaster.patches.dancepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface FollowUpInterface
{
    AbstractCard.CardType getFollowUpCard();
    void followUpEffect(AbstractMonster target);
}
