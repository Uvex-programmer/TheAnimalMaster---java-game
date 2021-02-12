package com.company.animals;

import com.company.foods.Food;
import com.company.GameHelper;
import com.company.foods.SuperFood;
import com.company.foods.Vegetables;

public class Parrot extends Animal {

    private final int maxAge;
    private final String animalType;
    private final int startPrice;
    private final int vetCost;
    private int health;

    public Parrot() {
        this.startPrice = 20;
        this.maxAge = 4;
        this.animalType = "parrot";
        this.vetCost = 5;
        this.health = 100;
    }

    @Override
    public boolean canEat(Food food) {
        if(food instanceof Vegetables){
            return true;
        }
        return food instanceof SuperFood;
    }

    @Override
    public void eat(Food food) {
        if (canEat(food)) {
            if (this.health >= 100) {
                this.health = 100;
                System.out.println("This animal is already at full health! ");
                GameHelper.menuHelper();
            }
            if (this.health < 100) {
                if(food instanceof SuperFood){
                    if(this.health >= 50)
                        this.health = this.health + (int) (this.health * 0.20);
                    if(this.health < 50)
                        this.health = this.health + 15;
                    if (this.health > 100) {
                        this.health = 100;
                        System.out.println(getName() + " -> is now at full health!" + getHealth() + "%");
                        GameHelper.menuHelper();

                    }
                }
                if(food instanceof Vegetables){
                    if(this.health >= 50)
                        this.health = this.health + (int) (this.health * 0.10);
                    if(this.health < 50)
                        this.health = this.health + 10;
                    if (this.health > 100) {
                        this.health = 100;
                        System.out.println(getName() + " -> is now at full health!" + getHealth() + "%");
                        GameHelper.menuHelper();
                    }
                }
            }
        }
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Override
    public String getAnimalType() {
        return animalType;
    }

    @Override
    public int getStartPrice() {
        return startPrice;
    }

    @Override
    public int getVetCost() {
        return vetCost;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }
}
