import Models.Category;
import Services.CategoryService;
import Services.ShoppingListService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        CategoryService categoryService = new CategoryService("src/main/java/products.txt");
        ShoppingListService shoppingListService = new ShoppingListService();
        shoppingListService.categories = categoryService.initializeCategories("shoppingList.txt");

        Scanner scanner = new Scanner(System.in, "UTF-8");

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int productIndex, categoryIndex;
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        categoryService.displayCategories();
                        System.out.print("Enter category number: ");
                        categoryIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (categoryIndex < 1 || categoryIndex > categoryService.getSize()) {
                            System.out.println("Invalid category number.");
                            break;
                        }

                        categoryService.displayProductsFromCategory(categoryService.categories.get(categoryIndex - 1).name);

                        System.out.print("Enter product number: ");
                        productIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (productIndex < 1 || productIndex > categoryService.categories.get(categoryIndex - 1).products.size()) {
                            System.out.println("Invalid product number.");
                            break;
                        }

                        shoppingListService.addProductToCategory(categoryService.categories.get(categoryIndex - 1).name, categoryService.categories.get(categoryIndex - 1).products.get(productIndex - 1));
                        System.out.println("Product has been added successfully.");
                        break;
                    case 2:
                        shoppingListService.displayAllProducts();
                        break;
                    case 3:
                        categoryService.displayCategories();
                        System.out.print("Enter category number: ");
                        categoryIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (categoryIndex < 1 || categoryIndex > categoryService.getSize()) {
                            System.out.println("Invalid category number.");
                            break;
                        }
                        shoppingListService.displayProductsFromCategory(categoryService.categories.get(categoryIndex - 1).name);
                        break;
                    case 4:
                        shoppingListService.removeAllProducts();
                        System.out.println("All products have been removed successfully.");
                        break;
                    case 5:
                        categoryService.displayCategories();
                        System.out.print("Enter category number: ");
                        categoryIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (categoryIndex < 1 || categoryIndex > categoryService.getSize()) {
                            System.out.println("Invalid category number.");
                            break;
                        }
                        boolean isCategoryRemoved = shoppingListService.removeAllProductsFromCategory(categoryService.categories.get(categoryIndex - 1).name);
                        if(isCategoryRemoved){
                            System.out.println("All products from " + categoryService.categories.get(categoryIndex - 1).name + " category have been removed successfully.");
                        }
                        else{
                            System.out.println("Category not found in the shopping list.");
                        }
                        break;
                    case 6:
                        categoryService.displayCategories();
                        System.out.print("Enter category number: ");
                        categoryIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (categoryIndex < 1 || categoryIndex > categoryService.getSize()) {
                            System.out.println("Invalid category number.");
                            break;
                        }

                        categoryService.displayProductsFromCategory(categoryService.categories.get(categoryIndex - 1).name);

                        System.out.print("Enter product number you want to remove: ");
                        productIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (productIndex < 1 || productIndex > categoryService.categories.get(categoryIndex - 1).products.size()) {
                            System.out.println("Invalid product number.");
                            break;
                        }

                        boolean isProductRemoved = shoppingListService.removeProductFromCategory(categoryService.categories.get(categoryIndex - 1).name, categoryService.categories.get(categoryIndex - 1).products.get(productIndex - 1));
                        if(isProductRemoved){
                            System.out.println("Product has been removed successfully.");
                        }
                        else{
                            System.out.println("Product not found in the shopping list.");
                        }
                        break;
                    case 7:
                        boolean isFileSaved = shoppingListService.saveShoppingListToFile();
                        if(isFileSaved) {
                            System.out.println("Shopping list has been saved successfully.");
                        }
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid input. Please enter a number from 1 to 8.");
                }
                System.out.println("Press anything to continue...");
                String check = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("1. Add product to shopping list.");
        System.out.println("2. Display all products from shopping list.");
        System.out.println("3. Display all products from a specific category.");
        System.out.println("4. Remove all products from shopping list.");
        System.out.println("5. Remove all products from a specific category.");
        System.out.println("6. Remove a product from a specific category.");
        System.out.println("7. Save shopping list to file.");
        System.out.println("8. Exit.");
    }
}
