package pl.edu.pja.prz.account.domain.entity;

import pl.edu.pja.prz.account.domain.value.Address;
import pl.edu.pja.prz.account.domain.value.Phone;

public class Borough extends BaseEntity<Long> {

	private String name;
	private Address address;
	private Phone phone;
	private String email;
	private String nipNumber;


}
