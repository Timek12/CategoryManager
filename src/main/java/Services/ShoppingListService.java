package Services;

import Models.Category;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListService {
    public List<Category> categories = new ArrayList<>();

    public void displayAllProducts() {
        if (this.categories.isEmpty()) {
            System.out.println("No products in the shopping list.");
            return;
        }
        for (Category category : this.categories) {
            System.out.println("[" + category.name + "]");
            for (String product : category.products) {
                System.out.println(" - " + product);
            }
            System.out.println();
        }
    }

    public void displayProductsFromCategory(String categoryName) {
        for (Category category : this.categories) {
            if (category.name.equals(categoryName)) {
                System.out.println("[" + category.name + "]");
                if (category.products.isEmpty()) {
                    System.out.println("No products in this category.");
                    return;
                }
                for (String product : category.products) {
                    System.out.println(" - " + product);
                }
                System.out.println();
                return;
            }
        }
        System.out.println("Category not found in the shopping list.");
    }

    public void addProductToCategory(String categoryName, String productName) {
        boolean isCategoryFound = false;
        for (Category category : this.categories) {
            if (category.name.equals(categoryName)) {
                category.products.add(productName);
                isCategoryFound = true;
                break;
            }
        }

        if (isCategoryFound) {
            return;
        }

        this.categories.add(new Category(categoryName));

        for (Category category : this.categories) {
            if (category.name.equals(categoryName)) {
                category.products.add(productName);
                return;
            }
        }
    }

    public void removeAllProducts() {
        this.categories.clear();
    }

    public boolean removeAllProductsFromCategory(String categoryName) {
        for (Category category : this.categories) {
            if (category.name.equals(categoryName)) {
                this.categories.remove(category);
                return true;
            }
        }

        return false;
    }

    public boolean removeProductFromCategory(String categoryName, String productName) {
        for (Category category : this.categories) {
            if (category.name.equals(categoryName)) {
                for(String product : category.products){
                    if(product.equals(productName)){
                        category.products.remove(productName);
                        if(category.products.isEmpty()){
                            this.categories.remove(category);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean saveShoppingListToFile() {
        File file = new File("shoppingList.txt");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Category category : this.categories) {
                writer.println("[" + category.name + "]");
                for (String product : category.products) {
                    writer.println(" - " + product);
                }
                writer.println();
            }
            writer.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("Error occured: " + e.getMessage());
            return false;
        }
    }
}
