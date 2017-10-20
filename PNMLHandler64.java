package SimpleParse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

/**
 * Petri Net Markup Language Basic Parser.
 *
 * <P>Reads a .pnml format file and creates basic object representation for PT nets.
 *
 * @author Benjamin Smith	bensmith@iastate.edu
 * @see <a href="http://orcid.org/0000-0003-2607-9338">My ORCID is: 0000-0003-2607-9338</a>
 *
 */
public class PNMLHandler64 extends DefaultHandler {

    boolean inPlace = false;
    boolean inArc = false;
    boolean placeMarked = false;
    boolean hasMarking = false;
    boolean arcCard = false;
    String placeName;
    long placeInit;
    String tmpValue;
    String tranName;


    String arcID;
    String arcSource;
    String arcTarget;
    long arcCardinality;

    public ArrayList<PetriPlace64> places;
    public ArrayList<PetriTransition> trans;
    public ArrayList<PetriArc64> arcs;

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("place")) {
            String id = attributes.getValue("id");
            placeName = id;
            placeInit = 0L;
            inPlace = true;
        } else if (qName.equalsIgnoreCase("initialMarking")) {
            placeMarked = true;
        } else if (qName.equalsIgnoreCase("transition")) {
            String id = attributes.getValue("id");
            tranName = id;
            trans.add(new PetriTransition(tranName));
        } else if (qName.equalsIgnoreCase("arc")) {
            String id = attributes.getValue("id");
            String source = attributes.getValue("source");
            String target = attributes.getValue("target");
            arcID = id;
            arcSource = source;
            arcTarget = target;
            arcCardinality = 1L;	// default value, might get changed later
            inArc = true;
        } else if (inArc && (qName.equalsIgnoreCase("inscription"))) {
            arcCard = true;
        }
    }


    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("text")) {
            if (placeMarked) {
                long marking = Long.parseLong(tmpValue);
                placeInit = marking;
                hasMarking = true;

            } else if (arcCard) {
                long cardinality = Long.parseLong(tmpValue);
                arcCardinality = cardinality;

                arcCard = false;
            }
        } else if (qName.equalsIgnoreCase("place")) {
            if (inPlace) {
                if (hasMarking) {
                    places.add(new PetriPlace64(placeName, placeInit));
                } else {
                    places.add(new PetriPlace64(placeName));
                }
                inPlace = false;
                hasMarking = false;
            }
            placeMarked = false;
        } else if (qName.equalsIgnoreCase("arc")) {
            PetriArc64 temp = new PetriArc64(arcID, arcSource, arcTarget, arcCardinality);
            arcs.add(temp);

            inArc = false;
            arcCard = false;
        } else if (qName.equalsIgnoreCase("initialMarking")) {
            placeMarked = false;
        }

    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        tmpValue = new String(ch, start, length);
    }
}
