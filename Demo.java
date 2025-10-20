import Model.OwnerDTO;
import Repository.RepositoryImpl2;
import Service.Service;
import Service.ServiceImpl;
import Util.InputUtil;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Demo{
    private Service ownerService;

    public static void main(String[] args) throws IOException {
        Demo demo = new Demo();
        demo.run();
    }
    public void run() {
        ownerService = new ServiceImpl(new RepositoryImpl2());
        System.out.println("Welcome to Petistaan");
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                int menuOption = InputUtil.acceptMenuOption(scanner);
                switch (menuOption) {
                    case 1:
                        OwnerDTO ownerDTO = InputUtil.acceptOwnerDetailsToSave(scanner);
                        ownerService.add(ownerDTO);
                        System.out.println("Owner has been saved successfully.");
                        break;
                    case 2:
                        int ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
                        ownerDTO = ownerService.getById(ownerId);
                        System.out.println("Owner has been fetched successfully.");
                        System.out.println(ownerDTO);
                        break;
                    case 3:
                       ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
                        String petName = InputUtil.acceptPetDetailsToUpdate(scanner);
                        ownerService.updatePetDetails(ownerId, petName);
                        System.out.println("Pet details of owner have been updated successfully.");
                        break;
                    case 4:
                        ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
                        ownerService.delete(ownerId);
                        System.out.println("Owner has been deleted successfully.");
                        break;
                    case 5:
                        List<OwnerDTO> ownerDTOList = ownerService.getAllOwners();
                        System.out.println("There are " + ownerDTOList.size() + " owners.");
                        ownerDTOList.forEach(System.out::println);
                        break;
                    case 6:
                        String petType=InputUtil.acceptPetTypeToOperate(scanner);
                       ownerDTOList = ownerService.updatePetDetails(petType);
                       System.out.println("There are " + ownerDTOList.size() + " owners.");
                       ownerDTOList.forEach(System.out::println);
                       break;
                    case 7:
                        System.out.println("Thank you see u again");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option entered.");
                }
            } while (InputUtil.wantToContinue(scanner));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}