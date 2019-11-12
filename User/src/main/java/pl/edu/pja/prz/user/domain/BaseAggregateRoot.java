package pl.edu.pja.prz.user.domain;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
class BaseAggregateRoot {

	private UUID id;
}
