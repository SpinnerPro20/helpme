package laba;

/**
 * Created by Артур on 21.02.2017.
 */
public class RateObject {
    private String name;
    private double rate;
    public void getValues() {
        System.out.println(name + "\n" + rate);
    }

    public RateObject(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

}
