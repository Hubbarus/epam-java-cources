package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Collection;

public class BeanDefinitionReaderImpl implements BeanDefinitionReader {
    private BeanDefinitionRegistry registry;

    public BeanDefinitionReaderImpl(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {
        try {
            JAXBContext context = JAXBContext.newInstance(BeansCollection.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = resource.getFile();
            BeansCollection beans = (BeansCollection) unmarshaller.unmarshal(file);

            BeanDefinitionRegistryImpl reg = (BeanDefinitionRegistryImpl) registry;
            reg.setCollection(beans);
            return beans.getBeans().size();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        int total = 0;
        for (Resource resource : resources) {
            total += loadBeanDefinitions(resource);
        }
        return total;
    }
}
