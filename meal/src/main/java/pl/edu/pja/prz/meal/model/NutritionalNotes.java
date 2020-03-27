package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class NutritionalNotes extends BaseEntityLong {

    private String nutritionalNotesValue;
    @ManyToOne(fetch = FetchType.LAZY)
    private Meal meal;



}
