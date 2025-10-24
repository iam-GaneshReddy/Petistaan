package TransactionService;
import Model.OwnerDTO;

import java.util.List;

public interface TransactionService {
    void saveOwnersAutomatically(List<OwnerDTO> ownerDTOs);

    void saveOwnersManually(List<OwnerDTO> ownerDTOs);

    void saveOwnersManuallyWithSavepoint(List<OwnerDTO> ownerDTOs);
}
