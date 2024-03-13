package Services;

import Models.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryService {
    private List<Category> categories;
    public CategoryService(String filename) {
        this.categories = initializeCategories(filename);
    }
    public List<Category> initializeCategories(String filename) {
        List<Category> categories = new ArrayList<>();
        Category currentCategory = null;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
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
    public void addCategory(String name) {
        // add category to the list
    }

    public void removeCategory(String name) {
        // remove category from the list
    }

    public void addProduct(String categoryName, String productName) {
        // add product to the category
    }

    public void removeProduct(String categoryName, String productName) {
        // remove product from the category
    }
}
