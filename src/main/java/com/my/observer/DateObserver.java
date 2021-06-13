package com.my.observer;

import com.my.dao.book.BookOnTicketDAO;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class DateObserver extends TimerTask {

    BookOnTicketDAO bookOnTicketDAO;

    public DateObserver(BookOnTicketDAO bookOnTicketDAO){
        this.bookOnTicketDAO = bookOnTicketDAO;
    }

    @Override
    public void run() {
        bookOnTicketDAO.updateFine();
    }

    public void startObserve(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY,0);
        today.set(Calendar.MINUTE,1);
        today.set(Calendar.SECOND,0);
        Timer timer = new Timer();
        timer.schedule(this,today.getTime(), TimeUnit.MILLISECONDS.convert(1,TimeUnit.DAYS));
    }
}
