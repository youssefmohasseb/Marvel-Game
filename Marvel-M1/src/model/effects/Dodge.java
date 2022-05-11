package model.effects;
import model.world.Champion;
public class Dodge extends Effect {
	public Dodge(int duration) {
		super("Dodge", duration, EffectType.BUFF);
	}
	public void apply(Champion c){
		int i = 0;
		int s = 0 ;
		while(i<c.getAppliedEffects().size()){
			if(c.getAppliedEffects().get(i) instanceof Dodge){
				s = ((int)c.getSpeed()/100)*105;
			}
			c.setSpeed(s);
		}
	}
	public void remove(Champion c){
		
	}

}
