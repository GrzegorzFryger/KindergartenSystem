package pl.edu.pja.prz.receivables.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import pl.edu.pja.prz.commons.event.AppendChildEvent;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.GuardianChildDependency;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;
import pl.edu.pja.prz.receivables.repository.TransactionMappingRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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

        ReflectionTestUtils.setField(service, "titleMappingLength", 6);
    }

    @Test
    public void Should_CreateTransactionMapping() {
        //Given
        ArgumentCaptor<TransactionMapping> argumentCaptor = ArgumentCaptor.forClass(TransactionMapping.class);

        //When
        service.create(UUID.randomUUID(), UUID.randomUUID());

        //Then
        verify(repository, times(1)).save(argumentCaptor.capture());
        assertEquals(14, argumentCaptor.getValue().getTitle().length());
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
    public void Should_ReturnEmptyOptional_When_TransactionDoesNotExist() {
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
        verify(repository, times(2)).findByTitle(anyString());
        verify(repository, times(1)).save(any(TransactionMapping.class));
    }

    @Test
    public void Should_GetAllTransactionMapping_ForGivenGuardian() {
        //Given

        //When
        service.getAllByGuardianId(UUID.randomUUID());

        //Then
        verify(repository, only()).findAllByGuardianId(any(UUID.class));
    }

    @Test
    public void Should_ThrowException_When_TransactionMappingToUpdateDoesNotExist() {
        //Given
        when(repository.findByTitle(anyString())).thenReturn(null);

        //When
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            service.update(transactionMapping);
        });

        //Then
        verify(repository, times(1)).findByTitle(anyString());
        verify(repository, never()).save(any(TransactionMapping.class));
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
    public void Should_ThrowException_When_TransactionMappingToDeleteDoesNotExist() {
        //Given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        //When
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            service.delete(1L);
        });

        //Then
        verify(repository, times(1)).findById(anyLong());
        verify(repository, never()).deleteById(anyLong());
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

    @Test
    public void Should_HandleOnApplicationEvent() {
        //Given
        GuardianChildDependency guardianChildDependency = new GuardianChildDependency();
        guardianChildDependency.setChildFullName(new FullName("John", "Snow"));
        guardianChildDependency.setChildId(UUID.randomUUID());
        guardianChildDependency.setGuardianId(UUID.randomUUID());
        AppendChildEvent appendChildEvent = new AppendChildEvent(new Object(), guardianChildDependency);

        //When
        service.onApplicationEvent(appendChildEvent);

        //Then
        verify(repository, times(1)).save(any(TransactionMapping.class));
    }

}
