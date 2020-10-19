package com.epam.university.java.project.core.cdi.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BeanFactoryImpl implements BeanFactory {
    private BeanDefinitionRegistry registry;
    private BeanSetterHelper setter;
    private HashMap<Class<?>, Object> singletonMap = new HashMap<>();

    public BeanFactoryImpl(BeanDefinitionRegistry registry) {
        this.registry = registry;
        this.setter = new BeanSetterHelper(this);
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        System.out.println();
        BeanDefinitionRegistryImpl reg = (BeanDefinitionRegistryImpl) registry;
        Collection<BeanDefinition> beans = reg.getCollection().getBeans();
        for (BeanDefinition bean : beans) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(bean.getClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Class<?>[] interfaces = clazz.getInterfaces();
            if (clazz.equals(beanClass) || Arrays.asList(interfaces).contains(beanClass)) {
                return (T) getBean(bean.getId());
            }
        }
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);

        Class<?> beanClass = null;
        try {
            beanClass = Class.forName(beanDefinition.getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (singletonMap.containsKey(beanClass)) {
            return singletonMap.get(beanClass);
        }

        Object bean = null;

        try {
            bean = Objects.requireNonNull(beanClass).getConstructor().newInstance();
            Collection<BeanPropertyDefinition> properties = beanDefinition.getProperties();
            if (properties != null && properties.size() > 0) {
                for (BeanPropertyDefinition property : properties) {
                    Field field = beanClass.getDeclaredField(property.getName());
                    if (property.getRef() != null) {
                        setter.setReference(field, bean, property);
                    } else if (property.getData() == null) {
                        setter.setValue(field, bean, property);
                    } else if (property.getData() != null) {
                        setter.setData(field, bean, property);
                    }
                }
            }

            if (beanDefinition.getPostConstruct() != null) {
                beanClass.getMethod(beanDefinition.getPostConstruct()).invoke(bean);
            }

            if (beanDefinition.getScope() != null
                    && beanDefinition.getScope().equals("singleton")) {
                singletonMap.put(beanClass, bean);
            }
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException
                | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return (T) getBean(beanName);
    }
}
