package edu.mvc.repository;

import edu.mvc.entity.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long> {

}
