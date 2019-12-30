package pl.edu.pja.prz.receivables.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;
import pl.edu.pja.prz.receivables.repository.TransactionMappingRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionMappingServiceImplTest {

    @Mock
    private TransactionMappingRepository repository;

    private TransactionMappingServiceImpl service;

    private TransactionMapping transactionMapping;

    @BeforeEach
    public void setUp() {
        service = new TransactionMappingServiceImpl(repository);

        transactionMapping = new TransactionMapping();
        transactionMapping.setTitle("XYZ-123456");
        transactionMapping.setChildId(UUID.randomUUID());
        transactionMapping.setGuardianId(UUID.randomUUID());
    }

    @Test
    public void Should_CreateTransactionMapping() {
        //Given

        //When
        service.create(transactionMapping);

        //Then
        verify(repository, times(1)).save(any(TransactionMapping.class));
    }

    @Test
    public void Should_GetTransactionMappingByTitle() {
        //Given
        when(repository.findByTitle(anyString())).thenReturn(transactionMapping);

        //When
        Optional<TransactionMapping> mapping = service.getByTitle("SOME TITLE");

        //Then
        verify(repository, times(1)).findByTitle(anyString());
        assertTrue(mapping.isPresent());
    }

    @Test
    public void Should_RetrurnEmptyOptional_When_TransactionDoesNotExist() {
        //Given
        when(repository.findByTitle(anyString())).thenReturn(null);

        //When
        Optional<TransactionMapping> mapping = service.getByTitle("SOME TITLE");

        //Then
        verify(repository, times(1)).findByTitle(anyString());
        assertTrue(mapping.isEmpty());

    }

    @Test
    public void Should_UpdateTransactionMapping() {
        //Given
        when(repository.findByTitle(anyString())).thenReturn(transactionMapping);

        //When
        service.update(transactionMapping);

        //Then
        verify(repository, times(1)).findByTitle(anyString());
        verify(repository, times(1)).save(any(TransactionMapping.class));
    }

    @Test
    public void Should_Delete_TransactionMapping() {
        //Given
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(transactionMapping));

        //When
        service.delete(1L);

        //Then
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    public void Should_Map_Transaction() {
        //Given
        Transaction transaction = new Transaction();
        transaction.setTitle("SOME TITLE");
        when(repository.findByTitle("SOME TITLE")).thenReturn(transactionMapping);

        //When
        Transaction result = service.mapTransaction(transaction);

        //Then
        verify(repository, times(1)).findByTitle(anyString());
        assertNotNull(result.getChildId());
        assertNotNull(result.getGuardianId());
    }

}