import Models.Category;
import Services.CategoryService;
import Services.ShoppingListService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        CategoryService categoryService = new CategoryService("src/main/java/products.txt");
        ShoppingListService shoppingListService = new ShoppingListService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            shoppingListService.displayMenu();
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
                        shoppingListService.removeAllProductsFromCategory(categoryService.categories.get(categoryIndex - 1).name);
                        System.out.println("All products from " + categoryService.categories.get(categoryIndex - 1).name + " category have been removed successfully.");
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

                        shoppingListService.removeProductFromCategory(categoryService.categories.get(categoryIndex - 1).name, categoryService.categories.get(categoryIndex - 1).products.get(productIndex - 1));
                        System.out.println("Product has been removed successfully.");
                        break;
                    case 7:
                        shoppingListService.saveShoppingListToFile();
                        System.out.println("Shopping list has been saved successfully.");
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid input. Please enter a number from 1 to 8.");
                }
                System.out.println("Press anything to continue...");
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
            }
        }
    }
}
