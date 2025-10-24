package TransactionsRepository;

import Model.OwnerDTO;

import java.util.List;

public interface Repository {
    void saveOwnersAutomatically(List<OwnerDTO> owners);

    void saveOwnersManually(List<OwnerDTO> owners);

    void saveOwnersManuallyWithSavepoint(List<OwnerDTO> owners);
}
