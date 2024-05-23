package org.example.TEst;

import org.example.CrudRepository;

public interface testInterface extends CrudRepository<Client,Integer> {
    Client findByName();
}
