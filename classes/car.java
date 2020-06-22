import java.util.Calendar;

public class car {
    String owner;
    int year;
    String make;
    String model;
    String color;
    double price;
    boolean isNew;
    double miles;

    public car(int year, String model) {
        this.year = year;
        this.model = model;
    }

    public void sell(String NewOwner) {
        owner = NewOwner;
        if (isNew) {
            isNew = false;
        }
    }
    public boolean isOld() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (currentYear - year > 10) {
            return true;
        }
        else {
            return false;
        }
    }


    public static void main(String[] args) {
        car myCar = new car(2001, "C-class");
        myCar.color = "red";
        myCar.make = "mercedes";
        myCar.isNew = true;

        boolean isMyCarOld = myCar.isOld();
        if (isMyCarOld) {
            System.out.println("This car is too old!");
        }
        else {
            System.out.println("Seems new enough.");
        }
        
        myCar.sell("Jim Smith");
        System.out.println("This car now belongs to " + myCar.owner);

        System.out.println("This car is a " + myCar.make +" of model " + myCar.model);

    }
}
