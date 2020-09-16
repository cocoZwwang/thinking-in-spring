package thinking.in.spring;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable observable = new EventObservable();
        observable.addObserver(new EventObserver());
        observable.notifyObservers("hello word!");
    }

    static class EventObservable extends Observable{
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object arg) {
            EventObject eventObject = (EventObject) arg;
            System.out.println("收到事件：" + eventObject);
        }
    }
}
