package com.picklegames.entities;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.picklegames.game.FishGame;

public class FishAI extends Fish {
	public FishAI(int state) {
		super(state);
	}

	private Food foodTarget;
 
	
	public void init() {
		FishGame.res.loadTexture("images/fish2.png", "fish2");
		FishGame.res.loadTexture("images/fish2_dead.png", "fish2_dead");
		
		System.out.println(fishState);
		if(getFishState() == FishState.ALIVE){
			setTex(FishGame.res.getTexture("fish2"));
		}else{
			setTex(FishGame.res.getTexture("fish2_dead"));
		}
		
		TextureRegion texR = new TextureRegion(getTex());
		setTexR(TextureRegion.split(texR.getTexture(), 250, 250)[0]);
		setAnimation(getTexR(), DELAY_STOP);
		setWidth(getWidth() * .25f);
		setHeight(getHeight() * .25f);

		setFacing(1);
		setTargets(new Stack<Vector2>());
		
		setMAX_HP(100);
		setMAX_SPEED(1.5f);
		setHealth(getMAX_HP() / 2);
		setSpeed(getMAX_SPEED());
	}

	boolean isDead = false;
	public void update(float dt) {
		super.update(dt);

		System.out.println("food target " + getTargets().size());
		if(!getTargets().isEmpty()){
			System.out.println("food target " + getTargets().peek());			
		}
		
		if(getFishState() == FishState.DEAD && !isDead){
			setTex(FishGame.res.getTexture("fish2_dead"));
			setTexR(TextureRegion.split(getTex(), 250, 250)[0]);
			setAnimation(getTexR(), DELAY_STOP);
			setWidth(getWidth() * .25f);
			setHeight(getHeight() * .25f);
			isDead = true;
		}
	}

	public void addFoodTarget(Food target) {
		System.out.println("food aquired");
		if(this.foodTarget == null){
			this.foodTarget = target;
		}
		if (!this.foodTarget.equals(target)) {
			this.foodTarget = target;
			System.out.println(foodTarget);
			addTarget(foodTarget.getWorldPosition().x, foodTarget.getWorldPosition().y);
		}
	}
}
