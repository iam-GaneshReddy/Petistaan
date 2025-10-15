package Repository;

import Model.OwnerDTO;

import java.util.List;
import java.util.Optional;

public interface Repository {
    void add(OwnerDTO owner) ;
    Optional<OwnerDTO> getById(int id);
    void delete(OwnerDTO owner);
    List<OwnerDTO> getAllOwners();
    void updatePetDetails(int ownerId, String petName);

}
