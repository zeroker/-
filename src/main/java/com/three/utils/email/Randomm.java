package com.three.utils.email;

import java.util.Random;

public class Randomm {

    public static void main(String [] args){
        System.out.println(getRandom());
    }
    public static int getRandom(){

        Random random = new Random();
        int temp = random.nextInt(10000);
        return temp;
    }


}
