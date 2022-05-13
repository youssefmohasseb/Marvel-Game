package model.effects;
import java.util.ArrayList;
import model.abilities.*;
import model.world.*;
public class Disarm extends Effect {
	public Disarm( int duration) {
		super("Disarm", duration, EffectType.DEBUFF);
	}
	public void apply(Champion c){
		DamagingAbility d = new DamagingAbility("Punch",0,1,1,AreaOfEffect.SINGLETARGET,1,50);
		int i = 0;
		while(i<c.getAppliedEffects().size()) {
		if(c.getAppliedEffects().get(i) instanceof Disarm){
			c.getAbilities().add(d);
		}
		i++;
		}
	}
	public void remove(Champion c){
		for(int i = 0; i<c.getAbilities().size(); i++) {
			if(c.getAbilities().get(i).getName().equals("Punch")) {
				c.getAbilities().remove(i);
			}
			i--;
		}
		
	}
}
