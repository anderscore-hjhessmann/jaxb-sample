package de.anderscore.samples.jaxbsample.check;

import de.anderscore.samples.jaxbsample.model.PurchaseOrderType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Configuration
public class OxmMarshalUnmarshal {

    @Bean
    public Jaxb2Marshaller getJaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(PurchaseOrderType.class.getPackage().getName());
        marshaller.setMarshallerProperties(Collections.singletonMap(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE));
        return marshaller;
    }

    public static void main(String[] args) throws JAXBException, IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(OxmMarshalUnmarshal.class);

        Unmarshaller unmarshaller = ctx.getBean(Unmarshaller.class);
        Object po = unmarshaller.unmarshal(new StreamSource(new File("./testdata/po.xml")));
        System.out.println("po = " + po);

        Marshaller marshaller = ctx.getBean(Marshaller.class);
        marshaller.marshal(po, new StreamResult(System.out));
    }
}
