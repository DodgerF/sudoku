package event;

import java.util.*;

public class EventBus {
    private static EventBus instance;
    private Map<Class, List<IEventListener>> listeners = new HashMap<>();
    private EventBus(){ }
    public static EventBus getInstance(){
        if (instance == null){
            instance = new EventBus();
        }
        return instance;
    }

    public void invoke(IEvent event){
        Class eventClass = event.getClass();
        List<IEventListener> eventListeners = listeners.get(eventClass);

        if (eventListeners == null){
            System.out.println("There are no listeners for the event.");
            return;
        }

        for (IEventListener eventListener : eventListeners){
            eventListener.handle(event);
        }
    }
    public <T extends IEvent> void subscribe(Class<T> eventClass, IEventListener<T> listener){
        if(!listeners.containsKey(eventClass)){
            listeners.put(eventClass, new LinkedList<>());
        }
        listeners.get(eventClass).add(listener);
    }
}
