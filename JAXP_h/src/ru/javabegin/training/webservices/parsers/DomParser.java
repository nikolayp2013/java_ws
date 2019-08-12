package ru.javabegin.training.webservices.parsers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class DomParser {

    private Document doc;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void parse(String xmlPath) {

        try {

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = dBuilder.parse(new File(xmlPath));

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            if (doc.hasChildNodes()) {
                print(doc.getChildNodes());
            }


            saveXML("c:\\test\\addDate.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }

    private void print(NodeList nodeList) {

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node tempNode = nodeList.item(i);


            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                System.out.println("Node \"" + tempNode.getNodeName() + "\"");
                System.out.println("Node Value = " + tempNode.getTextContent());

                if (tempNode.getNodeName().equals("person")) {
                    if (tempNode.hasAttributes()) {

                        NamedNodeMap nodeMap = tempNode.getAttributes();

                        for (int c = 0; c < nodeMap.getLength(); c++) {

                            Node node = nodeMap.item(c);
                            System.out.println("attribute \"" + node.getNodeName() + "\"=" + node.getNodeValue());

                        }

                    }

                    addDate(tempNode);
                }

                if (tempNode.getNodeName().equals("city")) {
                    tempNode.getParentNode().removeChild(tempNode);
                }


                if (tempNode.hasChildNodes()) {
                    print(tempNode.getChildNodes());
                }

                System.out.println();

            }

        }

    }

    private void addDate(Node node) {
        Node dateNode = doc.createElement("date");
        dateNode.appendChild(doc.createTextNode(dateFormat.format(new Date())));
        node.appendChild(dateNode);

    }

    private void saveXML(String path) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));

            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DomParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DomParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
