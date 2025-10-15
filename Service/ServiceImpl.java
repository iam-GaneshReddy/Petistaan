package Service;

import Model.OwnerDTO;
import Repository.RepositoryImpl;
import Exception.*;
import java.util.List;
import java.util.Optional;

public class ServiceImpl implements Service{
    private  RepositoryImpl repository;

    public ServiceImpl(RepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void add(OwnerDTO owner) throws OwnerAlreadyExistException {
       Optional<OwnerDTO> optionalOwner = repository.getById(owner.getId());
       if(optionalOwner.isPresent())
           throw new OwnerAlreadyExistException("owner already exists enter new details");
       else
           repository.add(owner);
    }

    @Override
    public OwnerDTO getById(int id) throws OwnerNotFoundException {
        Optional<OwnerDTO> optional = repository.getById(id);
        if(optional.isEmpty())
            throw new OwnerNotFoundException("owner not found with this"+id);
        return optional.get();
    }

    @Override
    public void delete(int id) throws OwnerNotFoundException {
        Optional<OwnerDTO> optional=repository.getById(id);
        if(optional.isEmpty())
            throw new OwnerNotFoundException("owner not found with this"+id);
        else
            repository.delete(optional.get());
    }
    @Override
    public List<OwnerDTO> getAllOwners() {
        return repository.getAllOwners();
    }
    @Override
    public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
        Optional<OwnerDTO> optional=repository.getById(ownerId);
        if(optional.isEmpty())
            throw new OwnerNotFoundException("owner not found with this"+ownerId);
        else
            repository.updatePetDetails(ownerId, petName);

    }
}
