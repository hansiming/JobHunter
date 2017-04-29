package com.cszjo.test;

/**
 * Created by Han on 2017/4/1.
 */
////method 2
//public class Elvis {
//
//    //Singleton with private final field
//    private static final Elvis INSTANCE = new Elvis();
//
//    //private constructor
//    private Elvis() {}
//
//    //static factory method
//    public static Elvis getInstance() {
//        return INSTANCE;
//    }
//}
//Noninstantiable utility class
public class UtilityClass {

    // 私有化，且无法被实例化，以及防止反射实例化
    private UtilityClass() {
        throw new AssertionError();
    }
}
