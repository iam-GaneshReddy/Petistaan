import TransactionService.TransactionService;
import TransactionService.TransactionServiceImpl;
import Util.InputUtil;
import Util.TransactionInputUtil;

import java.io.IOException;
import java.util.Scanner;

public class TransactionDemo {
    private TransactionService ownerService;

    public static void main(String[] args) throws IOException {
        Demo demo = new Demo();
        demo.run();
    }

    public void run() {
        ownerService = new TransactionServiceImpl();
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println("Welcome to Petistaan");
                int menuOption = InputUtil.acceptMenuOption(scanner);
                switch (menuOption) {
                    case 1:
                        ownerService.saveOwnersAutomatically(TransactionInputUtil.generateOwners());
                        System.out.println("Owners have been saved automatically successfully.");
                        break;
                    case 2:
                        ownerService.saveOwnersManually(TransactionInputUtil.generateOwners());
                        System.out.println("Owners have been saved manually successfully.");
                        break;
                    case 3:
                        ownerService.saveOwnersManuallyWithSavepoint(TransactionInputUtil.generateOwners());
                        System.out.println("Owners have been saved manually with savepoint successfully.");
                        break;
                    default:
                        System.out.println("Invalid option entered.");
                }
            } while (InputUtil.wantToContinue(scanner));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
