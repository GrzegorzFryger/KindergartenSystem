package pl.edu.pja.prz.account.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.util.Arrays;
import java.util.Optional;


public class GenericService<T extends CrudRepository<E, ID>, E, ID> {
	protected final Logger logger = LoggerFactory.logger(GenericService.class);
	protected final T repository;

	public GenericService(T repository) {
		this.repository = repository;
	}

	public E getByIg(ID id) {
		return repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
	}

	public E update(ID toUpdate, E updated) {
		return repository.findById(toUpdate).map(childToUpdate -> {
					updateNotNullFields(childToUpdate, updated);

					return repository.save(childToUpdate);
				}
		).orElseThrow(() -> new ElementNotFoundException(toUpdate));

	}

	public E save(E toSave) {
		return repository.save(toSave);
	}


	private void updateNotNullFields(E toUpdate, E updated) {
		Arrays.stream(updated.getClass().getDeclaredFields())
				.forEach(field -> {
					field.setAccessible(true);
					try {
						Optional.ofNullable(field.get(updated))
								.ifPresent(value -> {
									try {
										field.set(toUpdate, field.get(updated));

									} catch (IllegalAccessException e) {
										logger.error("Fail set property - {}", field, e);
									}
								});
					} catch (IllegalAccessException e) {
						logger.error("Fail set property - {}", field, e);
					}
				});
	}

}
