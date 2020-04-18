package pl.edu.pja.prz.account.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.BaseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class GenericService<T extends JpaRepository<E, ID>, E extends BaseEntity<ID>, ID> {
    protected final Logger logger = LoggerFactory.logger(GenericService.class);
    protected final T repository;

    public GenericService(T repository) {
        this.repository = repository;
    }

    public E getById(ID id) {
        return repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public E update(E updated) {
        return repository.findById(updated.getId()).map(childToUpdate -> {
                    updateNotNullFields(childToUpdate, updated);

                    return repository.save(childToUpdate);
                }
        ).orElseThrow(() -> new ElementNotFoundException(updated.getId()));

    }

    public E save(E toSave) {
        return repository.save(toSave);
    }

    private void updateNotNullFields(E toUpdate, E updated) {
        Arrays.stream(updated.getClass()
                .getDeclaredFields())
                .forEach(field -> {
                    field.setAccessible(true);

                    try {
                        Optional.ofNullable(field.get(updated)).ifPresent(value -> {
                            try {
                                field.set(toUpdate, field.get(updated));

                            } catch (IllegalAccessException e) {
                                logger.error("Fail set property - {}", field, e);
                                throw new BusinessException("Fail set property" + field.toString());
                            }
                        });
                    } catch (IllegalAccessException e) {
                        logger.error("Fail set property - {}", field, e);
                        throw new BusinessException("Fail set property" + field.toString());
                    }
                });
    }

    public Long count() {
        return this.repository.count();
    }

}
