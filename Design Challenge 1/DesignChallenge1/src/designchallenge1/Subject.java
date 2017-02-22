package designchallenge1;

import observer.ObserverAdapter;

public interface Subject {
    public void register(ObserverAdapter oa);
    public void unregister(ObserverAdapter oa);
    public void notifyObservers();
}
