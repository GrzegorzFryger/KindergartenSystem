package pl.edu.pja.prz.tuition.model;

import java.math.BigDecimal;

public class DisposablePayment extends Payment {
	DisposablePayment() {
	 	super();
	}

	public DisposablePayment(BigDecimal amount, String description) {
		super(amount, description);
	}
}
