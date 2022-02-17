package business;

public class ReportDate {

    private int day;
    private int month;
    private int year;
    private int startHour;
    private int endHour;
    private int minOrders;
    private int minPrice;
    private int timesOrdered;

    public ReportDate(int startHour,int endHour,int timesOdered,int minOrders,int minPrice,int day, int month, int year) {
        this.startHour = startHour;
        this.endHour=endHour;
        this.timesOrdered =timesOdered;
        this.minOrders=minOrders;
        this.minPrice=minPrice;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }


    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }
    public int getTimesOrdered() {
        return timesOrdered;
    }

    public int getMinOrders() {
        return minOrders;
    }

    public void setMinOrders(int minOrders) {
        this.minOrders = minOrders;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}