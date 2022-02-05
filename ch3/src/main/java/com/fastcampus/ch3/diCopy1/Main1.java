package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car{}
class SportsCar extends Car{}
class Truck extends Car {}
class Engine {}

public class Main1 {
    public static void main(String[] args) throws Exception {
        Car car = (Car) getObject("car");
        Engine engine = (Engine) getObject("engine");
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }

    static Object getObject(String key) throws Exception{
        Properties p = new Properties();                    // key - value 형태로 저장되어있는 파일을 읽어오기 편함.
        p.load(new FileReader("config.txt"));       // config.txt (key=value 형태)

        Class clazz = Class.forName(p.getProperty(key));    // getProperty(key) : value

        return clazz.newInstance();
    }
}