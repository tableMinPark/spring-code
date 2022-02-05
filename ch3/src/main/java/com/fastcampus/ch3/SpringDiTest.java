package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

// @Component
class Engine {}              // <bean id="engine" class="com.fastcampus.ch3.Engine"/>
@Component class SuperEngine extends Engine{}
@Component class TurboEngine extends Engine{}
@Component class Door {}
@Component
class Car {
    @Value("red") String color;

    @Value("100") int oil;

//    @Autowired                  // byType
//    @Qualifier("superEngine")   // superEngine, TurboEngine 두개가 후보인데 engine이라는 후보가 없어서 에러가 날때 superEngine을 고르라고 지정해줌
    @Resource(name = "superEngine")
    Engine engine;       // byType - 타입으로 먼저 검색, 여러개면 이름으로 검색 (이름으로 검색해서 일치하는것을 반환) - engine, superEngine, turboEngine

    @Autowired Door[] doors;

    public void setColor(String color) {
        this.color = color;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoors(Door[] doors) {
        this.doors = doors;
    }

    public Car() {}

    public Car(String color, int oil, Engine engine, Door[] doors) {
        this.color = color;
        this.oil = oil;
        this.engine = engine;
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine=" + engine +
                ", doors=" + Arrays.toString(doors) +
                '}';
    }
}

public class SpringDiTest {
    // config.xml에서  id는 config.txt의 key, class는 config.txt의 value
    // config.xml에서 scope="prototype" 은 매번 새로운 객체를 생성함 <-> scope="singleton" 은 매번 새로운 객체를 생성하지 않음 (기본값)

    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config1.xml");

//        Car car = (Car) ac.getBean("car");                  // byName (강제형변환)
        Car car = ac.getBean("car", Car.class);             // byName
//        Car car2 = ac.getBean(Car.class);                          // byType

//        Engine engine = (Engine) ac.getBean("engine");  // byName
//        Engine engine = (Engine) ac.getBean(Engine.class);  // byType - 일치하는것이 여러개 일 때는 에러가 난다. (byType은 일치하는 타입이 하나여야함)
//        Door door = (Door) ac.getBean("door");

//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoors(new Door[]{ac.getBean("door", Door.class), ac.getBean("door", Door.class)});
        System.out.println("car = " + car);
    }
}
