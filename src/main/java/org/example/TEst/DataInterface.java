package org.example.TEst;

import org.example.CrudRepository;

public interface DataInterface extends CrudRepository<USER, Integer> {
     USER findUserByName(String name);
}
