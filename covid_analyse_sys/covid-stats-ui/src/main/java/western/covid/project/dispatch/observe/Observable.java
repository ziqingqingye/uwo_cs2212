package western.covid.project.dispatch.observe;

/**
 * A interface define the basic function for observable design pattern.
 * @author Tianci Du
 * @version 1.0
 */
public interface Observable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyUpdate();
}
