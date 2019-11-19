package pl.edu.pja.prz.account.domain.value;

import lombok.Value;

@Value
public class Address {

	private String postalCode;
	private String city;
	private String streetNumber;

}
