package net.andreaskluth;

import org.junit.Assert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@SpringBootApplication
public class OptimisticLockingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(OptimisticLockingApplication.class, args);

        VersionedRepository repo = ctx.getBean(VersionedRepository.class);

        // Initial
        Versioned transientVersioned = new Versioned("versioned");
        String id = transientVersioned.getId();
        transientVersioned.addEntry(new VersionedEntry("entry"));
        repo.save(transientVersioned);
        Assert.assertEquals(0, transientVersioned.getVersion());
        Versioned old = repo.findById(id).get();
        Assert.assertNotNull(old.getCreated());

        sleep();

        // Update
        Versioned persitentVersioned = repo.findById(id).get();
        persitentVersioned.addEntry(new VersionedEntry("entry2"));
        repo.save(persitentVersioned);
        Assert.assertEquals(1, persitentVersioned.getVersion());
        Assert.assertNotNull(persitentVersioned.getCreated());
        Assert.assertTrue(repo.findById(id).get().getUpdated().isAfter(old.getUpdated()));

        System.out.println(repo.findById(id).get().getCreated());
        System.out.println(repo.findById(id).get().getUpdated());

        // Optimistic locking
        try {
            Versioned versionOne = repo.findById(id).get();
            versionOne.setVersion(0);
            versionOne.addEntry(new VersionedEntry("entry3"));
            repo.save(versionOne);

            throw new IllegalStateException();
        }
        catch (ObjectOptimisticLockingFailureException oEx) {
            // This is expected...
        }

        sleep();

        Versioned anotherOne = repo.findById(id).get();
        anotherOne.setName("updates");
        repo.save(anotherOne);

        System.out.println(repo.findById(id).get().getCreated());
        System.out.println(repo.findById(id).get().getUpdated());

        ctx.close();
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
