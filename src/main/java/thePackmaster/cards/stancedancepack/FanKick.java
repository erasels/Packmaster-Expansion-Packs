package thePackmaster.cards.stancedancepack;


import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.DivinityStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.NewExpr;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.legacypack.FetchCardToHandAction;
import thePackmaster.powers.stancedancepack.GainEnergyOnStanceEnter;
import thePackmaster.powers.stancedancepack.NextWovenCheaper;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.cthulhupack.AbstractCthulhuCard.loseSanity;


public class FanKick extends AbstractStanceDanceCard {
    public final static String ID = makeID("FanKick");

    public FanKick() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;
    }

    public static boolean changesStance(AbstractCard card) {
        ClassPool pool = Loader.getClassPool();
        final boolean[] stanceChangeFound = {false};
        try {
            CtClass ctClass = pool.get(card.getClass().getName());
            ctClass.defrost();
            CtMethod useMethod;
            useMethod = ctClass.getDeclaredMethod("use");
            useMethod.instrument(new ExprEditor() {
                @Override
                public void edit(NewExpr n) {
                    try {
                        CtConstructor constructor = n.getConstructor();
                        CtClass activeClass = constructor.getDeclaringClass();

                        if (activeClass != null) {
                            CtClass[] plz = {activeClass};
                            //Loop until we either run out of supers or we find a matching class
                            while (activeClass != null && !plz[0].getName().equals(ChangeStanceAction.class.getName())) {
                                activeClass = activeClass.getSuperclass();
                                plz[0] = activeClass;
                            }
                            //We found it, nice
                            if (activeClass != null && plz[0].getName().equals(ChangeStanceAction.class.getName())) {
                                stanceChangeFound[0] = true;
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
            });
        } catch (NotFoundException | CannotCompileException ignore) {
            return false;
        }
        return stanceChangeFound[0];
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        SpireAnniversary5Mod.logger.info(p.stance.ID);

        switch (p.stance.ID) {
            case NeutralStance.STANCE_ID: {
                ArrayList<AbstractCard> valids = new ArrayList<>();
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (changesStance(c)) {
                        valids.add(c);
                    }
                }
                if (!valids.isEmpty()) {
                    addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractDungeon.player.drawPile.moveToHand(Wiz.getRandomItem(valids));
                        }
                    });
                }
                break;
            }
            //Manual ID seems be necessary here? Weaver.Stance_ID errors out, constant expression required
            case "anniv5:Weaver": {
                Wiz.applyToSelf(new NextWovenCheaper(p, 1));
                break;
            }
            case "anniv5:Venemous": {
                Wiz.applyToEnemy(m, new PoisonPower(p, m, 5));
                break;
            }
            case "anniv5:Cunning": {
                Wiz.applyToEnemy(m, new WeakPower(m, 1, false));
                Wiz.applyToEnemy(m, new VulnerablePower(m, 1, false));
                break;
            }
            case CalmStance.STANCE_ID: {
                Wiz.atb(new DrawCardAction(2));
                break;
            }
            case WrathStance.STANCE_ID: {
                Wiz.applyToSelf(new VigorPower(p, 4));
                break;
            }
            case "anniv5:Angry": {
                Wiz.applyToSelf(new VigorPower(p, 4));
                break;
            }
            case "anniv5:Aggression": {
                dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
                break;
            }
            case "anniv5:Serene": {
                blck();
                break;
            }
            case "anniv5:Ancient": {
                Wiz.applyToSelf(new ArtifactPower(p, 1));
                break;
            }
            case "anniv5:Nightmare": {
                loseSanity(5);
                break;
            }
            case DivinityStance.STANCE_ID: {
                Wiz.applyToSelf(new MantraPower(p, 5));
                break;
            }
            default: {
                break;
            }
        }


    }


    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoom) {

            switch (AbstractDungeon.player.stance.ID) {
                case NeutralStance.STANCE_ID: {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
                    break;
                }
                //Manual ID seems be necessary here? Weaver.Stance_ID errors out, constant expression required
                case "anniv5:Weaver": {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
                    break;
                }
                case "anniv5:Venemous": {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[3];
                    break;
                }
                case "anniv5:Cunning": {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[4];
                    break;
                }
                case CalmStance.STANCE_ID: {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[5];
                    break;
                }
                case WrathStance.STANCE_ID: {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[6];
                    break;
                }
                case "anniv5:Angry": {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[6];
                    break;
                }
                case "anniv5:Aggression": {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[7];
                    break;
                }
                case "anniv5:Serene": {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[8];
                    break;
                }
                case "anniv5:Ancient": {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[9];
                    break;
                }
                case "anniv5:Nightmare": {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[10];
                    break;
                }
                case DivinityStance.STANCE_ID: {
                    this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[11];
                    break;
                }
                default: {
                    break;
                }
            }
        }
        this.initializeDescription();
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }
}




