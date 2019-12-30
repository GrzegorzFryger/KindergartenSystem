package pl.edu.pja.prz.receivables.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class TransactionMapping extends BaseEntity<Long> implements Serializable {
    @Column(unique = true)
    private String title;
    private UUID childId;
    private UUID guardianId;

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
}
