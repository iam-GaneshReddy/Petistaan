package TransactionService;

import Model.OwnerDTO;
import TransactionsRepository.TransactionRepositoryImpl;

import java.util.List;

public class TransactionServiceImpl implements TransactionService{
    private static TransactionRepositoryImpl transactionRepository;

    public TransactionServiceImpl(){
        transactionRepository=new TransactionRepositoryImpl();
    }
    @Override
    public void saveOwnersAutomatically(List<OwnerDTO> ownerDTOs) {
        transactionRepository.saveOwnersAutomatically(ownerDTOs);
    }

    @Override
    public void saveOwnersManually(List<OwnerDTO> ownerDTOs) {
       transactionRepository.saveOwnersManually(ownerDTOs);
    }

    @Override
    public void saveOwnersManuallyWithSavepoint(List<OwnerDTO> ownerDTOs) {
      transactionRepository.saveOwnersManuallyWithSavepoint(ownerDTOs);
    }
}
