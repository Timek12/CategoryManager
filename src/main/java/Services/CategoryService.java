package Services;

import Models.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryService {
    public List<Category> categories;
    public CategoryService(String filename) {
        this.categories = initializeCategories(filename);
    }
    public static List<Category> initializeCategories(String filename) {
        List<Category> categories = new ArrayList<>();
        Category currentCategory = null;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file, "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if(line.startsWith("[")) {
                    if (currentCategory != null) {
                        categories.add(currentCategory);
                    }

                    currentCategory = new Category();
                    currentCategory.name = line.substring(1, line.length() - 1);
                    currentCategory.products = new ArrayList<>();
                }
                else if(!line.isEmpty() && currentCategory != null) {
                    currentCategory.products.add(line.substring(2));
                }
            }
            if (currentCategory != null) {
                categories.add(currentCategory);
            }
            scanner.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error occured: " + e.getMessage());
        }

        return categories;
    }

    public void displayCategories() {
        if(this.categories.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }
        System.out.println("Available categories:");
        int i = 1;
        for (Category category : this.categories) {
            System.out.println(i + ". " + category.name);
            i++;
        }
    }

    public void displayProductsFromCategory(String categoryName) {
        if(this.categories.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }

        for (Category category : this.categories) {
            if (category.name.equals(categoryName)) {
                System.out.println("Available products from " + categoryName + " category:");
                if(category.products.isEmpty()) {
                    System.out.println("No products in this category.");
                    return;
                }
                int i = 1;
                for (String product : category.products) {
                    System.out.println(i + ". " + product);
                    i++;
                }
                System.out.println();
                return;
            }
        }

        System.out.println("Category not found");
    }

    public int getSize() {
        return this.categories.size();
    }
}
