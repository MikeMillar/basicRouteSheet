package com.mikemillar.basicroutesheet.datamodels;

import com.mikemillar.basicroutesheet.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;


public class RepairOrderData {
    
    private static RepairOrderData instance = new RepairOrderData();
    
    private static final String ACTIVE_RO_FILE = "activeList.xml";
    private static final String INACTIVE_RO_FILE = "inactiveList.xml";
    private static final String INACTIVE_SOP_FILE = "sopInactiveList.xml";
    
    private static final String REPAIR_ORDER = "repair_order";
    private static final String RO_NUMBER = "ro_number";
    private static final String TAG_NUMBER = "tag_number";
    private static final String YEAR = "year";
    private static final String MAKE = "make";
    private static final String MODEL = "model";
    private static final String MILEAGE = "mileage";
    private static final String CUSTOMER_NAME = "customer_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String STATUS = "status";
    private static final String JOBS = "jobs";
    private static final String ADVISER = "adviser";
    private static final String TECH = "tech";
    private static final String TIME_CREATED = "time_created";
    private static final String TIME_CLOSED = "time_closed";
    private static final String TIME_DUE = "repair_due";
    private static final String NOTES = "notes";
    private static final String IS_WAITING = "is_waiting";
    
    
    public static ObservableList<RepairOrder> roList;
    public static ObservableList<RepairOrder> inactiveList;
    public static ObservableList<RepairOrder> sopInactiveList;
    
    private RepairOrderData() {
        roList = FXCollections.observableArrayList();
        inactiveList = FXCollections.observableArrayList();
        sopInactiveList = FXCollections.observableArrayList();
    }
    
    public static RepairOrderData getInstance() {
        return instance;
    }
    
    public void addToList(RepairOrder ro, ObservableList<RepairOrder> list) {
        try {
            list.add(ro);
        } catch (NullPointerException e) {
            System.out.println("Unable to add RO to list. Incorrect RO / List");
            e.printStackTrace();
        }
    }
    
    public void removeFromList(RepairOrder ro, ObservableList<RepairOrder> list) {
        try {
            list.remove(ro);
        } catch (NullPointerException e) {
            System.out.println("Unable to remove RO from list. Incorrect RO / List");
            e.printStackTrace();
        }
    }
    
    public void replaceFromList(RepairOrder ro, ObservableList<RepairOrder> list) {
        try {
            int index = list.indexOf(ro);
            list.set(index, ro);
        } catch (NullPointerException e) {
            System.out.println("Unable to replace RO from list. Incorrect RO / List");
            e.printStackTrace();
        }
    }
    
    public static ObservableList<RepairOrder> getRoList() {
        return roList;
    }
    
    public static ObservableList<RepairOrder> getInactiveList() {
        return inactiveList;
    }
    
    public static ObservableList<RepairOrder> getSopInactiveList() {
        return sopInactiveList;
    }
    
    public void updateList() {
        for (RepairOrder ro: roList) {
            ro.setElapsedTime();
        }
    }
    
