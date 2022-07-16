package com.wyg.wechat.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.apache.http.Consts;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.commons.codec.binary.StringUtils.newStringUtf8;

public class XStreamUtils {
    //将xml转化为对象
    public static <T> T fromXml(String xml, Class<T> clazz) {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.processAnnotations(clazz);
        xStream.setClassLoader(clazz.getClassLoader());
        Object object = xStream.fromXML(xml);
        return clazz.cast(object);
    }

    //将对象转化为 XML
    public static String toXML(Object object) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        toXML(object, os);
        return newStringUtf8(os.toByteArray());
    }

    public static <T> void toXML(T t, OutputStream os) {
        Class<T> clazz = (Class<T>) t.getClass();
        JAXBContext jaxbContext = getJaxbContext(clazz);
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, Consts.UTF_8.name());
            XmlRootElement rootElement = clazz
                    .getAnnotation(XmlRootElement.class);
            if (rootElement == null
                    || rootElement.name().equals(
                    XmlRootElement.class.getMethod("name")
                            .getDefaultValue().toString())) {
                marshaller.marshal(new JAXBElement<T>(new QName(
                        ROOT_ELEMENT_XML), clazz, t), os);
            } else {
                marshaller.marshal(t, os);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not marshal class [" + clazz
                    + "] ", ex);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
    }


    private final static ConcurrentHashMap<Class<?>, JAXBContext> jaxbContexts = new ConcurrentHashMap<Class<?>, JAXBContext>();
    private final static String ROOT_ELEMENT_XML = "xml";
    private static JAXBContext getJaxbContext(Class<?> clazz) {
        JAXBContext jaxbContext = jaxbContexts.get(clazz);
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(clazz);
                jaxbContexts.putIfAbsent(clazz, jaxbContext);
            } catch (JAXBException ex) {
                throw new RuntimeException(
                        "Could not instantiate JAXBContext for class [" + clazz
                                + "] ", ex);
            }
        }
        return jaxbContext;
    }
}
