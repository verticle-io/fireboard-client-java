package io.verticle.oss.fireboard.client;


import java.util.Iterator;

/**
 * @author Jens Saade
 */
public class StatusJSONConverter {


    public static final String EVENT = "{{_EVENT_}}";
    public static final String CATEGORY = "{{_CATEGORY_}}";
    public static final String IDENT = "{{_IDENT_}}";
    public static final String MESSAGE = "{{_MESSAGE_}}";
    public static final String STATUS = "{{_STATUS_}}";
    public static final String SEVERITY = "{{_SEVERITY_}}";
    public static final String LINK = "{{_LINK_}}";
    public static final String CORRELATIONID = "{{_CORRELATIONID_}}";

    public String convert(Status status){


        StringBuffer sb = new StringBuffer();

        sb.append("{\n");   // start doc
        sb.append("\t\"event\":\""+EVENT+"\",\n");
        sb.append("\t\"ident\":\""+IDENT+"\",\n");
        sb.append("\t\"category\":\""+CATEGORY+"\",\n");
        sb.append("\t\"status\":\""+STATUS+"\",\n");
        sb.append("\t\"severity\":\""+SEVERITY+"\",\n");
        sb.append("\t\"message\":\""+MESSAGE+"\",\n");
        sb.append("\t\"link\":\""+LINK+"\",\n");
        sb.append("\t\"correlationId\":\""+CORRELATIONID+"\",\n");
        sb.append("\t\"messagePropertySection\":[\n");

        Iterator sectionIterator = status.getMessagePropertySection().iterator();
        while (sectionIterator.hasNext()){

            MessagePropertySection section = (MessagePropertySection) sectionIterator.next();
            sb.append("\t\t{\n");   // start doc
            sb.append("\t\t\t\"name\":\""+section.getName()+"\",\n");
            sb.append("\t\t\t\"uuid\":\""+section.getUuid()+"\",\n");
            sb.append("\t\t\t\"messageProperties\": [\n");

            Iterator propertyIterator = section.getMessageProperties().iterator();
            while(propertyIterator.hasNext()){

                MessageProperty property = (MessageProperty) propertyIterator.next();
                sb.append("\t\t\t\t{\n");   // start doc
                sb.append("\t\t\t\t\t\"name\":\""+property.getName()+"\",\n");
                sb.append("\t\t\t\t\t\"value\":\""+property.getValue()+"\"\n");
                if (propertyIterator.hasNext()){
                    sb.append("\t\t\t\t},\n");   // more elements
                } else {
                    sb.append("\t\t\t\t}\n");   // finish properties
                }
            }
            sb.append("\t\t\t]\n"); // end messageProperties


            if (sectionIterator.hasNext()){
                sb.append("\t\t},\n");   // more elements
            } else {
                sb.append("\t\t}\n");   // finish properties
            }


        }

        sb.append("\t]\n"); // end messageProperties section
        sb.append("}\n"); // end doc

        String json = sb.toString();
        json = json.replace(EVENT, status.getEvent() != null ? status.getEvent() : "");
        json = json.replace(CATEGORY, status.getCategory() != null ? status.getCategory() : "");
        json = json.replace(IDENT, status.getIdent() != null ? status.getIdent() : "");
        json = json.replace(MESSAGE, status.getMessage() != null ? status.getMessage() : "");
        json = json.replace(STATUS, status.getStatus() != null ? status.getStatus() : "");
        json = json.replace(SEVERITY, status.getSeverity() != null ? status.getSeverity() : "");
        json = json.replace(LINK, status.getLink() != null ? status.getLink() : "");
        json = json.replace(CORRELATIONID, "");

        return json;
    }

}
