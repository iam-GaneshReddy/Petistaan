package Service;

import Model.OwnerDTO;
import Exception.*;
import java.util.List;
import java.util.Optional;

public interface Service {
    void add(OwnerDTO owner) throws OwnerAlreadyExistException;
    OwnerDTO getById(int id)throws OwnerNotFoundException;
    void delete(int id)throws OwnerNotFoundException;
    List<OwnerDTO> getAllOwners();
    void updatePetDetails(int ownerId, String petName)throws OwnerNotFoundException;
    List<OwnerDTO> updatePetDetails(String petType);
}
