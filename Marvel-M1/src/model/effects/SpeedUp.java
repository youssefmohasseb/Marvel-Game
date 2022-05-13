package model.effects;

import model.world.Champion;

public class SpeedUp extends Effect{

	public SpeedUp(int duration) {
		super("SpeedUp",duration,EffectType.BUFF);
	}
	public void apply(Champion c){
		int w= c.getSpeed();
		int x = c.getMaxActionPointsPerTurn();
		int y= c.getCurrentActionPoints();
		for(int i = 0; i<c.getAbilities().size(); i++) {
			if(c.getAppliedEffects().get(i) instanceof SpeedUp){
				w=(int)(c.getSpeed()*(1.15));
				c.setSpeed(w);
				x= (int)(c.getMaxActionPointsPerTurn()+1);
				c.setMaxActionPointsPerTurn(x);
				y= (int)(c.getMaxActionPointsPerTurn()+1);
				c.setMaxActionPointsPerTurn(y);
			}
			i++;
			}
	}
	public void remove(Champion c){
		int w= c.getSpeed();
		int x = c.getMaxActionPointsPerTurn();
		int y= c.getCurrentActionPoints();
		for(int i = 0; i<c.getAbilities().size(); i++) {
			if(c.getAppliedEffects().get(i) instanceof SpeedUp){
				w= (int)(c.getSpeed()/1.15);
				c.setSpeed(w);	
				x= (int)(c.getMaxActionPointsPerTurn()-1);
				c.setAttackDamage(x);
				y= (int)(c.getMaxActionPointsPerTurn()-1);
				c.setMana(y);
		
	}
}
	}
}

