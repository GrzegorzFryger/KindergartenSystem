package pl.edu.pja.prz.receivables.model;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
public class TransactionMapping extends BaseEntityLong implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(TransactionMapping.class);

    @Column(unique = true)
    @NotNull
    private String title;
    @Type(type = "uuid-char")
    @Column(length = 36)
    @NotNull
    private UUID childId;
    @Type(type = "uuid-char")
    @Column(length = 36)
    @NotNull
    private UUID guardianId;
    @NotNull
    private String childName;
    @NotNull
    private String childSurname;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getChildId() {
        return childId;
    }

    public void setChildId(UUID childId) {
        this.childId = childId;
    }

    public UUID getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(UUID guardianId) {
        this.guardianId = guardianId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildSurname() {
        return childSurname;
    }

    public void setChildSurname(String childSurname) {
        this.childSurname = childSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionMapping mapping = (TransactionMapping) o;
        return title.equals(mapping.title) &&
                childId.equals(mapping.childId) &&
                guardianId.equals(mapping.guardianId) &&
                childName.equals(mapping.childName) &&
                childSurname.equals(mapping.childSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, childId, guardianId, childName, childSurname);
    }

    @Override
    public String toString() {
        return childId + " - " + title;
    }

    @PostPersist
    public void postPersist() {
        logger.info("Saved transaction mapping: {}", this);
    }
}
