package Repository;

import Model.OwnerDTO;
import Util.InputUtil;
import enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryImpl implements Repository{
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
        ownerDTO.setPetType(PetType.Bird);
        ownerDTOList.add(ownerDTO);
    }
    @Override
    public void add(OwnerDTO owner) {
        ownerDTOList.add(owner);
    }

    @Override
    public Optional<OwnerDTO> getById(int id) {
        return ownerDTOList.stream().filter(owner->owner.getId()==id).findFirst();

    }

    @Override
    public void delete(OwnerDTO owner) {
        ownerDTOList.remove(owner);
    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        return ownerDTOList;
    }

    @Override
    public void updatePetDetails(int ownerId, String petName) {
       ownerDTOList.stream().filter(owner->owner.getId()==ownerId).
               findFirst().ifPresent(owner-> {
                   owner.setId(ownerId);
                   owner.setPetName(petName);
               });
    }
}
