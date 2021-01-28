package ru.trsvav.library.common.batchColector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class JpaBachInsertCollector<T> implements Collector<T, BatchInserter<T>, Long>, AutoCloseable {

    private final EntityManager entityManager;
    private final EntityTransaction transaction;
    private final int batchSize;

    public JpaBachInsertCollector(EntityManagerFactory managerFactory) {
        this(managerFactory, 100);
    }

    public JpaBachInsertCollector(EntityManagerFactory managerFactory, int batchSize) {
        this.batchSize = batchSize;
        entityManager = managerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @Override
    public Supplier<BatchInserter<T>> supplier() {
        return () -> new BatchInserter<>(entityManager, batchSize);
    }

    @Override
    public BiConsumer<BatchInserter<T>, T> accumulator() {
        return BatchInserter::append;
    }

    @Override
    public BinaryOperator<BatchInserter<T>> combiner() {
        return (a, b) -> {
            a.save();
            b.save();
            return new BatchInserter<>(entityManager, a.getBatchSize(), a.getCount() + b.getCount());
        };
    }

    @Override
    public Function<BatchInserter<T>, Long> finisher() {
        return inserter -> {
            inserter.save();
            transaction.commit();
            return inserter.getCount();
        };

    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.CONCURRENT, Characteristics.UNORDERED);
    }

    @Override
    public void close(){
        if (transaction != null && transaction.isActive()) transaction.rollback();
        if (entityManager != null) entityManager.close();
    }
}
