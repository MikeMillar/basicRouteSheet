package com.mikemillar.basicroutesheet.datamodels;

import com.mikemillar.basicroutesheet.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public final class Refresher {
    
    private Timer timer;
    
    public Refresher(Controller controller, long delay, long period) {
        timer = new Timer();
        timer.schedule(new RefresherTask(controller), delay, period);
    }
    
    public void end() {
        timer.cancel();
    }
    
    public static class RefresherTask extends TimerTask {
        
        private Controller controller;
        
        public RefresherTask(Controller controller) {
            this.controller = controller;
        }
        
        @Override
        public void run() {
            RepairOrderData.getInstance().updateList();
            controller.refreshList();
        }
    }
    
}
