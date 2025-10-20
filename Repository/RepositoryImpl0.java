package Repository;

import Model.OwnerDTO;
import Util.InputUtil;
import enums.Gender;
import enums.PetType;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

public class RepositoryImpl0 implements Repository{
    private static List<OwnerDTO> ownerDTOList;

    static {
        ownerDTOList = new ArrayList<>();
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(1);
        ownerDTO.setFirstName("Abhihsek");
        ownerDTO.setLastName("Verma");
        ownerDTO.setGender(Gender.M);
        ownerDTO.setCity("Chandigarh");
        ownerDTO.setState("Chandigarh");
        ownerDTO.setMobileNumber("9876543210");
        ownerDTO.setEmailId("abhishekvermaa10@gmail.com");
        ownerDTO.setPetId(1);
        ownerDTO.setPetName("Mitthu");
        ownerDTO.setPetBirthDate(InputUtil.convertStringToDate("07-03-2022"));
        ownerDTO.setPetGender(Gender.M);
        ownerDTO.setPetType(PetType.BIRD);
        ownerDTOList.add(ownerDTO);
    }

    @Override
    public void add(OwnerDTO ownerDTO) {
        ownerDTOList.add(ownerDTO);
    }


    public Optional<OwnerDTO> getById(int ownerId) {
        return ownerDTOList.stream().filter(owner -> owner.getId() == ownerId).findFirst();
    }


    public void updatePetDetails(int ownerId, String petName) {
        ownerDTOList.stream().filter(owner -> owner.getId() == ownerId).findFirst().ifPresent(owner -> owner.setPetName(petName));
    }

    @Override
    public List<OwnerDTO> updatePetDetails(String petType) {

        return List.of();
    }


    public void delete(int ownerId) {
        ownerDTOList.removeIf(owner -> owner.getId() == ownerId);
    }


    public List<OwnerDTO> getAllOwners() {
        return ownerDTOList;
    }
}
