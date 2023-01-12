package western.covid.project.dispatch.observe;


/**
 * A class can implement the {@code Observer} interface when it
 * ants to be informed of changes in observable objects.
 * @author Tianci Du
 * @version 1.0
 */
public interface Observer {

    void update(Object obj);
}
