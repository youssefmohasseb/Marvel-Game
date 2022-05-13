package model.effects;
import model.world.Champion;
public class Embrace extends Effect {
	public Embrace(int duration) {
		super("Embrace", duration, EffectType.BUFF);
	}
	public void apply(Champion c){
		int x = 0;
		int s= c.getMana();
		int y= c.getAttackDamage();
		int w= c.getSpeed();
		for(int i = 0; i<c.getAbilities().size(); i++) {
			if(c.getAppliedEffects().get(i) instanceof Embrace){
		        x = (int) (0.2 * c.getMaxHP());
				c.setCurrentHP( c.getCurrentHP() + x);
				s=(int)(c.getMana()*(1.2));
				c.setMana(s);
				y=(int)(c.getAttackDamage()*(1.2));
				c.setAttackDamage(y);
				w=(int)(c.getSpeed()*(1.2));
				c.setSpeed(w);
			}
			i++;
			}
	}
	public void remove(Champion c){
		int w = c.getSpeed();
		int x= 0;
		int y = c.getAttackDamage();
		int s= c.getMana();
		
		for(int i = 0; i<c.getAbilities().size(); i++) {
			if(c.getAppliedEffects().get(i) instanceof Embrace){
				w= (int)(c.getSpeed()/1.2);
				c.setSpeed(w);	
				y= (int)(c.getAttackDamage()/(1.2));
				c.setAttackDamage(y);
				s= (int)(c.getMana()/(1.2));
				c.setMana(s);
				x = (int) (0.2 * c.getMaxHP());
				c.setCurrentHP(x);
				
			}
			i++;
		
		}
	
		
	}
}

