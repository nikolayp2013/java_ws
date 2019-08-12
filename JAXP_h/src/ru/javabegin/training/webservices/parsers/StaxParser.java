package ru.javabegin.training.webservices.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class StaxParser {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void parse(String xmlPath) {
        try {
            XMLInputFactory inFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = inFactory.createXMLEventReader(new FileInputStream(xmlPath));
        
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLEventWriter writer = factory.createXMLEventWriter(new FileWriter("c:\\test\\test.xml"));
            
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            boolean cityTag = false;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                System.out.println(event);

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT: {


                        if (event.asStartElement().getName().toString().equalsIgnoreCase("city")) {
                            cityTag = true;
                            continue;
                        }

                        break;
                    }

                    case XMLStreamConstants.CHARACTERS: {
                        if (cityTag) {
                            continue;
                        }
                        break;
                    }

                    case XMLStreamConstants.END_ELEMENT: {

                        if (event.asEndElement().getName().toString().equalsIgnoreCase("city")) {
                            cityTag = false;
                            continue;
                        }
                        
                        if (event.asEndElement().getName().toString().equalsIgnoreCase("person")) {
                            writer.add(eventFactory.createStartElement("", null, "date"));
                            writer.add(eventFactory.createCharacters(dateFormat.format(new Date())));
                            writer.add(eventFactory.createEndElement("", null, "date"));
                        }

                        break;
                    }
                }

                writer.add(event);



            }
            writer.flush();
            writer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(StaxParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(StaxParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StaxParser.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File(xmlPath)));

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if ("person".equals(reader.getLocalName())) {

                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                System.out.println(reader.getAttributeName(i) + "=" + reader.getAttributeValue(i));
                            }

                            System.out.println();


                        }

                        break;

                    case XMLStreamConstants.CHARACTERS:
                        System.out.println(reader.getText().trim());
                        System.out.println();
                        break;
                }

            }


        } catch (XMLStreamException ex) {
            Logger.getLogger(StaxParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StaxParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
