package SimpleParse;

import java.util.*;

/**
 * Petri Net Transition object.
 *
 * <P>Stores attributes of a Petri Net Transition.
 *
 * @author Benjamin Smith	bensmith@iastate.edu
 * @see <a href="http://orcid.org/0000-0003-2607-9338">My ORCID is: 0000-0003-2607-9338</a>
 *
 */
public class PetriTransition {
    final String id;

    /**
     * Constructor
     * @param anID Identifier for this transition
     */
    public PetriTransition(String anID) {
        id = anID;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        PetriTransition other = (PetriTransition) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * Return this object in string form, formatted for the Smart tool.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "trans " + id + ";";
    }


}
