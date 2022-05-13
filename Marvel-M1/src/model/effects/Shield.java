package model.effects;

import model.world.Champion;

public class Shield extends Effect {

	public Shield( int duration) {
		super("Shield", duration, EffectType.BUFF);
		
	}
	public void apply(Champion c){
		int s = c.getSpeed();
		for(int i = 0; i<c.getAbilities().size(); i++){
			if(c.getAppliedEffects().get(i) instanceof Shield){
					s = (int)(c.getSpeed()*(1.02));
					c.setSpeed(s);
			}
			
			i++;
		}
	}
	public void remove(Champion c){
		int s = c.getSpeed();
		for(int i = 0; i<c.getAbilities().size(); i++){
			if(c.getAppliedEffects().get(i) instanceof Shield){
					s = (int)(c.getSpeed()/(1.02));
					c.setSpeed(s);
			}
			
			i++;
		}
	}

}
