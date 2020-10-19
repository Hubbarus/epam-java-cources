package com.epam.university.java.project.core.cdi.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name = "beans")
@XmlAccessorType(XmlAccessType.FIELD)
public class BeansCollection {
    @XmlElement(name = "bean", type = BeanDefinitionImpl.class)
    private Collection<BeanDefinition> beans = new ArrayList<>();

    public Collection<BeanDefinition> getBeans() {
        return beans;
    }

    public void setBeans(Collection<BeanDefinition> beans) {
        this.beans = beans;
    }
}
