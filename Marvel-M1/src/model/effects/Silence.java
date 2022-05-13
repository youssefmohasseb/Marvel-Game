package model.effects;

import model.world.Champion;

public class Silence extends Effect {

	public Silence( int duration) {
		super("Silence", duration, EffectType.DEBUFF);
		
		
	}
	public void apply(Champion c){
		int x = c.getMaxActionPointsPerTurn();
		int y= c.getCurrentActionPoints();
	for(int i = 0; i<c.getAbilities().size(); i++) {
		if(c.getAppliedEffects().get(i) instanceof SpeedUp){
			x= (int)(c.getMaxActionPointsPerTurn()+2);
			c.setMaxActionPointsPerTurn(x);
			y= (int)(c.getMaxActionPointsPerTurn()+2);
			c.setMaxActionPointsPerTurn(y);
		}	
		i++;
		}

}
	public void remove(Champion c){
		int x = c.getMaxActionPointsPerTurn();
		int y= c.getCurrentActionPoints();
	for(int i = 0; i<c.getAbilities().size(); i++) {
		if(c.getAppliedEffects().get(i) instanceof SpeedUp){
			x= (int)(c.getMaxActionPointsPerTurn()-2);
			c.setMaxActionPointsPerTurn(x);
			y= (int)(c.getMaxActionPointsPerTurn()-2);
			c.setMaxActionPointsPerTurn(y);
		}	
		i++;
		}
	}
}