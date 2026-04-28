package xml;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;

public class transformatuXmlHtmln {

    public static void transformatuXmlHtmln(String xml, String xsd, String xslt, String html) {
        try {
            File xmlFitxategia = new File(xml);
            File xsdFitxategia = new File(xsd);
            File xsltFitxategia = new File(xslt);
            File htmlIrteera = new File(html);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdFitxategia);
            Validator validator = schema.newValidator();

            try {
                validator.validate(new StreamSource(xmlFitxategia));
                System.out.println("XML-a baliozkoa da.");

                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer(new StreamSource(xsltFitxategia));

                transformer.transform(new StreamSource(xmlFitxategia), new StreamResult(htmlIrteera));

                System.out.println("HTML fitxategia sortuta: " + htmlIrteera.getAbsolutePath());

            } catch (SAXException e) {
                System.out.println("XML-a EZ da baliozkoa: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String xml = "xml/donazioak.xml";
        String xsd = "xml/donazioak.xsd";
        String xslt = "xml/donazioak.xsl";
        String html = "xml/donazioak.html";

        transformatuXmlHtmln(xml, xsd, xslt, html);
    }
}