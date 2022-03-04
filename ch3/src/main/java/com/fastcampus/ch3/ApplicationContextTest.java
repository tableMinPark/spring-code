//package com.fastcampus.ch3;
//
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.context.*;
//import org.springframework.context.annotation.*;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.support.*;
//import org.springframework.stereotype.*;
//
//import javax.inject.*;
//import java.util.*;
//
//@Component
//@Scope("prototype")
//class Door {}
//@Component class Engine {}
//@Component class TurboEngine extends Engine {}
//@Component class SuperEngine extends Engine {}
//
//@Component
//class Car {
//    @Value("red") String color;
//    @Value("100") int oil;
////    @Autowired
//    Engine engine;
////    @Autowired
//    Door[] doors;
//
//    // 생성자가 1개 일때: @Autowired 가 필요없고, 생성자에 @Autowired 가 생략되어있다.
//    // 생성자가 여러개 일 때 : 어떤 생성자를 써야하는지 모호하기 때문에 @Autowired를 이용해 주입에 이용할 생성자를 명시해줘야한다.
//    // ex) 아래는 생성자가 2개 이기 때문에 주입에 사용할 생성자를 @Autowired를 통해 명시해주었다.
//    @Autowired
//    public Car(@Value("red") String color, @Value("100") int oil, Engine engine, Door[] doors) {
//        this.color = color;
//        this.oil = oil;
//        this.engine = engine;
//        this.doors = doors;
//    }
//
//    // @Autowired는 타입으로 빈을 검색해서 참조변수에 자동주입한다.
//    // 주입대상이 변수일때 : 검색된 빈이 1개 -> 그대로주입 / 검색된 빈이 여러개 -> 참조변수와 이름이 일치하는 것을 주입 (일치하는 것이 없으면 예외발생) = 반드시 하나의 빈이 주입되어야한다. 하지만 @Autowired(required=false)이면 주입할 빈을 못찾아도 예외가 발생하지 않는다. (값은 null)
//    // 주입대상이 배열일때 : 검색된 빈을 모두 배열에 주입 (예외발생 X)
//
//    public Car() {}
//
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//@Component
//@PropertySource("setting.properties")
//class SysInfo{
//    @Value("#{systemProperties['user.timezone']}")
//    String timeZone;
//
//    @Value("#{systemEnvironment['SystemRoot']}")
//    String currDir;
//
//    @Value("${autosaveDir}")
//    String autosaveDir;
//
//    @Value("${autosaveInterval}")
//    int autosaveInterval;
//
//    @Value("${autosave}")
//    boolean autosave;
//
//    @Override
//    public String toString() {
//        return "SysInfo{" +
//                "timeZone='" + timeZone + '\'' +
//                ", currDir='" + currDir + '\'' +
//                ", autosaveDir='" + autosaveDir + '\'' +
//                ", autosaveInterval=" + autosaveInterval +
//                ", autosave=" + autosave +
//                '}';
//    }
//}
//
//public class ApplicationContextTest {
//    public static void main(String[] args) {
//        ApplicationContext ac = new GenericXmlApplicationContext("config1.xml");
////      Car car = ac.getBean("car", Car.class); // 타입을 지정하면 형변환 안해도됨. 아래의 문장과 동일
//        Car car  = (Car) ac.getBean("car"); // 이름으로 빈 검색
//        Car car2 = (Car) ac.getBean(Car.class);   // 타입으로 빈 검색
//        System.out.println("car = " + car);
//
//        System.out.println("ac.getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));
//
//        Map<String, String> map = System.getenv();
////        System.out.println("map = " + map);
//
//        Properties properties = System.getProperties();
////        System.out.println("properties = " + properties);
//    }
//}