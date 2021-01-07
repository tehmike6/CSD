package csd.uoc.gr.A33;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
class Car implements Comparable {
    private String name;
    private String manufacturer;
    private String country;
    private int topSpeed;
    private double acceleration;
    private int year;
    public int compareTo(Object carObj){
        Car car = (Car) carObj;
        return car.topSpeed - this.topSpeed;
    }
    @Override
    public String toString() {
        return "Name: "+name+", Manufacturer: "+manufacturer+", Country: "+country+", Top speed: "+topSpeed+", Acceleration: "+acceleration+", Year: "+year;
    }
    public Car() { }
    public Car(String name, String manufacturer, String country, int topSpeed, double acceleration,
               int year) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.country = country;
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.year = year;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getManufacturer() {return manufacturer;}
    public void setManufacturer(String manufacturer) {this.manufacturer = manufacturer;}
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
    public int getTopSpeed() {return topSpeed; }
    public void setTopSpeed(int topSpeed) {this.topSpeed = topSpeed;}
    public double getAcceleration() {return acceleration; }
    public void setAcceleration(double acceleration) {this.acceleration = acceleration;}
    public int getYear() {return year;}
    public void setYear(int year) {this.year = year;}
}
interface CarFactory<P extends Car> {
    P create(String name, String manufacturer, String country, int topSpeed, double acceleration,
             int year);
}
public class a30 {
    public static void main(String[] args) {
        CarFactory<Car> cf = Car::new;
        ArrayList<Car> list = new ArrayList<>();
        list.add(cf.create("918 Spyder", "Porche", "Germany", 340, 2.2, 2014));
        list.add(cf.create("Model S P100D", "Tesla", "USA", 250, 2.28, 2016));
        list.add(cf.create("Chiron", "Bugatti", "France", 420, 2.3, 2017));
        list.add(cf.create("LaFerrari", "Ferrari", "Italy", 349, 2.4, 2015));
        list.add(cf.create("Veyron", "Bugatti", "France", 407, 2.5, 2006));
        list.add(cf.create("991 Turbo S", "Porche", "Germany", 330, 2.5, 2016));
        list.add(cf.create("Huracan", "Lamborghini", "Italy", 341, 2.5, 2015));
        list.add(cf.create("R8 V10 Plus", "Audi", "Germany", 330, 2.6, 2016));

        System.out.println("3.1: Cars made in France");
        list.stream().filter(car -> car.getCountry().equals("France")).forEach(System.out::println);
        System.out.println("\n3.2: Cars made in France or USA");
        list.stream().filter(car -> car.getCountry().equals("France") || car.getCountry().equals("USA")).forEach(System.out::println);
        System.out.println("\n3.3: Cars in France or USA ordered ... naturally");
        list.stream().filter(car -> car.getCountry().equals("France") || car.getCountry().equals("USA")).sorted().forEach(System.out::println);
        System.out.println("\n3.4: Cars made in France or USA ordered in chronological order");
        list.stream().filter(car -> car.getCountry().equals("France") || car.getCountry().equals("USA")).sorted(Comparator.comparingInt(Car::getYear)).forEach(System.out::println);
        System.out.println("\n3.5: Cars made in France or USA ordered in reverse chronological order");
        list.stream().filter(car -> car.getCountry().equals("France") || car.getCountry().equals("USA")).sorted(Comparator.comparingInt(Car::getYear).reversed()).forEach(System.out::println);
        System.out.println("\n3.6: Top-speed Car");
        list.stream().max(Comparator.comparingInt(Car::getTopSpeed)).ifPresent(System.out::println);
        System.out.println("\n3.7: Italian car with best acceleration");
        list.stream().filter(car -> car.getCountry().equals("Italy")).min(Comparator.comparingDouble(Car::getAcceleration)).ifPresent(System.out::println);
        System.out.println("\n3.8: The three cars with the best acceleration");
        list.stream().sorted(Comparator.comparingDouble(Car::getAcceleration)).limit(3).forEach(System.out::println);
        System.out.println("\n3.9: Number of cars by year");
        list.stream().sorted(Comparator.comparingInt(Car::getYear)).map(Car::getYear).distinct().forEach(year->System.out.println(year+": "+list.stream().filter(car -> car.getYear() == year).count()));
        System.out.println("\n3.10: Average top speed of cars by year");
        list.stream().sorted(Comparator.comparingInt(Car::getYear)).map(Car::getYear).distinct().forEach(year->
                System.out.println(year+": "+ list.stream().filter(car ->
                        car.getYear() == year).map( Car::getTopSpeed).reduce(0,(x,y) -> x +=y) / (double)list.stream().filter(car -> car.getYear() == year).count()));
    }
}