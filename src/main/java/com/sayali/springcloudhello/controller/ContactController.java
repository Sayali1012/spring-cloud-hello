package com.sayali.springcloudhello.controller;

// import org.apache.tomcat.jni.Thread;
import java.lang.Thread;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RestController;
import com.sayali.springcloudhello.repository.ContactRepository;
import java.util.List;
import com.sayali.springcloudhello.model.Contact;

@RestController
public class ContactController {
    private ContactRepository repository;

    ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/contacts")
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/deactivate"})
    public Boolean deactivate() {
        int numCore = 2;
        int numThreadsPerCore = 2;
        double load = 0.8;
        final long duration = 100000;
        for (int thread = 0; thread < numCore * numThreadsPerCore; thread++) {
            new BusyThread("Thread" + thread, load, duration).start();
        }
        return true;
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Contact> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(rec -> ResponseEntity.ok().body(rec))
                .orElse(ResponseEntity.notFound().build());
    }

    private static class BusyThread extends Thread {
        private double load;
        private long duration;

        /**
         * Constructor which creates the thread
         * @param name Name of this thread
         * @param load Load % that this thread should generate
         * @param duration Duration that this thread should generate the load for
         */
        public BusyThread(String name, double load, long duration) {
            super(name);
            this.load = load;
            this.duration = duration;
        }

        /**
         * Generates the load when run
         */
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                // Loop for the given duration
                while (System.currentTimeMillis() - startTime < duration) {
                    // Every 100ms, sleep for the percentage of unladen time
                    if (System.currentTimeMillis() % 100 == 0) {
                        Thread.sleep((long) Math.floor((1 - load) * 100));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
