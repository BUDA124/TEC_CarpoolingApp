package org.tec.carpooling.da.entities; // Or your preferred common package

/**
 * Interface for entities that have an identifier.
 *
 * @param <ID> The type of the identifier.
 */
public interface Identifiable<ID> {
    ID getId();
    // void setId(ID id); // Optional: include if you want to enforce a setter too
}