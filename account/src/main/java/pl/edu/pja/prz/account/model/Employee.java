package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public abstract class Employee extends Account {

	Employee() {
		super();
	}

	public Employee(Phone phoneNumber, String email, FullName fullName, Address address, Password password, Set<Role> roles) {
		super(phoneNumber, email, fullName, address, password, roles);
	}


}
