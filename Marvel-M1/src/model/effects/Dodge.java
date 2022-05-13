package model.effects;
import model.world.Champion;
public class Dodge extends Effect {
	public Dodge(int duration) {
		super("Dodge", duration, EffectType.BUFF);
	}
	public void apply(Champion c){
		int i = 0;
		int s = c.getSpeed();
		while(i<c.getAppliedEffects().size()){
			if(c.getAppliedEffects().get(i) instanceof Dodge){
					s = (int)(c.getSpeed()*(1.05));
			}
			c.setSpeed(s);
			i++;
		}
	}
	public void remove(Champion c){
		int s = c.getSpeed();
		for(int i = 0; i<c.getAbilities().size(); i++) {
			if(c.getAppliedEffects().get(i) instanceof Dodge){
				s = (int)(c.getSpeed()/(1.05));
				c.setSpeed(s);
			}
		i++;}
		
	}

}
