package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.util.Wiz.atb;

    public abstract class AbstractSmoothCard extends AbstractPackmasterCard {

        public AbstractSmoothCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
            super(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
            setBackgroundTexture(
                    "anniv5Resources/images/512/disco/" + type.name().toLowerCase() + ".png",
                    "anniv5Resources/images/1024/disco/" + type.name().toLowerCase() + ".png"
            );
        }
        public void applyToSelf(AbstractPower po) {
            atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
        }
        public int secondBlock;
        public int baseSecondBlock = -1;
        public boolean upgradedSecondBlock;
        public boolean isSecondBlockModified;
        @Override
        public void applyPowers() {
            super.applyPowers();
            if (baseSecondBlock > -1) {
                int originalBaseBlock = baseBlock;
                int originalBlock = block;
                boolean originalIsBlockModified = isBlockModified;

                baseBlock = baseSecondBlock;
                super.applyPowers();
                isSecondBlockModified = (secondBlock != baseSecondBlock);
                secondBlock = block;

                baseBlock = originalBaseBlock;
                block = originalBlock;
                isBlockModified = originalIsBlockModified;
            }
        }
        public void resetAttributes() {
            super.resetAttributes();
            secondBlock = baseSecondBlock;
            isSecondBlockModified = false;
        }
        public void displayUpgrades() {
            super.displayUpgrades();
            if (upgradedSecondBlock) {
                secondBlock = baseSecondBlock;
                isSecondBlockModified = true;
            }
        }
        protected void upgradeSecondBlock(int amount) {
            baseSecondBlock += amount;
            secondBlock = baseSecondBlock;
            upgradedSecondBlock = true;
        }
        protected void upSecondBlock(int x) {
            upgradeSecondBlock(x);
        }

    }
