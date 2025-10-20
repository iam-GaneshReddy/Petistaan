package Service;

import Config.PropertiesConfig;
import Model.OwnerDTO;
import Repository.RepositoryImpl;
import Exception.*;
import Repository.RepositoryImpl2;

import java.util.List;
import java.util.Optional;

public class ServiceImpl implements Service{
    private static  RepositoryImpl2 repository;
    private static final String OWNER_ALREADY_EXISTS = "owner.already.exists";
    private static final String OWNER_NOT_FOUND = "owner.not.found";
    private static final PropertiesConfig PROPERTIES_CONFIG = PropertiesConfig.getInstance();

    public ServiceImpl(RepositoryImpl2 repository) {
        this.repository = repository;
    }

    @Override
    public void add(OwnerDTO owner) throws OwnerAlreadyExistException {
       Optional<OwnerDTO> optionalOwner = repository.getById(owner.getId());
       if(optionalOwner.isPresent())
           throw new OwnerAlreadyExistException(PROPERTIES_CONFIG.getProperty(OWNER_ALREADY_EXISTS));
       else
           repository.add(owner);
    }

    @Override
    public OwnerDTO getById(int id) throws OwnerNotFoundException {
        Optional<OwnerDTO> optional = repository.getById(id);
        if(optional.isEmpty())
            throw new OwnerNotFoundException(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND));
        return optional.get();
    }

    @Override
    public void delete(int id) throws OwnerNotFoundException {
        Optional<OwnerDTO> optional=repository.getById(id);
        if(optional.isEmpty())
            throw new OwnerNotFoundException(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND));
        else
            repository.delete(optional.get().getId());
    }
    @Override
    public List<OwnerDTO> getAllOwners() {
        return repository.getAllOwners();
    }
    @Override
    public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
        Optional<OwnerDTO> optional=repository.getById(ownerId);
        if(optional.isEmpty())
            throw new OwnerNotFoundException(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND));
        else
            repository.updatePetDetails(ownerId, petName);

    }

    @Override
    public List<OwnerDTO> updatePetDetails(String petType) {
        return repository.updatePetDetails(petType);
    }
}
