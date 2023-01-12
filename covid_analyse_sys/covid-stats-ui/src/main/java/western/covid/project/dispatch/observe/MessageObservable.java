package western.covid.project.dispatch.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * A class implement Observable interface mainly function as a observer collector.
 * By calling notify method to inform all observer to update result.
 * @author Tianci Du
 * @version 1.0
 */
public class MessageObservable implements Observable{

    private List<Observer> observerList;
    private Object message;

    public MessageObservable(){
        observerList=new ArrayList<>();
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * Add an observer in list.
     * @param observer
     */
    @Override
    public void registerObserver(Observer observer) {
        if (observer!=null){
            observerList.add(observer);
        }
    }

    /**
     * remove a observer from list.
     * @param observer
     */
    @Override
    public void removeObserver(Observer observer) {
        if (observer!=null){
            observerList.remove(observer);
        }
    }

    /**
     * notify all observer to call update method.
     */
    @Override
    public void notifyUpdate() {
        if (observerList.size()>0){
            for (int i=0;i<observerList.size();i++){
                Observer observer=observerList.get(i);
                observer.update(message);
            }
        }
    }
}
