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

        while(true) {
            shoppingListService.displayMenu();
            System.out.print("Enter your choice: ");
            int i, productIndex, categoryIndex;
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch(choice){
                    case 1:
                        System.out.println("Available categories:");
                        i = 1;
                        for (Category category : categoryService.categories) {
                            System.out.println(i + ". " + category.name);
                            i++;
                        }
                        System.out.print("Enter category number: ");
                        categoryIndex = scanner.nextInt();
                        scanner.nextLine();
                        if(categoryIndex < 1 || categoryIndex > i) {
                            System.out.println("Invalid category number.");
                            break;
                        }

                        System.out.println("Available products from " + categoryService.categories.get(categoryIndex - 1).name + " category:");
                        i = 1;

                        for (String product : categoryService.categories.get(categoryIndex - 1).products) {
                            System.out.println(i + ". " + product);
                            i++;
                        }

                        System.out.print("Enter product number: ");
                        productIndex = scanner.nextInt();
                        scanner.nextLine();
                        if(productIndex < 1 || productIndex > i) {
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
                        System.out.println("Available categories:");
                        i = 1;
                        for (Category category : categoryService.categories) {
                            System.out.println(i + ". " + category.name);
                            i++;
                        }
                        System.out.print("Enter category number: ");
                        categoryIndex = scanner.nextInt();
                        scanner.nextLine();
                        if(categoryIndex < 1 || categoryIndex > i) {
                            System.out.println("Invalid category number.");
                            break;
                        }
                        shoppingListService.displayProductsFromCategory(categoryService.categories.get(categoryIndex - 1).name);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid input. Please enter a number from 1 to 8.");
                }
                System.out.println("Press anything to continue...");
                scanner.nextLine();
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
            }
        }
    }
}
