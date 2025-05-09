package org.tec.carpooling.da.repositories;

import jakarta.persistence.EntityNotFoundException;
import org.tec.carpooling.common.utils.PersistenceManager;
import org.tec.carpooling.da.entities.Identifiable; // Import the interface

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * A generic base repository for common CRUD operations.
 *
 * @param <T>  The entity type, which must implement Identifiable.
 * @param <ID> The type of the entity's identifier.
 */
public abstract class BaseRepository<T extends Identifiable<ID>, ID> {

    private final Class<T> entityClass;
    private final String entityName;

    /**
     * Constructor for the BaseRepository.
     *
     * @param entityClass The class of the entity this repository manages.
     */
    public BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityName = entityClass.getSimpleName();
    }

    /**
     * Saves or updates an entity.
     * If the entity's ID is null, it's persisted. Otherwise, it's merged.
     *
     * @param entity The entity to save or update.
     * @return The managed entity.
     */
    public T save(T entity) {
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            if (entity.getId() == null) { // Now type-safe due to T extends Identifiable<ID>
                em.persist(entity);
            } else {
                entity = em.merge(entity);
            }
            tx.commit();
            return entity;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            // Consider logging the exception here
            throw new RuntimeException("Error saving " + entityName.toLowerCase() + ": " + entity, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Finds an entity by its ID.
     *
     * @param id The ID of the entity.
     * @return An Optional containing the entity if found, or an empty Optional.
     */
    public Optional<T> findById(ID id) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            T entity = em.find(entityClass, id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            // Consider logging the exception here
            throw new RuntimeException("Error finding " + entityName.toLowerCase() + " by ID: " + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Retrieves all entities of type T.
     *
     * @return A list of all entities.
     */
    public List<T> findAll() {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            TypedQuery<T> query = em.createQuery("SELECT e FROM " + entityName + " e", entityClass);
            return query.getResultList();
        } catch (Exception e) {
            // Consider logging the exception here
            throw new RuntimeException("Error finding all " + entityName.toLowerCase() + "s", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Deletes a given entity.
     *
     * @param entity The entity to delete.
     */
    public void delete(T entity) {
        if (entity == null) {
            // Or throw IllegalArgumentException("Entity to delete cannot be null");
            return;
        }
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            // Ensure the entity is managed before removal. If it's detached, merge it first.
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            // Consider logging the exception here
            throw new RuntimeException("Error deleting " + entityName.toLowerCase() + ": " + entity, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id The ID of the entity to delete.
     */
    public void deleteById(ID id) {
        if (id == null) {
            // Or throw IllegalArgumentException("ID for deletion cannot be null");
            return;
        }
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            else {
                throw new EntityNotFoundException("No " + entityName + " found with ID " + id);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error deleting " + entityName.toLowerCase() + " by ID: " + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Provides access to the EntityManager for subclasses that need to implement
     * custom queries. The caller is responsible for closing the EntityManager
     * if it's obtained through this method directly.
     *
     * @return EntityManager instance.
     */
    protected EntityManager getEntityManager() {
        return PersistenceManager.getEntityManager();
    }
}