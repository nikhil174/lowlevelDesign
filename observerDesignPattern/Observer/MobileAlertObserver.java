package Observer;

import Observable.StockObservable;

public class MobileAlertObserver implements NotificationAlertObserver {
    String userName;
    StockObservable observable;

    public MobileAlertObserver(String userName, StockObservable observable) {
        this.observable = observable;
        this.userName = userName;
    }

    @Override
    public void update() {
        sendMsgOnMobile(userName, "Product is in stock hurry up!");
    }

    private void sendMsgOnMobile(String userName, String msg) {
        System.out.println("Message sent to : " + userName);
    }
}
