package com.epam.university.java.core.task041;

import java.io.ObjectStreamException;
import java.util.Collection;

public class Task041Impl implements Task041 {
    @Override
    public Entity create(Collection<Entity> collection, String value) {
        validate(collection, value);
        EntityImpl entity = new EntityImpl();
        entity.setId(collection.size());
        entity.setValue(value);
        collection.add(entity);
        return entity;
    }

    @Override
    public Entity read(Collection<Entity> collection, Entity entity) {
        validate(collection, entity);
        for (Entity ent : collection) {
            if (ent.equals(entity)) {
                return ent;
            }
        }
        return null;
    }

    @Override
    public void update(Collection<Entity> collection, Entity entity, String value) {
        validate(collection, entity);
        if (!collection.contains(entity)) {
            throw new IllegalArgumentException();
        }

        validate(collection, value);
        for (Entity ent : collection) {
            if (ent.getId() == entity.getId()) {
                EntityImpl e = (EntityImpl) ent;
                e.setValue(value);
                e.setId(ent.getId());
                collection.remove(ent);
                collection.add(e);
                return;
            }
        }
    }

    @Override
    public void delete(Collection<Entity> collection, Entity entity) {
        validate(collection, entity);
        collection.remove(entity);
    }

    private void validate(Object o1, Object o2) {
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException();
        }
    }
}
