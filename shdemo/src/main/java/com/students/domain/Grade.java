package com.students.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "grade.all", query = "Select g from Grade g"),
	@NamedQuery(name = "grade.removeAll", query = "Delete from Grade g")
	})


public class Grade {
    private Long id;
    private float value;
    private boolean approved;
    
    
    public Grade() {}

    public Grade(Long id, float value, boolean approved) {
		super();
		this.id = id;
		this.value = value;
		this.approved = approved;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
