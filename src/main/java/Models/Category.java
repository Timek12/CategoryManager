package Models;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }
    public Category() {
        this.products = new ArrayList<>();
    }
    public String name;
    public List<String> products;
}
