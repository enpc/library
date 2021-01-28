package ru.trsvav.library.common.batchColector;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

class BatchInserter<T> {

    private final List<T> entities = new LinkedList<>();

    private final EntityManager entityManager;

    private long count;

    private final int batchSize;

    private final Object sync = new Object();

    public BatchInserter(EntityManager entityManager, int batchSize) {
        this(entityManager, batchSize, 0L);
    }

    public BatchInserter(EntityManager entityManager, int batchSize, Long initCount) {
        this.entityManager = entityManager;
        this.count = initCount;
        this.batchSize = batchSize;
    }

    public void append(T entity) {
        synchronized (sync) {
            entities.add(entity);
            if (entities.size() >= batchSize) {
                persist();
            }
        }
    }

    public void save() {
        synchronized (sync) {
            persist();
        }
    }

    public long getCount() {
        return count;
    }

    public int getBatchSize() {
        return batchSize;
    }

    private void persist() {
        if (entities.isEmpty()) {
            return;
        }

        for (var entity : entities) {
            entityManager.persist(entity);
        }
        entityManager.flush();
        entityManager.clear();

        count += entities.size();

        entities.clear();
    }
}
