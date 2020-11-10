package com.epam.university.java.project.core.state.machine.domain;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class HandlerXmlAdapter extends XmlAdapter<String, Class<?>> {

    @Override
    public Class<?> unmarshal(String v) throws Exception {
        return Class.forName(v);
    }

    
    @Override
    public String marshal(Class<?> v) throws Exception {
        return v.getName();
    }
}
