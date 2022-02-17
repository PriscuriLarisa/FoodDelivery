package business;

import java.util.ArrayList;
import java.util.Objects;

public class CompositeProduct extends MenuItem{

    private String title;
    private ArrayList<MenuItem> productList = new ArrayList<>();

    public CompositeProduct(){

    }

    public CompositeProduct(String title, ArrayList<MenuItem> productList) {
        this.title = title;
        this.productList = productList;
    }

    @Override
    public int computePrice() {
        int s=0;
        for(MenuItem menuItem : productList){
            s+=menuItem.computePrice();
        }

        return s;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public double getRating() {
        return 0;
    }

    @Override
    public int getCalories() {
        int cal = 0;
        for(MenuItem tmp : productList)
            cal+=tmp.getCalories();
        return cal;
    }

    @Override
    public int getProtein() {
        int prot = 0;
        for(MenuItem tmp : productList)
            prot+=tmp.getProtein();
        return prot;
    }

    @Override
    public int getFat() {
        int fat = 0;
        for(MenuItem tmp : productList)
            fat+=tmp.getFat();
        return fat;
    }

    @Override
    public int getSodium() {
        int sod = 0;
        for(MenuItem tmp : productList)
            sod+=tmp.getSodium();
        return sod;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public ArrayList<MenuItem> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<MenuItem> productList) {
        this.productList = productList;
    }

    public String toString(){
        String s="";
        s+= "Title: " + title + "\n";
        s+= "Price: " + computePrice() + "\n";
        for(MenuItem mi : productList){
            s+= "   " + mi.getTitle() + "\n";
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeProduct that = (CompositeProduct) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
