package model.effects;
import java.util.ArrayList;
import model.abilities.*;
import model.world.*;
public class Disarm extends Effect {
	public Disarm( int duration) {
		super("Disarm", duration, EffectType.DEBUFF);
	}
	public void apply(Champion c){
		Champion c1;
		DamagingAbility d = new DamagingAbility("Punch",0,1,1,AreaOfEffect.SINGLETARGET,1,50);
		int i = 0;
		while(i<c.getAppliedEffects().size())
		if(c.getAppliedEffects().get(i) instanceof Disarm){
			c.getAbilities().add(d);
		}
		i++;
	}
	public void remove(Champion c){
		
		
	}
}
