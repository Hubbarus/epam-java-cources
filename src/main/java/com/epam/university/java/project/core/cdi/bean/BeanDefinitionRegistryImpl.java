package com.epam.university.java.project.core.cdi.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {
    private BeansCollection collection;

    public BeanDefinitionRegistryImpl() {
        collection = new BeansCollection();
    }

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        Collection<BeanDefinition> beans = collection.getBeans();
        beans.add(definition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        List<BeanDefinition> beans = new ArrayList<>(collection.getBeans());
        for (BeanDefinition bean : beans) {
            if (bean.getId().equals(beanId)) {
                return bean;
            }
        }
        return null;
    }

    public BeansCollection getCollection() {
        return collection;
    }

    public void setCollection(BeansCollection collection) {
        this.collection = collection;
    }
}
