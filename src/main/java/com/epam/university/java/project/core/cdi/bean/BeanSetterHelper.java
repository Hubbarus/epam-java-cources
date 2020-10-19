package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BeanSetterHelper {
    private BeanFactoryImpl factory;

    public BeanSetterHelper(BeanFactoryImpl factory) {
        this.factory = factory;
    }

    /**
     * Saves value to bean obj.
     * @param field of value.
     * @param bean target bean.
     * @param property property.
     * @throws IllegalAccessException from field.
     */
    public void setValue(Field field,
                         Object bean,
                         BeanPropertyDefinition property) throws IllegalAccessException {
        Class<?> type = field.getType();
        Object val = null;
        if (type.equals(int.class)) {
            val = Integer.parseInt(property.getValue());
        } else {
            val = property.getValue();
        }

        if (val == null) {
            throw new RuntimeException();
        }

        field.setAccessible(true);
        field.set(bean, val);
    }

    /**
     * Saves reference to bean obj.
     * @param field of value.
     * @param bean target bean.
     * @param property property.
     * @throws IllegalAccessException from field.
     */
    public void setReference(Field field,
                             Object bean,
                             BeanPropertyDefinition property) throws IllegalAccessException {
        Object val = null;
        Class<?> type = field.getType();
        String ref = property.getRef();
        if (ref != null) {
            val = factory.getBean(ref);
            field.setAccessible(true);
            field.set(bean, val);
        }
    }

    /**
     * Saves complex data to bean obj.
     * @param field of value.
     * @param bean target bean.
     * @param property property.
     * @throws IllegalAccessException from field.
     */
    public void setData(Field field,
                        Object bean,
                        BeanPropertyDefinition property) throws IllegalAccessException {
        switch (field.getName()) {
            case "stringCollection" : {
                Collection<String> data = new ArrayList<>();
                ListDefinition definition = (ListDefinition) property.getData();
                Collection<ListDefinition.ListItemDefinition> items = definition.getItems();
                for (ListDefinition.ListItemDefinition item : items) {
                    data.add(item.getValue());
                }

                field.setAccessible(true);
                field.set(bean, data);
            } break;
            case "stringMap" : {
                Map<String, String> map = new HashMap<>();
                MapDefinition definition = (MapDefinition) property.getData();
                Collection<MapDefinition.MapEntryDefinition> values = definition.getValues();
                for (MapDefinition.MapEntryDefinition entry : values) {
                    map.put(entry.getKey(), entry.getValue());
                }

                field.setAccessible(true);
                field.set(bean, map);
            } break;

            case "objectMap" : {
                Map<String, Object> map = new HashMap<>();
                MapDefinition definition = (MapDefinition) property.getData();
                Collection<MapDefinition.MapEntryDefinition> values = definition.getValues();
                for (MapDefinition.MapEntryDefinition entry : values) {
                    map.put(entry.getKey(), factory.getBean(entry.getRef()));
                }

                field.setAccessible(true);
                field.set(bean, map);
            } break;

            default: {
                throw new IllegalArgumentException();
            }
        }
    }
}
