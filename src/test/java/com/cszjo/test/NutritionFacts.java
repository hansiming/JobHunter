package com.cszjo.test;

/**
 * Created by Han on 2017/3/31.
 */
public class NutritionFacts {

    private final int servingSize;    //required 必须
    private final int servings;       //required 必须
    private final int calories;       //optional 可选
    private final int fat;            //optional 可选
    private final int sodium;         //optional 可选
    private final int carbohydrate;   //optional 可选

    public static class Builder {

        private final int servingSize;    //required 必须
        private final int servings;       //required 必须
        private int calories      = 0;    //optional 可选
        private int fat           = 0;    //optional 可选
        private int sodium        = 0;    //optional 可选
        private int carbohydrate  = 0;    //optional 可选

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }

    NutritionFacts nutritionFacts = new NutritionFacts.Builder(1, 2).fat(1).build();
}
