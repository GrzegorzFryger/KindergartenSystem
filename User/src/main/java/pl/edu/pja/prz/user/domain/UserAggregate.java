package pl.edu.pja.prz.user.domain;


import pl.edu.pja.prz.user.domain.entity.*;

import java.util.List;
import java.util.UUID;

public class UserAggregate extends BaseAggregateRoot {

	private FullName fullName;
	private Email email;
	private Password password;
	private PhoneNumber phoneNumber;
	private UserRole userRole;
	private List<UUID> kidsList;



}
