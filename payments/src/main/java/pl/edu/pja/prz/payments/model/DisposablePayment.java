package pl.edu.pja.prz.payments.model;

import java.math.BigDecimal;

public class DisposablePayment extends Payment {
	DisposablePayment() {
		super();
	}

	public DisposablePayment(Payment payment) {
		this(payment.getAmount(), payment.getDescription());
	}


	public DisposablePayment(BigDecimal amount, String description) {
		super(amount, description);
	}
}
