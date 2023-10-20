package thePackmaster.cards.dancepack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractDanceCard extends AbstractPackmasterCard
{
    public AbstractDanceCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "dance");
    }
    public AbstractDanceCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, "dance");
    }

    protected static String makeID(String cardID)
    {
        return SpireAnniversary5Mod.makeID(cardID);
    }

    protected void setDamage(int dmg)
    {
        baseDamage = dmg;
    }
    protected void setBlock(int blk)
    {
        baseBlock = blk;
    }
    protected void setMN(int magic)
    {
        baseMagicNumber = magicNumber = magic;
    }
}
