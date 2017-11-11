package de.anderscore.samples.jaxbsample.check;

import de.anderscore.samples.jaxbsample.model.PurchaseOrderType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class BasicMarshalUnmarshal {

    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PurchaseOrderType.class.getPackage().getName());

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object po = unmarshaller.unmarshal(new File("./testdata/po.xml"));
        System.out.println("po: " + po);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(po, System.out);
    }
}
