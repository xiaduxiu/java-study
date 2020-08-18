package com.xcr.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author xia
 */
public class SpringIOC {

    private HashMap<String, Object> beanMap = new HashMap<>();

    public SpringIOC(String location) throws Exception {
        loadBean(location);
    }

    public Object getBean(String name) {
        Object bean = beanMap.get(name);
        if (bean == null) {
            throw new IllegalArgumentException("there id no bean with name" + name);
        }
        return bean;
    }


    private void loadBean(String location) throws Exception {
        InputStream fileInputStream = new FileInputStream(location);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document parse = documentBuilder.parse(fileInputStream);
        Element documentElement = parse.getDocumentElement();
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Element) {
                Element element = (Element) item;
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");
                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                // 创建bean
                Object bean = beanClass.newInstance();
                NodeList property = element.getElementsByTagName("property");
                for (int j = 0; j < property.getLength(); j++) {
                    Node item1 = property.item(j);
                    if (item1 instanceof Element) {
                        Element propertyElement = (Element) item1;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");
                        Field declaredField = bean.getClass().getDeclaredField(name);
                        declaredField.setAccessible(true);
                        if (value != null && value.length() > 0) {
                            declaredField.set(bean, value);
                        } else {
                            String ref = propertyElement.getAttribute("ref");
                            if (ref == null || ref.length() == 0) {
                                throw new IllegalArgumentException("ref config error");
                            }
                            declaredField.set(bean, getBean(ref));
                        }
                    }
                    registerBean(id, bean);
                }
            }
        }

    }

    private void registerBean(String id, Object bean) {
        beanMap.put(id, bean);
    }

}
