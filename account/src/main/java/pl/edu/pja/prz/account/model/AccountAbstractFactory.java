package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

abstract class AccountAbstractFactory implements AccountFactory {

	protected abstract Employee createTeacher(Address address, FullName fullName, Phone phone,
	                                          Password password, String email);

	protected abstract Guardian createGuardian(Address address, FullName fullName, Phone phone,
	                                           Password password, String email);

	public AccountAbstractFactory() {
	}

	@Override
	public <T extends  Account> T createStandardAccount(Address address, FullName fullName,
	                                                    Phone phone, Password password, String email, Class<T> tClass) {
		if(tClass.getTypeName().equals(Employee.class.getName())) {
			return tClass.cast(createTeacher(address, fullName, phone, password, email));
		}
		else if(tClass.getTypeName().equals(Guardian.class.getName())) {
			return tClass.cast(createGuardian(address, fullName, phone, password, email));
		}
		else throw new IllegalArgumentException("Not Found " + tClass.getName() + " type ");
	}

 }
