package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IncomingPaymentsService {
    List<IncomingPaymentDto> getAllPaymentsForGuardian(UUID guardianId);

    List<IncomingPaymentDto> getAllPaymentsForChild(UUID childId);

    List<IncomingPaymentDto> getAllPaymentsForGuardian(UUID guardianId, LocalDate from, LocalDate to);

    List<IncomingPaymentDto> getAllPaymentsForChild(UUID childId, LocalDate from, LocalDate to);
}
