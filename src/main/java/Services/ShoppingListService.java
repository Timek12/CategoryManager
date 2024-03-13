package Services;

import Models.Category;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListService {
    public List<Category> categories = new ArrayList<>();

    public void displayMenu() {
        System.out.println("1. Add product to shopping list.");
        System.out.println("2. Display all products from shopping list.");
        System.out.println("3. Display all products from a specific category.");
        System.out.println("4. Remove all products from shopping list.");
        System.out.println("5. Remove all products from a specific category.");
        System.out.println("6. Remove a product from a specific category.");
        System.out.println("7. Save shopping list to file.");
        System.out.println("8. Exit.");
    }

    public void displayAllProducts() {
        if (this.categories.isEmpty()) {
            System.out.println("No products in shopping list.");
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
        System.out.println("Category not found");
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
}
