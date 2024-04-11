import Observable.IPhoneObservable;
import Observable.StockObservable;
import Observer.EmailAlertObserver;
import Observer.MobileAlertObserver;
import Observer.NotificationAlertObserver;

public class Store {
    public static void main(String args[]) {
        StockObservable iphoneObservable = new IPhoneObservable();

        NotificationAlertObserver observer1 = new EmailAlertObserver("user1@gmail.com", iphoneObservable);
        NotificationAlertObserver observer2 = new EmailAlertObserver("user2@gmail.com", iphoneObservable);
        NotificationAlertObserver observer3 = new MobileAlertObserver("User 3", iphoneObservable);

        iphoneObservable.add(observer1);
        iphoneObservable.add(observer2);
        iphoneObservable.add(observer3);

        iphoneObservable.setStockCount(10);
        iphoneObservable.setStockCount(0);
        iphoneObservable.setStockCount(100);
    }
}
