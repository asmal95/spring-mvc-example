package edu.mvc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=3, max=30)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(min=10, max=255)
    private String description;

    @OneToMany(mappedBy="theme", cascade = CascadeType.ALL)
    private Set<Quote> quotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(Set<Quote> quotes) {
        this.quotes = quotes;
    }
}
