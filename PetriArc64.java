package SimpleParse;

import java.util.*;

/**
 * Petri Net Arc object.
 *
 * <P>Stores attributes of a Petri Net Arc.
 *
 * @author Benjamin Smith	bensmith@iastate.edu
 * @see <a href="http://orcid.org/0000-0003-2607-9338">My ORCID is: 0000-0003-2607-9338</a>
 *
 */
public class PetriArc64 {

    final String id;
    final String source;
    final String target;
    final long cardinality;

    /**
     * Constructor.
     *
     * @param anID		Identifier for the Arc
     * @param aSource	ID for the source place or transition of this arc
     * @param aTarget	ID for the target place or transition of this arc
     * @param aCard		Cardinality of this Arc (how many tokens to remove or produce)
     */
    public PetriArc64(String anID, String aSource, String aTarget, long aCard) {
        id = anID;
        source = aSource;
        target = aTarget;
        cardinality = aCard;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (cardinality ^ (cardinality >>> 32));
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        return result;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PetriArc64 other = (PetriArc64) obj;
        if (cardinality != other.cardinality)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        return true;
    }


    /* (non-Javadoc)
     * Return this object as a string, formatted for the Smart tool
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "arcs (" + source + ":" + target + ":" + cardinality + ");";
    }

}