    public void loadLists() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(ACTIVE_RO_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            RepairOrder ro = null;
            
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a repair order, we create a new repair order
                    if (startElement.getName().getLocalPart().equals(REPAIR_ORDER)) {
                        ro = new RepairOrder();
                        continue;
                    }
                    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(RO_NUMBER)) {
                            event = eventReader.nextEvent();
                            ro.setRepairOrderNumber(Integer.parseInt(event.asCharacters().getData()));
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TAG_NUMBER)) {
                            event = eventReader.nextEvent();
                            ro.setTagNumber(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(YEAR)) {
                            event = eventReader.nextEvent();
                            ro.setYear(Integer.parseInt(event.asCharacters().getData()));
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(MAKE)) {
                            event = eventReader.nextEvent();
                            ro.setMake(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(MODEL)) {
                            event = eventReader.nextEvent();
                            ro.setModel(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(MILEAGE)) {
                            event = eventReader.nextEvent();
                            ro.setMileage(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(CUSTOMER_NAME)) {
                            event = eventReader.nextEvent();
                            ro.setCustomerName(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(PHONE_NUMBER)) {
                            event = eventReader.nextEvent();
                            ro.setPhoneNumber(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(STATUS)) {
                            event = eventReader.nextEvent();
                            ro.setStatus(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(JOBS)) {
                            event = eventReader.nextEvent();
                            ro.setJobs(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(ADVISER)) {
                            event = eventReader.nextEvent();
                            ro.setAdviser(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TECH)) {
                            event = eventReader.nextEvent();
                            ro.setTech(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TIME_CREATED)) {
                            event = eventReader.nextEvent();
                            ro.setTimeCreated(event.asCharacters().getData());
                            continue;
                        }
                    }
    
//                    if (event.isStartElement()) {
//                        if (event.asStartElement().getName().getLocalPart().equals(TIME_CLOSED)) {
//                            event = eventReader.nextEvent();
//                            ro.setTimeClosed(event.asCharacters().getData());
//                            continue;
//                        }
//                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TIME_DUE)) {
                            event = eventReader.nextEvent();
                            ro.setTimeDue(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(NOTES)) {
                            event = eventReader.nextEvent();
                            ro.setNotes(event.asCharacters().getData());
                            continue;
                        }
                    }
    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(IS_WAITING)) {
                            event = eventReader.nextEvent();
                            ro.setWaiting(event.asCharacters().getData());
                            continue;
                        }
                    }
                }
                // If we reach end of a repair order element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(REPAIR_ORDER)) {
                        roList.add(ro);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(INACTIVE_RO_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            RepairOrder ro = null;
        
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
            
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a repair order, we create a new repair order
                    if (startElement.getName().getLocalPart().equals(REPAIR_ORDER)) {
                        ro = new RepairOrder();
                        continue;
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(RO_NUMBER)) {
                            event = eventReader.nextEvent();
                            ro.setRepairOrderNumber(Integer.parseInt(event.asCharacters().getData()));
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TAG_NUMBER)) {
                            event = eventReader.nextEvent();
                            ro.setTagNumber(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(YEAR)) {
                            event = eventReader.nextEvent();
                            ro.setYear(Integer.parseInt(event.asCharacters().getData()));
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(MAKE)) {
                            event = eventReader.nextEvent();
                            ro.setMake(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(MODEL)) {
                            event = eventReader.nextEvent();
                            ro.setModel(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(MILEAGE)) {
                            event = eventReader.nextEvent();
                            ro.setMileage(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(CUSTOMER_NAME)) {
                            event = eventReader.nextEvent();
                            ro.setCustomerName(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(PHONE_NUMBER)) {
                            event = eventReader.nextEvent();
                            ro.setPhoneNumber(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(STATUS)) {
                            event = eventReader.nextEvent();
                            ro.setStatus(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(JOBS)) {
                            event = eventReader.nextEvent();
                            ro.setJobs(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(ADVISER)) {
                            event = eventReader.nextEvent();
                            ro.setAdviser(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TECH)) {
                            event = eventReader.nextEvent();
                            ro.setTech(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TIME_CREATED)) {
                            event = eventReader.nextEvent();
                            ro.setTimeCreated(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TIME_CLOSED)) {
                            event = eventReader.nextEvent();
                            ro.setTimeClosed(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TIME_DUE)) {
                            event = eventReader.nextEvent();
                            ro.setTimeDue(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(NOTES)) {
                            event = eventReader.nextEvent();
                            ro.setNotes(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(IS_WAITING)) {
                            event = eventReader.nextEvent();
                            ro.setWaiting(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    // If we reach end of a repair order element, we add it to the list
                    if (event.isEndElement()) {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals(REPAIR_ORDER)) {
                            roList.add(ro);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(INACTIVE_SOP_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            RepairOrder ro = null;
        
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
            
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a repair order, we create a new repair order
                    if (startElement.getName().getLocalPart().equals(REPAIR_ORDER)) {
                        ro = new RepairOrder();
                        continue;
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(RO_NUMBER)) {
                            event = eventReader.nextEvent();
                            ro.setRepairOrderNumber(Integer.parseInt(event.asCharacters().getData()));
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TAG_NUMBER)) {
                            event = eventReader.nextEvent();
                            ro.setTagNumber(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(YEAR)) {
                            event = eventReader.nextEvent();
                            ro.setYear(Integer.parseInt(event.asCharacters().getData()));
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(MAKE)) {
                            event = eventReader.nextEvent();
                            ro.setMake(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(MODEL)) {
                            event = eventReader.nextEvent();
                            ro.setModel(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(MILEAGE)) {
                            event = eventReader.nextEvent();
                            ro.setMileage(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(CUSTOMER_NAME)) {
                            event = eventReader.nextEvent();
                            ro.setCustomerName(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(PHONE_NUMBER)) {
                            event = eventReader.nextEvent();
                            ro.setPhoneNumber(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(STATUS)) {
                            event = eventReader.nextEvent();
                            ro.setStatus(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(JOBS)) {
                            event = eventReader.nextEvent();
                            ro.setJobs(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(ADVISER)) {
                            event = eventReader.nextEvent();
                            ro.setAdviser(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TECH)) {
                            event = eventReader.nextEvent();
                            ro.setTech(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TIME_CREATED)) {
                            event = eventReader.nextEvent();
                            ro.setTimeCreated(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TIME_CLOSED)) {
                            event = eventReader.nextEvent();
                            ro.setTimeClosed(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(TIME_DUE)) {
                            event = eventReader.nextEvent();
                            ro.setTimeDue(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(NOTES)) {
                            event = eventReader.nextEvent();
                            ro.setNotes(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(IS_WAITING)) {
                            event = eventReader.nextEvent();
                            ro.setWaiting(event.asCharacters().getData());
                            continue;
                        }
                    }
                
                    // If we reach end of a repair order element, we add it to the list
                    if (event.isEndElement()) {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals(REPAIR_ORDER)) {
                            roList.add(ro);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
    
    public void saveLists() {
        
        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(ACTIVE_RO_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);
    
            StartElement activeStartElement = eventFactory.createStartElement("", "", "active_list");
            eventWriter.add(activeStartElement);
            eventWriter.add(end);
            
            for (RepairOrder ro: roList) {
                saveRepairOrder(eventWriter, eventFactory, ro);
            }
            
            eventWriter.add(eventFactory.createEndElement("", "", "active_list"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problem with Active RO file: " + e.getMessage());
            e.printStackTrace();
        } catch (XMLStreamException e) {
            System.out.println("Problem writing repair order: " + e.getMessage());
            e.printStackTrace();
        }
    
        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(INACTIVE_RO_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);
        
            StartElement activeStartElement = eventFactory.createStartElement("", "", "inactive_list");
            eventWriter.add(activeStartElement);
            eventWriter.add(end);
        
            for (RepairOrder ro: inactiveList) {
                saveRepairOrder(eventWriter, eventFactory, ro);
            }
        
            eventWriter.add(eventFactory.createEndElement("", "", "inactive_list"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problem with Inactive RO file: " + e.getMessage());
            e.printStackTrace();
        } catch (XMLStreamException e) {
            System.out.println("Problem writing repair order: " + e.getMessage());
            e.printStackTrace();
        }
    
        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(INACTIVE_SOP_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);
        
            StartElement activeStartElement = eventFactory.createStartElement("", "", "inactive_sop_list");
            eventWriter.add(activeStartElement);
            eventWriter.add(end);
        
            for (RepairOrder ro: sopInactiveList) {
                saveRepairOrder(eventWriter, eventFactory, ro);
            }
        
            eventWriter.add(eventFactory.createEndElement("", "", "inactive_sop_list"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problem with Inactive SOP RO file: " + e.getMessage());
            e.printStackTrace();
        } catch (XMLStreamException e) {
            System.out.println("Problem writing repair order: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    private void saveRepairOrder(XMLEventWriter eventWriter, XMLEventFactory eventFactory, RepairOrder ro)
        throws FileNotFoundException, XMLStreamException {
        
        XMLEvent end = eventFactory.createDTD("\n");
        
        // create repair order open tag
        StartElement configStartElement = eventFactory.createStartElement("", "", REPAIR_ORDER);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, RO_NUMBER, ro.getRepairOrderNumber());
        createNode(eventWriter, TAG_NUMBER, ro.getTagNumber());
        createNode(eventWriter, YEAR, ro.getYear());
        createNode(eventWriter, MAKE, ro.getMake());
        createNode(eventWriter, MODEL, ro.getModel());
        createNode(eventWriter, MILEAGE, ro.getMileage());
        createNode(eventWriter, CUSTOMER_NAME, ro.getCustomerName());
        createNode(eventWriter, PHONE_NUMBER, ro.getPhoneNumber());
        createNode(eventWriter, STATUS, ro.getStatus());
        createNode(eventWriter, JOBS, ro.getJobs());
        createNode(eventWriter, ADVISER, ro.getAdviser());
        createNode(eventWriter, TECH, ro.getTech());
        createNode(eventWriter, TIME_CREATED, ro.getTCreatedString());
        createNode(eventWriter, TIME_CLOSED, ro.getTClosedString());
        createNode(eventWriter, TIME_DUE, ro.getTimeDue());
        createNode(eventWriter, NOTES, ro.getNotes().getNote());
        createNode(eventWriter, IS_WAITING, ro.isWaiting());
        
        eventWriter.add(eventFactory.createEndElement("", "", REPAIR_ORDER));
        eventWriter.add(end);
    }
    
    public void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
    
    public void createNode(XMLEventWriter eventWriter, String name, int value) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters("" + value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
    
}
